package main

import "fmt"

type Buku struct {
	Kode  string
	Judul string
}

func cariSequential(data []Buku, kode string) int {
	for i := 0; i < len(data); i++ {
		if data[i].Kode == kode {
			return i
		}
	}
	return -1
}

func main() {
	var n int
	fmt.Scan(&n)
	buku := make([]Buku, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&buku[i].Kode, &buku[i].Judul)
	}
	var kodeCari string
	fmt.Scan(&kodeCari)
	idx := cariSequential(buku, kodeCari)
	if idx == -1 {
		fmt.Println("Buku tidak ditemukan")
	} else {
		fmt.Printf("Judul: %s\n", buku[idx].Judul)
	}
}
