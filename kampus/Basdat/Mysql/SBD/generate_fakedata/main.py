import mysql.connector
from faker import Faker
import random

fake = Faker()

db_config = {
    "user": "root",
    "password": ":)",
    "host": "localhost",
    "database": "test_sisbasdat",
}

connection = mysql.connector.connect(**db_config)
cursor = connection.cursor()


def generate_fake_products(num_products):
    product_ids = []
    for i in range(num_products):
        product_id = f"PD{str(i + 1).zfill(3)}"
        if len(product_id) > 10:
            product_id = product_id[:10]
        product_name = fake.catch_phrase()[:100]
        product_category = random.choice(
            [
                "Laptop",
                "Smartphone",
                "RAM",
                "CPU",
                "GPU",
                "SSD",
                "HDD",
            ]
        )[:100]
        product_description = fake.text()
        price = random.randint(1000000, 30000000)
        stock = random.randint(1, 100)

        cursor.execute(
            "INSERT INTO product (product_id, product_name, product_category, product_description, price, stock) VALUES (%s, %s, %s, %s, %s, %s)",
            (
                product_id,
                product_name,
                product_category,
                product_description,
                price,
                stock,
            ),
        )

        product_ids.append(product_id)

    return product_ids


def generate_fake_customers(num_customers):
    customer_ids = []
    for i in range(num_customers):
        customer_id = f"CS{str(i + 1).zfill(3)}"
        if len(customer_id) > 10:
            customer_id = customer_id[:10]
        name = fake.name()[:100]
        email = fake.unique.email()[:100]
        phone_numb = fake.phone_number()[:20]
        address = fake.address()

        cursor.execute(
            "INSERT INTO customers (customer_id, name, email, phone_numb, address) VALUES (%s, %s, %s, %s, %s)",
            (customer_id, name, email, phone_numb, address),
        )

        customer_ids.append(customer_id)

    return customer_ids


def generate_fake_orders(num_orders, customer_ids):
    order_ids = []
    for i in range(num_orders):
        order_id = f"ORD{str(i + 1).zfill(3)}"
        if len(order_id) > 10:
            order_id = order_id[:10]
        customer_id = random.choice(customer_ids)

        cursor.execute(
            "INSERT INTO orders (order_id, customer_id) VALUES (%s, %s)",
            (order_id, customer_id),
        )

        order_ids.append(order_id)

    return order_ids


def generate_fake_order_details(num_details, order_ids, product_ids):
    for i in range(num_details):
        order_detail_id = f"OD{str(i + 1).zfill(3)}"
        if len(order_detail_id) > 10:
            order_detail_id = order_detail_id[:10]
        order_id = random.choice(order_ids)
        product_id = random.choice(product_ids)
        product_quantity = random.randint(1, 3)

        cursor.execute(
            "INSERT INTO order_details (order_detail_id, order_id, product_id, product_quantity) VALUES (%s, %s, %s, %s)",
            (order_detail_id, order_id, product_id, product_quantity),
        )


def generate_fake_payments(num_payments, order_ids):
    for i in range(num_payments):
        payment_id = f"PAY{str(i + 1).zfill(3)}"
        if len(payment_id) > 10:
            payment_id = payment_id[:10]
        order_id = random.choice(order_ids)
        payment_method = random.choice(["Credit Card", "PayPal", "Bank Transfer"])[:50]
        payment_status = random.choice(["Pending", "Success", "Failed"])[:10]

        cursor.execute(
            "INSERT INTO payment (payment_id, order_id, payment_method, payment_status) VALUES (%s, %s, %s, %s)",
            (
                payment_id,
                order_id,
                payment_method,
                payment_status,
            ),
        )


def main():
    # 2000000
    num_products = 500000
    num_customers = 750000
    num_orders = 250000
    num_order_details = 250000
    num_payments = 250000

    product_ids = generate_fake_products(num_products)

    customer_ids = generate_fake_customers(num_customers)

    order_ids = generate_fake_orders(num_orders, customer_ids)

    generate_fake_order_details(num_order_details, order_ids, product_ids)

    generate_fake_payments(num_payments, order_ids)

    connection.commit()

    cursor.close()
    connection.close()

    print(
        "Fake data for products, customers, orders, order details, and payments generated and inserted into the database."
    )


if __name__ == "__main__":
    main()
