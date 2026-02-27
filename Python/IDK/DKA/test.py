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

# Define fuzzy variables
startup_age = ctrl.Antecedent(np.arange(0, 15, 1), "Startup_Age")
number_of_founders = ctrl.Antecedent(np.arange(1, 5, 1), "Number_of_Founders")
employees_count = ctrl.Antecedent(np.arange(1, 1000, 1), "Employees_Count")
funding_amount = ctrl.Antecedent(np.arange(11000, 50000001, 10000), "Funding_Amount")
status = ctrl.Consequent(np.arange(0, 1.1, 0.1), "Startup_Status")

# Define membership functions
startup_age["low"] = fuzz.trimf(startup_age.universe, [0, 0, 5])
startup_age["medium"] = fuzz.trimf(startup_age.universe, [3, 5, 9])
startup_age["high"] = fuzz.trimf(startup_age.universe, [5, 10, 15])

number_of_founders["low"] = fuzz.trimf(number_of_founders.universe, [0, 0, 1])
number_of_founders["medium"] = fuzz.trimf(number_of_founders.universe, [1, 2, 3])
number_of_founders["high"] = fuzz.trimf(number_of_founders.universe, [3, 4, 5])

employees_count["low"] = fuzz.trimf(employees_count.universe, [0, 0, 399])
employees_count["medium"] = fuzz.trimf(employees_count.universe, [350, 499, 699])
employees_count["high"] = fuzz.trimf(employees_count.universe, [499, 799, 1000])

funding_amount["low"] = fuzz.trimf(funding_amount.universe, [11000, 11000, 20000000])
funding_amount["medium"] = fuzz.trimf(
    funding_amount.universe, [18000000, 25000000, 35000000]
)
funding_amount["high"] = fuzz.trimf(
    funding_amount.universe, [25000000, 40000000, 50000001]
)

# Output membership functions
status["fail"] = fuzz.trimf(status.universe, [0, 0, 0.5])
status["success"] = fuzz.trimf(status.universe, [0.5, 1, 1])

