input_bilangan = int(input("Masukan bilangan bulat: "))

for i in range(1, input_bilangan + 1, 2):
    print(i)
print("\n")


def penjumlahan_sendiri(n):
    if n < 1:
        return 0

    else:
        total = 0
        i = 0
        while i <= n:
            total += i
            i += 1

    return total


input_bil = int(input("Masukan Angka : "))
hasil = penjumlahan_sendiri(input_bil)
print(hasil)
