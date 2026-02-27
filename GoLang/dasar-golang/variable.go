package main

import "fmt"

func main() {
	var name, address string = "halo", "indonesia"
	age, height, weight := 20, "seratus tujuh puluh centimeter", 75
	fmt.Println(name)

	name = "halooo"
	fmt.Println(name)
	fmt.Println(address)

	var (
		firstName  = "Gendhi"
		middleName = "Ramona"
		lastName   = "Prastyo"
	)

	fmt.Println(firstName)
	fmt.Println(middleName)
	fmt.Println(lastName)
}
