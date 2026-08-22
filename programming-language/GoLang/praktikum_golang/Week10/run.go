package main

import "fmt"

func main() {
	a := [...]int{5, 2, 7, 4, 9}
	n := len(a)
	maxIndex := 0

	for i := 1; i < n; i++ {
		if a[i] > a[maxIndex] {
			maxIndex = i
		}
	}

	fmt.Println("Index of Max: ", maxIndex)
	fmt.Println("Value of Max: ", a[maxIndex])
}
