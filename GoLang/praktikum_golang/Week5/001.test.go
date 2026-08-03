package main

import "fmt"

func FibonacciRecursive(n int) int {
	if n <= 1 {
		return n
	}
	return FibonacciRecursive(n-1) + FibonacciRecursive(n-2)
}

func main() {
	var a int
	fmt.Scan(&a)

	fmt.Println("Total : ", FibonacciRecursive(a))
}
