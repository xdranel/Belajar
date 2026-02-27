package main

import (
	"fmt"
)

func main() {
	var names [3]string

	names[0] = "John"
	names[1] = "Doe"
	names[2] = "Domn"

	fmt.Println(names)
	fmt.Println(names[0])
	fmt.Println(names[1])
	fmt.Println(names[2])

	numbers := [...]int{
		90,
		80,
		95,
		70,
		60,
	}

	fmt.Println(numbers)
	fmt.Println(numbers[0])
	fmt.Println(numbers[1])
	fmt.Println(numbers[2])
	fmt.Println(numbers[3])
	fmt.Println(numbers[4])

	numbers[3] = 120
	fmt.Println(len(numbers))
	fmt.Println(numbers)

	numbers[2] = 0
	fmt.Println(numbers)
}
