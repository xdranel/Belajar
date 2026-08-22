package main

import "fmt"

type Customer struct {
	Name, Address string
	Age           int
}

func (customer Customer) sayHello(name string) {
	fmt.Println("Hello", name, "My Name is", customer.Name)
}

func main() {
	//	person := []Customer{
	//		{"Gendhi", "Surabaya", 30},
	//		{"x", "y", 20},
	//	}
	//
	//	for i, person := range person {
	//		fmt.Println(i+1, ":", person)
	//	}

	// person[0].sayHello("Jake")

	person := Customer{
		Name:    "Gendhi",
		Address: "Surabaya",
		Age:     30,
	}

	orang := Customer{
		Name:    "Joko",
		Address: "Surabaya",
		Age:     30,
	}

	fmt.Println(person)

	people := []Customer{person, orang}

	for i, person := range people {
		fmt.Println(i+1, ":", person)
	}

	person.sayHello("Jake")
	orang.sayHello("Budi")
}
