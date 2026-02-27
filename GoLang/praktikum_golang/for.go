package main

import "fmt"

func main() {
	var menu int
	running := true

	for running {
		fmt.Println()
		fmt.Println("Menu")
		fmt.Println("1. Tambah")
		fmt.Println("2. Kurang")
		fmt.Println("3. Exit")
		fmt.Println()
		fmt.Printf("Masukkan menu: ")
		fmt.Scan(&menu)

		var a, b, c int

		switch menu {
		case 1:
			fmt.Print("\033[H\033[2J")
			fmt.Printf("Masukkan angka pertama: ")
			fmt.Scan(&a)
			fmt.Printf("Masukkan angka kedua: ")
			fmt.Scan(&b)
			c = a + b
			fmt.Print("Penjumlahan a + b = ", c)
		case 2:
			fmt.Print("\033[H\033[2J")
			fmt.Printf("Masukkan angka pertama: ")
			fmt.Scan(&a)
			fmt.Printf("Masukkan angka kedua: ")
			fmt.Scan(&b)
			c = a - b
			fmt.Print("Penjumlahan a + b = ", c)
		case 3:
			fmt.Printf("Terimakasih")
			running = false
		}
	}
}
