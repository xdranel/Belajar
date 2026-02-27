package main

import "fmt"

func main() {
	isRunning := true

	for isRunning {
		var menu int
		fmt.Println("Masukan Menu:")
		fmt.Println("1. Tambah")
		fmt.Println("2. Exit")

		fmt.Print("Pilih Menu:")
		fmt.Scan(&menu)
		switch menu {
		case 1:
			fmt.Println("Hai 1")
		case 2:
			fmt.Println("Terima Kasih")
			isRunning = false
		}
	}
}
