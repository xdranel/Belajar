package main

import "fmt"

func cekGenap(x int) bool {
	return x%2 == 0
}

func main() {
	fmt.Println(cekGenap(9))
}
