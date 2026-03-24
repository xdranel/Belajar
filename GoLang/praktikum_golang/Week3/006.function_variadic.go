package main

import "fmt"

func test(numbers int) {
	for i := range numbers {
		fmt.Printf("%d\n", i)
	}
}

func sumAll(numbers ...int) int {
	total := 0
	for _, number := range numbers {
		total += number
	}
	return total
}

func main() {
	total := sumAll(10, 20, 30)
	fmt.Println(total)

	test(10)
}
