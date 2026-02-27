package main

import "fmt"

func main() {
	var n int
	fmt.Scan(&n)

	hasil := 1
	i := 1

	for i <= n {
		hasil = hasil * i
		i++
	}

	fmt.Println(hasil)
}
