package main

import "fmt"

func getHello(nim, firstName, lastName string) string {
	return nim + " " + firstName + " " + lastName
}

func main() {
	result := getHello("1203", "Joko", "Widodo")
	fmt.Println(result)
}
