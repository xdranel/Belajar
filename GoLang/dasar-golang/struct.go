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
	//person := []Customer{
	//	{"Gendhi", "Surabaya", 30},
	//}
	//
	//for i, person := range person {
	//	fmt.Println(i+1, ":", person)
	//}

	person := Customer{
		Name:    "Gendhi",
		Address: "Surabaya",
		Age:     30,
	}

	people := []Customer{person}

	for i, person := range people {
		fmt.Println(i+1, ":", person)
	}

	person.sayHello("Jake")
}
