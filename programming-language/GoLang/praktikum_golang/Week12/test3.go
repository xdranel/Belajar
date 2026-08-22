package main

import "fmt"

type arrInteger [10]int

func insertionSort1(T *arrInteger, n int) {
	var temp, i, j int
	i = 1
	for i <= n-1 {
		j = i
		temp = T[j]
		for j > 0 && temp > T[j-1] {
			T[j] = T[j-1]
			j = j - 1
		}
		T[j] = temp
		i = i + 1
	}
}

func main() {
	numbers := arrInteger{28, 10, 14}
	fmt.Println("Before sorting:", numbers)

	insertionSort1(&numbers, len(numbers))
	fmt.Println("After sorting: ", numbers)
}
