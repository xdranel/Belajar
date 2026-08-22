package main

import "fmt"

func factorialLoop(value int) int {
	result := 1
	for i := value; i > 0; i-- {
		fmt.Println("i = ", i)
		result *= i
	}
	return result
}

func factorialRecursive(value int) int {
	if value == 1 || value == 0 {
		return 1
	} else {
		return value * factorialRecursive(value-1)
	}
}

func main() {
	result1 := factorialLoop(3)
	fmt.Println("Result 1 : ", result1)

	result2 := factorialRecursive(3)
	fmt.Println("Result 2 : ", result2)
}
