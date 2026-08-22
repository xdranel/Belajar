import mysql.connector
from faker import Faker
import random
from datetime import datetime, timedelta

fake = Faker()

db_config = {
    "user": "root",
    "password": "plm987!",
    "host": "localhost",
    "database": "basdat_app_test",
}

connection = mysql.connector.connect(**db_config)
cursor = connection.cursor()


def generate_fake_users(num_users):
    user_ids = []
    for i in range(num_users):
        username = fake.unique.user_name()[:50]
        password = fake.password(length=12)[:255]  # In real app, this should be hashed
        name = fake.name()[:100]
        email = fake.unique.email()[:100]
        role = "user"  # Only adding regular users as requested
        status = random.choice(["active", "inactive"])

        # Random created_at within last 2 years
        created_at = fake.date_time_between(start_date="-2y", end_date="now")

        cursor.execute(
            "INSERT INTO users (username, password, name, email, role, status, created_at) VALUES (%s, %s, %s, %s, %s, %s, %s)",
            (username, password, name, email, role, status, created_at),
        )

        # Get the actual inserted user ID
        user_id = cursor.lastrowid
        user_ids.append(user_id)

    return user_ids


def generate_fake_products(num_products):
    product_ids = []
    for i in range(num_products):
        name = fake.catch_phrase()[:100]
        category = random.choice(
            [
                "Laptop",
                "Smartphone",
                "RAM",
                "CPU",
                "GPU",
                "SSD",
                "HDD",
                "Monitor",
                "Keyboard",
                "Mouse",
            ]
        )[:50]
        price = round(random.uniform(100000, 30000000), 2)  # Using DECIMAL format
        stock = random.randint(0, 100)
        status = random.choice(["active", "inactive"])

        # Random created_at within last year
        created_at = fake.date_time_between(start_date="-1y", end_date="now")

        cursor.execute(
            "INSERT INTO products (name, category, price, stock, status, created_at) VALUES (%s, %s, %s, %s, %s, %s)",
            (name, category, price, stock, status, created_at),
        )

        # Get the actual inserted product ID
        product_id = cursor.lastrowid
        product_ids.append(product_id)

    return product_ids


def generate_fake_activities(num_activities, user_ids, product_ids):
    actions = [
        "Login",
        "Logout",
        "View Product",
        "Add to Cart",
        "Remove from Cart",
        "Purchase",
        "Update Profile",
        "Search Products",
        "View Categories",
        "Contact Support",
        "Write Review",
        "Update Password",
    ]

    for i in range(num_activities):
        user_id = random.choice(user_ids) if user_ids else None
        action = random.choice(actions)[:100]

        # Generate contextual descriptions based on action
        if action == "View Product" and product_ids:
            description = f"Viewed product ID: {random.choice(product_ids)}"
        elif action == "Purchase" and product_ids:
            description = f"Purchased product ID: {random.choice(product_ids)}, Quantity: {random.randint(1, 5)}"
        elif action == "Search Products":
            description = f"Searched for: {fake.word()}"
        elif action == "Login":
            description = f"User logged in from IP: {fake.ipv4()}"
        elif action == "Add to Cart" and product_ids:
            description = f"Added product ID: {random.choice(product_ids)} to cart"
        else:
            description = fake.sentence()

        status = random.choice(["success", "failed"])

        # Random created_at within last 6 months
        created_at = fake.date_time_between(start_date="-6M", end_date="now")

        cursor.execute(
            "INSERT INTO activities (user_id, action, description, status, created_at) VALUES (%s, %s, %s, %s, %s)",
            (user_id, action, description, status, created_at),
        )


def main():
    try:
        print("Starting fake data generation...")

        # Generate fake data
        num_users = 0
        num_products = 0
        num_activities = 250000

        print(f"Generating {num_users} users...")
        user_ids = generate_fake_users(num_users)
        print(f"Generated {len(user_ids)} users")

        print(f"Generating {num_products} products...")
        product_ids = generate_fake_products(num_products)
        print(f"Generated {len(product_ids)} products")

        print(f"Generating {num_activities} activities...")
        generate_fake_activities(num_activities, user_ids, product_ids)
        print(f"Generated {num_activities} activities")

        # Commit all changes
        connection.commit()
        print(
            "All fake data has been generated and inserted into the database successfully!"
        )

    except Exception as e:
        print(f"An error occurred: {e}")
        connection.rollback()

    finally:
        cursor.close()
        connection.close()


if __name__ == "__main__":
    main()
