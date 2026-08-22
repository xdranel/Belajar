package main

import "fmt"

func selisih(a, b int) int {
	if a > b {
		return a - b
	} else {
		return b - a
	}
}

func tukar(a, b *int) {
	temp := *a
	*a = *b
	*b = temp
}

func tampil(label string, a, b int) {
	fmt.Printf("%s : a=%d b=%d\n", label, a, b)
}

func main() {
	var a, b int
	fmt.Scan(&a, &b)
	tampil("Sebelum", a, b)
	tukar(&a, &b)
	tampil("Sesudah", a, b)
	fmt.Printf("Selisih : %d\n", selisih(a, b))
}
