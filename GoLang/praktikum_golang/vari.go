package main

import "fmt"

func main() {
	var (
		n   int
		sum int = 0
	)
	fmt.Print("Masukan Angka: ")
	fmt.Scan(&n)

	for i := 0; i < n; i++ {
		n += i
	}
	fmt.Println("Output :", n)
}
