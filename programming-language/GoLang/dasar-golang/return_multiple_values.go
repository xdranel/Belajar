package main

import "fmt"

func getFullName() (string, string) {
	return "John", "Doe"
}

func main() {
	firstName, _ := getFullName()
	fmt.Println(firstName)
}
