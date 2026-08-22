import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import skfuzzy as fuzz
from skfuzzy import control as ctrl

# Load dataset
df = pd.read_csv("startup_failure_prediction.csv")
print("Dataset shape:", df.shape)
print("\nDataset info:")
print(df.head())
print("\nDataset columns:", df.columns.tolist())
print("\nDataset description:")
print(df.describe())

# Check for missing values
print("\n=== MISSING VALUES ===")
print(df.isnull().sum())

# Extend ranges slightly to accommodate all data
startup_age = ctrl.Antecedent(np.arange(0, 16, 1), "Startup_Age")
number_of_founders = ctrl.Antecedent(np.arange(1, 5, 1), "Number_of_Founders")
employees_count = ctrl.Antecedent(np.arange(1, 100, 1), "Employees_Count")
funding_amount = ctrl.Antecedent(np.arange(11209, 49993134, 10000), "Funding_Amount")
status = ctrl.Consequent(np.arange(0, 1.1, 0.1), "Startup_Status")

# Define membership functions dengan range yang fleksibel
startup_age["low"] = fuzz.trimf(startup_age.universe, [0, 0, 7])
startup_age["medium"] = fuzz.trimf(startup_age.universe, [0, 7, 16])
startup_age["high"] = fuzz.trimf(startup_age.universe, [7, 16, 16])

number_of_founders["low"] = fuzz.trimf(number_of_founders.universe, [1, 1, 2.5])
number_of_founders["medium"] = fuzz.trimf(number_of_founders.universe, [1, 2.5, 5])
number_of_founders["high"] = fuzz.trimf(number_of_founders.universe, [2.5, 5, 5])

employees_count["low"] = fuzz.trimf(employees_count.universe, [1, 1, 332])
employees_count["medium"] = fuzz.trimf(employees_count.universe, [1, 333, 699])
employees_count["high"] = fuzz.trimf(employees_count.universe, [333, 1000, 1000])

funding_amount["low"] = fuzz.trimf(funding_amount.universe, [11209, 11209, 1600000])
funding_amount["medium"] = fuzz.trimf(
    funding_amount.universe, [11209, 1611209, 3222000]
)
funding_amount["high"] = fuzz.trimf(
    funding_amount.universe, [1611209, 49993134, 49993134]
)

# Output membership functions
status["fail"] = fuzz.trimf(status.universe, [0, 0, 0.5])
status["success"] = fuzz.trimf(status.universe, [0.5, 1, 1])

