package main

import "fmt"

func main() {
	// Boolean Comparison
	// && And
	// || Or
	// ! Opposite

	var nilaiAkhir int = 90
	var absensi int = 80

	var lulusNilaiAkhir bool = nilaiAkhir >= 80
	var lulusAbsensi bool = absensi > 80

	var lulus bool = lulusNilaiAkhir && lulusAbsensi
	fmt.Println(lulus)
}
