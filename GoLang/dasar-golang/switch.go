package main

import "fmt"

func main() {
	name := "John"

	switch name {
	case "John":
		fmt.Println("Hello John")
	case "Jake":
		fmt.Println("Hi Jake")
	default:
		fmt.Println("Hi, Boleh Kenalan?")
	}

	length := len(name)
	// lebih baik pakai if-else condition
	switch {
	case length > 5:
		fmt.Println("Nama Terlalu Panjang")
	case length <= 5 && length > 0:
		fmt.Println("Nama Sudah Benar")
	default:
		fmt.Println("Nama Tidak Boleh Kosong")
	}

	switch length := len(name); length > 5 {
	case true:
		fmt.Println("Nama Terlalu Panjang")
	case false:
		fmt.Println("Nama Sudah Benar")
	}
}