# Expanded rules untuk coverage yang lebih baik
rules = [
    # Clear failure cases
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["low"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"] & number_of_founders["low"] & employees_count["low"],
        status["fail"],
    ),
    ctrl.Rule(employees_count["low"] & funding_amount["low"], status["fail"]),
    ctrl.Rule(startup_age["low"] & funding_amount["low"], status["fail"]),
    # Clear success cases
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["high"]
        & employees_count["high"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"] & employees_count["high"] & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(employees_count["high"] & funding_amount["high"], status["success"]),
    ctrl.Rule(startup_age["high"] & funding_amount["high"], status["success"]),
    # Medium cases - more balanced approach
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["medium"]
        & employees_count["medium"]
        & funding_amount["medium"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["medium"] & employees_count["medium"] & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"] & number_of_founders["medium"] & employees_count["medium"],
        status["success"],
    ),
    # Mixed conditions
    ctrl.Rule(
        startup_age["low"] & employees_count["high"] & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"] & employees_count["low"] & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(startup_age["medium"] & funding_amount["low"], status["fail"]),
    ctrl.Rule(number_of_founders["low"] & employees_count["low"], status["fail"]),
    # Default rules untuk coverage
    ctrl.Rule(funding_amount["high"], status["success"]),
    ctrl.Rule(employees_count["high"], status["success"]),
    ctrl.Rule(startup_age["high"], status["success"]),
    ctrl.Rule(funding_amount["low"], status["fail"]),
    ctrl.Rule(employees_count["low"], status["fail"]),
    ctrl.Rule(startup_age["low"], status["fail"]),
]

status_ctrl = ctrl.ControlSystem(rules)
status_sim = ctrl.ControlSystemSimulation(status_ctrl)


def predict_single_startup(age, founders, employees, funding):
    """Prediksi untuk satu startup dengan error handling yang lebih baik"""
    try:
        # Check for missing values
        if pd.isna(age) or pd.isna(founders) or pd.isna(employees) or pd.isna(funding):
            return None, "MISSING_DATA"

        # Check if values are within acceptable ranges
        if (
            age < startup_age.universe.min()
            or age > startup_age.universe.max()
            or founders < number_of_founders.universe.min()
            or founders > number_of_founders.universe.max()
            or employees < employees_count.universe.min()
            or employees > employees_count.universe.max()
            or funding < funding_amount.universe.min()
            or funding > funding_amount.universe.max()
        ):
            return None, "OUT_OF_RANGE"

        status_sim.input["Startup_Age"] = age
        status_sim.input["Number_of_Founders"] = founders
        status_sim.input["Employees_Count"] = employees
        status_sim.input["Funding_Amount"] = funding

        status_sim.compute()
        predicted_status = status_sim.output["Startup_Status"]

        return predicted_status, "SUCCESS"

    except Exception as e:
        return None, f"COMPUTE_ERROR: {str(e)}"


def predict_from_csv(dataframe, show_details=False):
    """Prediksi untuk semua data dalam CSV dengan detailed error reporting"""

    required_columns = [
        "Startup_Age",
        "Number_of_Founders",
        "Employees_Count",
        "Funding_Amount",
    ]
    missing_columns = [col for col in required_columns if col not in dataframe.columns]

    if missing_columns:
        print(f"Error: Kolom berikut tidak ditemukan dalam CSV: {missing_columns}")
        return None

    print("=== Prediksi Startup Success/Failure dari CSV ===\n")

    predictions = []
    prediction_labels = []
    error_details = []

    # Counters untuk error tracking
    error_counts = {
        "MISSING_DATA": 0,
        "OUT_OF_RANGE": 0,
        "COMPUTE_ERROR": 0,
        "SUCCESS": 0,
    }

    for index, row in dataframe.iterrows():
        age = row["Startup_Age"]
        founders = row["Number_of_Founders"]
        employees = row["Employees_Count"]
        funding = row["Funding_Amount"]

        pred_score, error_type = predict_single_startup(
            age, founders, employees, funding
        )
        error_counts[error_type if error_type != "SUCCESS" else "SUCCESS"] += 1

        if pred_score is not None:
            predictions.append(pred_score)

            if pred_score <= 0.5:
                label = "FAILURE"
            else:
                label = "SUCCESS"
            prediction_labels.append(label)
            error_details.append("OK")

            if show_details:
                print(
                    f"Row {index + 1}: Age={age}, Founders={founders}, Employees={employees}, Funding=${funding:,}"
                )
                print(f"  → Score: {pred_score:.3f}, Prediction: {label}\n")
        else:
            predictions.append(None)
            prediction_labels.append("ERROR")
            error_details.append(error_type)

            if show_details:
                print(
                    f"Row {index + 1}: Age={age}, Founders={founders}, Employees={employees}, Funding=${funding:,}"
                )
                print(f"  → ERROR: {error_type}\n")

    # Tambahkan hasil ke dataframe
    result_df = dataframe.copy()
    result_df["Predicted_Score"] = predictions
    result_df["Predicted_Label"] = prediction_labels
    result_df["Error_Detail"] = error_details

    # Statistik hasil
    valid_predictions = [p for p in predictions if p is not None]
    if valid_predictions:
        success_count = sum(1 for label in prediction_labels if label == "SUCCESS")
        failure_count = sum(1 for label in prediction_labels if label == "FAILURE")
        error_count = sum(1 for label in prediction_labels if label == "ERROR")

        print("\n=== RINGKASAN HASIL PREDIKSI ===")
        print(f"Total data: {len(dataframe)}")
        print(
            f"Prediksi SUCCESS: {success_count} ({success_count / len(dataframe) * 100:.1f}%)"
        )
        print(
            f"Prediksi FAILURE: {failure_count} ({failure_count / len(dataframe) * 100:.1f}%)"
        )
        print(
            f"Error/Tidak bisa diprediksi: {error_count} ({error_count / len(dataframe) * 100:.1f}%)"
        )
        print(f"Average prediction score: {np.mean(valid_predictions):.3f}")
        print(
            f"Score range: {min(valid_predictions):.3f} - {max(valid_predictions):.3f}"
        )

        print("\n=== DETAIL ERROR ===")
        for error_type, count in error_counts.items():
            if count > 0:
                print(f"{error_type}: {count} cases")

    return result_df


# Jalankan prediksi
print("Memproses data dari CSV...")
results = predict_from_csv(df, show_details=False)  # Set True untuk detail

if results is not None:
    # Tampilkan contoh data yang error
    error_data = results[results["Predicted_Label"] == "ERROR"]
    if len(error_data) > 0:
        print("\n=== CONTOH DATA YANG ERROR (5 pertama) ===")
        print(
            error_data[
                [
                    "Startup_Age",
                    "Number_of_Founders",
                    "Employees_Count",
                    "Funding_Amount",
                    "Error_Detail",
                ]
            ].head()
        )

    # Simpan hasil
    output_filename = "startup_predictions_results.csv"
    results.to_csv(output_filename, index=False)
    print("\nHasil prediksi telah disimpan ke '{output_filename}'")

    # Tampilkan contoh hasil yang berhasil
    success_data = results[results["Predicted_Label"] != "ERROR"]
    if len(success_data) > 0:
        print("\n=== CONTOH HASIL PREDIKSI BERHASIL ===")
        print(
            success_data[
                [
                    "Startup_Age",
                    "Number_of_Founders",
                    "Employees_Count",
                    "Funding_Amount",
                    "Predicted_Score",
                    "Predicted_Label",
                ]
            ].head(10)
        )
