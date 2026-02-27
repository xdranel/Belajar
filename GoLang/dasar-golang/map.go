package main

import (
	"fmt"
)

func main() {
	//
	//var person map[string]string = map[string]string{}
	//person["name"] = "Gendhi"
	//person["address"] = "Prastyo"

	person := map[string]string{
		"name": "",
		"age":  "",
	}

	person["name"] = "Gendhi"
	person["age"] = "21"
	person["address"] = "Surabaya"

	fmt.Println(person)
	fmt.Println(person["name"])
	fmt.Println(person["address"])

	book := make(map[string]string)
	book["title"] = "Golang Book"
	book["author"] = "Denis"
	book["wrong"] = "ups"

	fmt.Println(book)

	delete(book, "wrong")

	fmt.Println(book)
}
