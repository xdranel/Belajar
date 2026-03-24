package main

import "fmt"

func sumAllSlice(numbers ...int) int {
	total := 0
	for _, number := range numbers {
		total += number
	}
	return total
}

func main() {
	numbers := []int{10, 10, 10}
	total := sumAllSlice(numbers...)
	fmt.Println(total)
}
