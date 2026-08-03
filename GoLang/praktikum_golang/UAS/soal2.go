package main

import "fmt"

type Siswa struct {
	Nama  string
	Nilai int
}

func selectionSort(data []Siswa) {
	n := len(data)
	for i := 0; i < n-1; i++ {
		idxMaks := i
		for j := i + 1; j < n; j++ {
			if data[j].Nilai > data[idxMaks].Nilai {
				idxMaks = j
			}
		}
		data[i], data[idxMaks] = data[idxMaks], data[i]
	}
}

func main() {
	var n int
	fmt.Scan(&n)
	siswa := make([]Siswa, n)
	for i := 0; i < n; i++ {
		fmt.Scan(&siswa[i].Nama, &siswa[i].Nilai)
	}
	selectionSort(siswa)
	for i := 0; i < len(siswa); i++ {
		s := siswa[i]

		status := "TIDAK LULUS"
		if s.Nilai >= 75 {
			status = "LULUS"
		}
		fmt.Printf("%s %d %s\n", s.Nama, s.Nilai, status)
	}
}
