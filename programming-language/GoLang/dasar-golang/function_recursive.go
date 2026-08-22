package main

import "fmt"

func factiorialLoop(value int) int {
	result := 1

	for i := value; i > 0; i-- {
		result *= i
	}

	return result
}

func factiorialRecursive(value int) int {
	if value == 1 {
		return 1
	} else {
		return value * factiorialRecursive(value-1)
	}
}

func main() {
	fmt.Println(factiorialLoop(10))

	fmt.Println(factiorialRecursive(10))
}
