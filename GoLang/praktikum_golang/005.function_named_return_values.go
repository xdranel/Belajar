package main

import "fmt"

func getCompleteName() (firstName int, middleName, lastName string) {
	firstName = 1
	middleName = "Joko"
	lastName = "wi"

	return firstName, middleName, lastName
}

func main() {
	firstName, middleName, lastName := getCompleteName()
	fmt.Println(firstName, middleName, lastName)
}
