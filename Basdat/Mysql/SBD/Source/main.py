import mysql.connector
from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from sqlalchemy.exc import OperationalError
from models import (
    Base,
    Mahasiswa,
    MataKuliah,
    Transaksi,
    create_fake_data,
    close_session,
)


def main():
    conn = mysql.connector.connect(host="127.0.0.1", user="root", password="plm987!")

    cursor = conn.cursor()

    try:
        cursor.execute("CREATE DATABASE IF NOT EXISTS test_sisbasdat")
        print("Database 'test_sisbasdat' berhasil dibuat atau sudah ada.")
    except mysql.connector.Error as err:
        print(f"Terjadi kesalahan saat membuat database: {err}")

    try:
        cursor.execute("USE test_sisbasdat")
        print("Berhasil memilih database 'test_sisbasdat'.")
    except mysql.connector.Error as err:
        print(f"Terjadi kesalahan saat memilih database: {err}")

    cursor.close()
    conn.close()

    DATABASE_URL = "mysql+mysqlconnector://root:@localhost:3306/test_sisbasdat"
    engine = create_engine(DATABASE_URL, echo=True)

    SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
    session = SessionLocal()

    try:
        Base.metadata.create_all(bind=engine)
        print("Tabel berhasil dibuat di database.")
    except OperationalError as e:
        print(f"Terjadi kesalahan saat membuat tabel: {e}")

    create_fake_data(session)

    close_session(session)


if __name__ == "__main__":
    main()
