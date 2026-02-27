n = int(input("Masukan Angka: "))
if (n % 2) == 0:
    print("Angka tersebut adalah genap\n")
else:
    print("Angka tersebut adalah ganjil\n")

nilai = int(input("Masukan nilai: "))
nilai = float(nilai)

if nilai >= 85:
    print("Nilai anda A")
elif nilai >= 70 and nilai < 85:
    print("Nilai anda B")
elif nilai >= 55 and nilai < 70:
    print("Nilai anda C")
elif nilai >= 40 and nilai < 55:
    print("Nilai anda D")
else:
    print("Nilai anda E")
