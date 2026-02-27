import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import skfuzzy as fuzz
from skfuzzy import control as ctrl

# Load dataset
df = pd.read_csv("startup_failure_prediction.csv")
print(df.head())

# Define fuzzy variables
startup_age = ctrl.Antecedent(np.arange(0, 15, 1), "Startup_Age")
number_of_founders = ctrl.Antecedent(np.arange(1, 5, 1), "Number_Of_Founders")
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


def test_prediction(age, founders, employees, funding):
    try:
        status_sim.input["Startup_Age"] = age
        status_sim.input["Number_Of_Founders"] = founders
        status_sim.input["Employees_Count"] = employees
        status_sim.input["Funding_Amount"] = funding

        status_sim.compute()

        # Fixed: Use correct key name
        predicted_status = status_sim.output["Startup_Status"]
        print(
            f"\nInputs: Age={age}, Founders={founders}, Employees={employees}, Funding=${funding:,}"
        )
        print(f"Predicted Status Score: {predicted_status:.3f}")

        # Interpret the result
        if predicted_status <= 0.5:
            print("Prediction: FAILURE")
        else:
            print("Prediction: SUCCESS")

        return predicted_status

    except Exception as e:
        print(f"Error occurred: {e}")
        print(
            "This might be due to insufficient rules or input values outside defined ranges"
        )


print("=== Binary Startup Success/Failure Prediction ===")

# Test case 1 - Your original inputs
test_prediction(5, 2, 100, 1010000)

# Test case 2 - Young startup with low resources (should fail)
test_prediction(1, 1, 10, 50000)

# Test case 3 - Mature startup with good resources (should succeed)
test_prediction(8, 3, 250, 40000000)

# Test case 4 - High funding startup (should succeed)
test_prediction(3, 2, 150, 25000000)

# Test case 5 - Very low resources (should fail)
test_prediction(1, 1, 5, 20000)
