package main

import "fmt"

const MAX = 100

type Skripsi struct {
	NIM        string
	Nama       string
	Judul      string
	Pembimbing string
	Topik      string
	Tahun      int
	Status     string
}

var (
	data [MAX]Skripsi
	n    int
)

func inputString(pesan string) string {
	var x string
	fmt.Print(pesan)
	fmt.Scan(&x)
	return x
}

func inputInt(pesan string) int {
	var x int
	fmt.Print(pesan)
	fmt.Scan(&x)
	return x
}

func cariNIM(nim string) int {
	for i := 0; i < n; i++ {
		if data[i].NIM == nim {
			return i
		}
	}
	return -1
}

func tampilData() {
	if n == 0 {
		fmt.Println("Belum ada data.")
		return
	}

	for i := 0; i < n; i++ {
		fmt.Println("================================")
		fmt.Println("NIM        :", data[i].NIM)
		fmt.Println("Nama       :", data[i].Nama)
		fmt.Println("Judul      :", data[i].Judul)
		fmt.Println("Pembimbing :", data[i].Pembimbing)
		fmt.Println("Topik      :", data[i].Topik)
		fmt.Println("Tahun      :", data[i].Tahun)
		fmt.Println("Status     :", data[i].Status)
	}
}

func tambahData() {
	if n >= MAX {
		fmt.Println("Data penuh")
		return
	}

	var s Skripsi

	s.NIM = inputString("NIM : ")
	if cariNIM(s.NIM) != -1 {
		fmt.Println("NIM sudah ada")
		return
	}

	s.Nama = inputString("Nama : ")
	s.Judul = inputString("Judul : ")
	s.Pembimbing = inputString("Pembimbing : ")
	s.Topik = inputString("Topik : ")
	s.Tahun = inputInt("Tahun : ")
	s.Status = inputString("Status : ")

	data[n] = s
	n++

	fmt.Println("Data berhasil ditambah")
}

func editData() {
	nim := inputString("Masukkan NIM : ")
	idx := cariNIM(nim)

	if idx == -1 {
		fmt.Println("Data tidak ditemukan")
		return
	}

	data[idx].Nama = inputString("Nama baru : ")
	data[idx].Judul = inputString("Judul baru : ")
	data[idx].Pembimbing = inputString("Pembimbing baru : ")
	data[idx].Topik = inputString("Topik baru : ")
	data[idx].Tahun = inputInt("Tahun baru : ")
	data[idx].Status = inputString("Status baru : ")

	fmt.Println("Data berhasil diubah")
}

func hapusData() {
	nim := inputString("Masukkan NIM : ")
	idx := cariNIM(nim)

	if idx == -1 {
		fmt.Println("Data tidak ditemukan")
		return
	}

	for i := idx; i < n-1; i++ {
		data[i] = data[i+1]
	}

	n--
	fmt.Println("Data berhasil dihapus")
}

func sequentialNama() {
	nama := inputString("Cari nama : ")
	ketemu := false

	for i := 0; i < n; i++ {
		if data[i].Nama == nama {
			fmt.Println("Ditemukan :", data[i].NIM, data[i].Nama)
			ketemu = true
		}
	}

	if !ketemu {
		fmt.Println("Data tidak ditemukan")
	}
}

func selectionSortNama() {
	for i := 0; i < n-1; i++ {
		min := i

		for j := i + 1; j < n; j++ {
			if data[j].Nama < data[min].Nama {
				min = j
			}
		}

		data[i], data[min] = data[min], data[i]
	}

	fmt.Println("Data berhasil diurutkan berdasarkan nama")
}

func insertionSortTahun() {
	for i := 1; i < n; i++ {
		temp := data[i]
		j := i - 1

		for j >= 0 && data[j].Tahun > temp.Tahun {
			data[j+1] = data[j]
			j--
		}

		data[j+1] = temp
	}

	fmt.Println("Data berhasil diurutkan berdasarkan tahun")
}

func binarySearchNama() {
	selectionSortNama()

	nama := inputString("Cari nama : ")

	kiri := 0
	kanan := n - 1

	for kiri <= kanan {
		tengah := (kiri + kanan) / 2

		if data[tengah].Nama == nama {
			fmt.Println("Data ditemukan")
			fmt.Println("NIM :", data[tengah].NIM)
			return
		}

		if nama < data[tengah].Nama {
			kanan = tengah - 1
		} else {
			kiri = tengah + 1
		}
	}

	fmt.Println("Data tidak ditemukan")
}

func statistik() {
	fmt.Println("Total Dokumen :", n)

	for i := 0; i < n; i++ {
		sudah := false

		for k := 0; k < i; k++ {
			if data[k].Tahun == data[i].Tahun {
				sudah = true
			}
		}

		if !sudah {
			jumlah := 0

			for j := 0; j < n; j++ {
				if data[j].Tahun == data[i].Tahun {
					jumlah++
				}
			}

			fmt.Println("Status", data[i].Status, ":", jumlah, "skripsi")
		}
	}
}

func main() {
	var pilih int

	for {
		fmt.Println("\n===== MENU SKRIPSI =====")
		fmt.Println("1. Tampil Data")
		fmt.Println("2. Tambah Data")
		fmt.Println("3. Edit Data")
		fmt.Println("4. Hapus Data")
		fmt.Println("5. Sequential Search Nama")
		fmt.Println("6. Binary Search Nama")
		fmt.Println("7. Selection Sort Nama")
		fmt.Println("8. Insertion Sort Tahun")
		fmt.Println("9. Statistik")
		fmt.Println("10. Keluar")
		fmt.Print("Pilih : ")
		fmt.Scan(&pilih)

		switch pilih {
		case 1:
			tampilData()
		case 2:
			tambahData()
		case 3:
			editData()
		case 4:
			hapusData()
		case 5:
			sequentialNama()
		case 6:
			binarySearchNama()
		case 7:
			selectionSortNama()
		case 8:
			insertionSortTahun()
		case 9:
			statistik()
		case 10:
			return
		default:
			fmt.Println("Pilihan tidak valid")
		}
	}
}
