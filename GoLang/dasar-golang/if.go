package main

import "fmt"

func main() {
	var name string
	fmt.Print("Input Name: ")
	fmt.Scan(&name)

	if name == "John" {
		fmt.Println("Hello John")
	} else if name == "Jake" {
		fmt.Println("Hi Jake")
	} else {
		fmt.Println("Hi, Boleh Kenalan?")
	}

	fmt.Println(len(name))

	// length := len(name)
	if length := len(name); length > 5 {
		fmt.Println("Nama Terlalu Panjang")
	} else if length <= 5 && length > 0 {
		fmt.Println("Nama Sudah Benar")
	} else {
		fmt.Println("Nama Tidak Boleh Kosong")
	}
}