rules = [
    # Failure rules (output = 0 or close to 0)
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["low"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["low"]
        & employees_count["low"]
        & funding_amount["medium"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["low"]
        & employees_count["medium"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["low"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["high"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["medium"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["low"]
        & employees_count["low"]
        & funding_amount["low"],
        status["fail"],
    ),
    # Success rules (output = 1 or close to 1)
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["medium"]
        & employees_count["high"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["high"]
        & employees_count["medium"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["medium"]
        & employees_count["high"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["medium"]
        & employees_count["medium"]
        & funding_amount["medium"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["high"]
        & employees_count["high"]
        & funding_amount["medium"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["high"]
        & number_of_founders["low"]
        & employees_count["high"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["medium"]
        & employees_count["medium"]
        & funding_amount["high"],
        status["success"],
    ),
    # Mixed conditions - lean towards more logical outcomes
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["medium"]
        & employees_count["medium"]
        & funding_amount["medium"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["low"]
        & employees_count["medium"]
        & funding_amount["medium"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["medium"]
        & employees_count["low"]
        & funding_amount["medium"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["high"]
        & employees_count["medium"]
        & funding_amount["medium"],
        status["fail"],
    ),
    ctrl.Rule(
        startup_age["low"]
        & number_of_founders["medium"]
        & employees_count["high"]
        & funding_amount["high"],
        status["success"],
    ),
    ctrl.Rule(
        startup_age["medium"]
        & number_of_founders["high"]
        & employees_count["medium"]
        & funding_amount["high"],
        status["success"],
    ),
    # Additional coverage rules
    ctrl.Rule(employees_count["high"] & funding_amount["high"], status["success"]),
    ctrl.Rule(employees_count["low"] & funding_amount["low"], status["fail"]),
    ctrl.Rule(startup_age["high"] & funding_amount["high"], status["success"]),
    ctrl.Rule(startup_age["low"] & funding_amount["low"], status["fail"]),
]

status_ctrl = ctrl.ControlSystem(rules)
status_sim = ctrl.ControlSystemSimulation(status_ctrl)


def predict_single_startup(age, founders, employees, funding):
    """Prediksi untuk satu startup"""
    try:
        status_sim.input["Startup_Age"] = age
        status_sim.input["Number_of_Founders"] = founders
        status_sim.input["Employees_Count"] = employees
        status_sim.input["Funding_Amount"] = funding

        status_sim.compute()
        predicted_status = status_sim.output["Startup_Status"]

        return predicted_status
    except Exception as e:
        print(
            f"Error predicting for Age={age}, Founders={founders}, Employees={employees}, Funding={funding}: {e}"
        )
        return None


def predict_from_csv(dataframe, show_details=False):
    """Prediksi untuk semua data dalam CSV"""

    # Pastikan kolom yang diperlukan ada
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

    # List untuk menyimpan hasil
    predictions = []
    prediction_labels = []

    # Proses setiap baris data
    for index, row in dataframe.iterrows():
        age = row["Startup_Age"]
        founders = row["Number_of_Founders"]
        employees = row["Employees_Count"]
        funding = row["Funding_Amount"]

        # Prediksi
        pred_score = predict_single_startup(age, founders, employees, funding)

        if pred_score is not None:
            predictions.append(pred_score)

            # Interpretasi hasil
            if pred_score <= 0.5:
                label = "FAILURE"
            else:
                label = "SUCCESS"
            prediction_labels.append(label)

            # Tampilkan detail jika diminta
            if show_details:
                print(
                    f"Row {index + 1}: Age={age}, Founders={founders}, Employees={employees}, Funding=${funding:,}"
                )
                print(f"  → Score: {pred_score:.3f}, Prediction: {label}\n")
        else:
            predictions.append(None)
            prediction_labels.append("ERROR")

    # Tambahkan hasil ke dataframe
    result_df = dataframe.copy()
    result_df["Predicted_Score"] = predictions
    result_df["Predicted_Label"] = prediction_labels

    # Statistik hasil
    valid_predictions = [p for p in predictions if p is not None]
    if valid_predictions:
        success_count = sum(1 for label in prediction_labels if label == "SUCCESS")
        failure_count = sum(1 for label in prediction_labels if label == "FAILURE")

        print(f"\n=== RINGKASAN HASIL PREDIKSI ===")
        print(f"Total data: {len(dataframe)}")
        print(
            f"Prediksi SUCCESS: {success_count} ({success_count / len(dataframe) * 100:.1f}%)"
        )
        print(
            f"Prediksi FAILURE: {failure_count} ({failure_count / len(dataframe) * 100:.1f}%)"
        )
        print(f"Average prediction score: {np.mean(valid_predictions):.3f}")
        print(
            f"Score range: {min(valid_predictions):.3f} - {max(valid_predictions):.3f}"
        )

    return result_df


# Jalankan prediksi untuk semua data dalam CSV
print("Memproses data dari CSV...")
results = predict_from_csv(df, show_details=True)

# Simpan hasil ke file CSV baru jika berhasil
if results is not None:
    output_filename = "startup_predictions_results.csv"
    results.to_csv(output_filename, index=False)
    print(f"\n✅ Hasil prediksi telah disimpan ke '{output_filename}'")

    # Tampilkan beberapa contoh hasil
    print(f"\n=== CONTOH HASIL PREDIKSI ===")
    print(
        results[
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

# Optional: Buat visualisasi distribusi prediksi
if results is not None and "Predicted_Score" in results.columns:
    plt.figure(figsize=(12, 8))

    # Subplot 1: Histogram skor prediksi
    plt.subplot(2, 2, 1)
    valid_scores = results["Predicted_Score"].dropna()
    plt.hist(valid_scores, bins=20, alpha=0.7, color="skyblue", edgecolor="black")
    plt.title("Distribusi Skor Prediksi")
    plt.xlabel("Prediction Score")
    plt.ylabel("Frequency")
    plt.axvline(x=0.5, color="red", linestyle="--", label="Threshold (0.5)")
    plt.legend()

    # Subplot 2: Bar chart success vs failure
    plt.subplot(2, 2, 2)
    label_counts = results["Predicted_Label"].value_counts()
    colors = [
        "lightcoral" if label == "FAILURE" else "lightgreen"
        for label in label_counts.index
    ]
    plt.bar(label_counts.index, label_counts.values, color=colors)
    plt.title("Jumlah Prediksi Success vs Failure")
    plt.ylabel("Count")

    # Subplot 3: Scatter plot - Funding vs Employee dengan prediksi
    plt.subplot(2, 2, 3)
    success_data = results[results["Predicted_Label"] == "SUCCESS"]
    failure_data = results[results["Predicted_Label"] == "FAILURE"]

    plt.scatter(
        failure_data["Funding_Amount"],
        failure_data["Employees_Count"],
        alpha=0.6,
        c="red",
        label="Predicted Failure",
        s=30,
    )
    plt.scatter(
        success_data["Funding_Amount"],
        success_data["Employees_Count"],
        alpha=0.6,
        c="green",
        label="Predicted Success",
        s=30,
    )
    plt.xlabel("Funding Amount")
    plt.ylabel("Employee Count")
    plt.title("Funding vs Employees (Colored by Prediction)")
    plt.legend()
    plt.xscale("log")

    # Subplot 4: Box plot skor berdasarkan umur startup
    plt.subplot(2, 2, 4)
    age_groups = results.groupby("Startup_Age")["Predicted_Score"].apply(list)
    valid_age_data = [
        (age, scores) for age, scores in age_groups.items() if len(scores) > 0
    ]

    if valid_age_data:
        ages, score_lists = zip(*valid_age_data)
        bp = plt.boxplot(score_lists)
        plt.xticks(range(1, len(ages) + 1), ages, rotation=45)
        plt.title("Distribusi Skor Prediksi per Umur Startup")
        plt.xlabel("Startup Age")
        plt.ylabel("Prediction Score")
    else:
        plt.text(
            0.5,
            0.5,
            "No valid data for age groups",
            ha="center",
            va="center",
            transform=plt.gca().transAxes,
        )
        plt.title("Distribusi Skor Prediksi per Umur Startup")

    plt.tight_layout()
    plt.savefig("startup_prediction_analysis.png", dpi=300, bbox_inches="tight")
    print(f"\n📊 Grafik analisis disimpan sebagai 'startup_prediction_analysis.png'")
    plt.show()
