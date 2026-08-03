def greet_reza(nama):
    if nama == "reza" or nama == "Reza":
        print("Selamat datang kembali,", nama, "\n")
    else:
        print("Selamat datang,", nama, "\n")


input_nama = input("Masukan nama: ")
greet_reza(input_nama)


def konversi_jarak(kilometer):
    meter = kilometer * 1000
    return meter


input_kilometer = input("Masukan jarak dalam kilometer: ")
input_kilometer = float(input_kilometer)
hasil = konversi_jarak(input_kilometer)
print("Hasil konversi jarak dari kilometer ke meter adalah:", hasil)
