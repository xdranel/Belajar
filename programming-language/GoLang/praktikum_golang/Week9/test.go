package main

import "fmt"

func main() {
	var names [3]string

	names[0] = "John"
	names[1] = "Kurniawan"
	names[2] = "Sutris"

	fmt.Println(names)
	fmt.Println(names[0])
	fmt.Println(names[1])
	fmt.Println(names[2])

	//	for i := 0; i < len(names); i++ {
	//		fmt.Println(names[i])
	//	}

	numbers := [...]int{
		90,
		80,
		95,
	}

	fmt.Println(numbers)
	fmt.Println(numbers[0])
	fmt.Println(numbers[1])
	fmt.Println(numbers[2])

	numbers[1] = 120
	fmt.Println(len(numbers))
	fmt.Println(numbers)

	numbers[2] = 0
	fmt.Println(numbers)
}
