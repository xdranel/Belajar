def hitung_rata_rata(nilai):
    hasil = sum(nilai) / len(nilai)
    return hasil


data_siswa = {}

while True:
    nama = input("Masukan Nama Siswa: ")

    if nama.lower() == "selesai":
        break

    nilai = []
    for i in range(1, 4):
        input_nilai = input("Masukan Nilai %d: " % i)
        input_nilai = float(input_nilai)
        nilai.append(input_nilai)

    data_siswa[nama] = nilai


print("Hasil Rata-Rata dan Status Kelulusan :")
for nama, nilai in data_siswa.items():
    rata_rata = hitung_rata_rata(nilai)
    if rata_rata >= 70:
        status = "Lulus"
    else:
        status = "Tidak Lulus"
    print(f"Nama: {nama}, Rata-Rata: {rata_rata:.2f}, Status: {status}")
