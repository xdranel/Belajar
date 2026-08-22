package main

import "fmt"

type Karyawan struct {
	NIP        string
	Nama       string
	GajiBersih int
}

type Kompensasi struct {
	NIP       string
	GajiPokok int
	Potongan  int
}

func insertionSort(data []Karyawan) {
	for i := 1; i < len(data); i++ {
		key := data[i]
		j := i - 1
		for j >= 0 && data[j].GajiBersih < key.GajiBersih {
			data[j+1] = data[j]
			j--
		}
		data[j+1] = key
	}
}

func totalRekursif(data []Karyawan, i int) int {
	if i >= len(data) {
		return 0
	}
	return data[i].GajiBersih + totalRekursif(data, i+1)
}

func main() {
	var n int
	fmt.Scan(&n)

	type DataAwal struct {
		NIP  string
		Nama string
	}
	pegawai := make([]DataAwal, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&pegawai[i].NIP, &pegawai[i].Nama)
	}

	kompensasi := make([]Kompensasi, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&kompensasi[i].NIP, &kompensasi[i].GajiPokok, &kompensasi[i].Potongan)
	}

	karyawan := make([]Karyawan, n)
	for i := 0; i < n; i++ {
		karyawan[i].NIP = pegawai[i].NIP
		karyawan[i].Nama = pegawai[i].Nama
		for _, k := range kompensasi {
			if k.NIP == pegawai[i].NIP {
				karyawan[i].GajiBersih = k.GajiPokok - k.Potongan
				break
			}
		}
	}

	insertionSort(karyawan)
	for _, k := range karyawan {
		fmt.Printf("%s (%s): Rp%d\n", k.Nama, k.NIP, k.GajiBersih)
	}
	total := totalRekursif(karyawan, 0)
	fmt.Printf("Total pengeluaran: Rp%d\n", total)
}
