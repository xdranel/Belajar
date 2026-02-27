package main

import "fmt"

func main() {
	type NoKTP string

	var eKTP NoKTP = "787823523"

	var contoh string = "21387123"
	var contohKTP NoKTP = NoKTP(contoh)

	fmt.Println(eKTP)
	fmt.Println(contohKTP)
}
