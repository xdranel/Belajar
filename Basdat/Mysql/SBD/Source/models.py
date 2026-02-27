from faker import Faker
from sqlalchemy import create_engine, Column, Integer, String, ForeignKey
from sqlalchemy.orm import relationship, sessionmaker
from sqlalchemy.ext.declarative import declarative_base
import random

Base = declarative_base()


class Mahasiswa(Base):
    __tablename__ = "mahasiswa"

    id = Column(Integer, primary_key=True, index=True)
    nim = Column(String(10), unique=True, index=True)
    nama = Column(String(100), index=True)
    kota_asal = Column(String(100))
    umur = Column(Integer)

    transaksi = relationship("Transaksi", back_populates="mahasiswa")


class MataKuliah(Base):
    __tablename__ = "mata_kuliah"

    id = Column(Integer, primary_key=True, index=True)
    kode_mk = Column(String(10), unique=True, index=True)
    nama_mk = Column(String(100))

    transaksi = relationship("Transaksi", back_populates="mata_kuliah")


class Transaksi(Base):
    __tablename__ = "transaksi"

    id = Column(Integer, primary_key=True, index=True)
    mahasiswa_id = Column(Integer, ForeignKey("mahasiswa.id"))
    mata_kuliah_id = Column(Integer, ForeignKey("mata_kuliah.id"))
    semester = Column(String(255), nullable=False)

    mahasiswa = relationship("Mahasiswa", back_populates="transaksi")
    mata_kuliah = relationship("MataKuliah", back_populates="transaksi")


def create_fake_data(session):
    fake = Faker("id_ID")

    for _ in range(100000):
        mahasiswa = Mahasiswa(
            nim=fake.unique.random_number(digits=10),
            nama=fake.name(),
            kota_asal=fake.city(),
            umur=random.randint(18, 30),
        )
        session.add(mahasiswa)

    for _ in range(5000):
        mata_kuliah = MataKuliah(
            kode_mk=fake.unique.bothify(text="MK"), nama_mk=fake.word()
        )
        session.add(mata_kuliah)

    session.commit()

    mahasiswa_ids = [mahasiswa.id for mahasiswa in session.query(Mahasiswa).all()]
    mata_kuliah_ids = [
        mata_kuliah.id for mata_kuliah in session.query(MataKuliah).all()
    ]

    for _ in range(200000):
        transaksi = Transaksi(
            mahasiswa_id=random.choice(mahasiswa_ids),
            mata_kuliah_id=random.choice(mata_kuliah_ids),
            semester=fake.word(),
        )
        session.add(transaksi)

    session.commit()


def close_session(session):
    session.close()
