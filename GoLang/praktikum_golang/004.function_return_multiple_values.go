package main

import "fmt"

func getFullName(id string) (string, string) {
	return id + "Joko", "wi"
}

func main() {
	firstName, lastName := getFullName("1203")
	fmt.Println(firstName, lastName)

	namaPertama, _ := getFullName("1202")
	fmt.Println(namaPertama)
}
