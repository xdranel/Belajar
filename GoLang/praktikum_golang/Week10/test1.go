package main

import "fmt"

func main() {
	a := [...]int{5, 2, 7, 9, 6}
	max := 0
	i := 1
	n := len(a)

	for i < n {
		if a[i] > a[max] {
			max = i
		}
		i = i + 1
	}

	fmt.Println("Index of Max: ", max)
	fmt.Println("Value: ", a[max])
}
