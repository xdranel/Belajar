package main

import "fmt"

const maxArr = 2023

type arrInt [maxArr]int

func terkecil1(tabInt arrInt, n int) int {
	min := tabInt[0]
	for j := 1; j < n; j++ {
		if min > tabInt[j] {
			min = tabInt[j]
		}
	}
	return min
}

func main() {
	var data arrInt
	var n int

	for n < maxArr {
		var input int
		fmt.Scan(&input)
		if input == -1 {
			break
		}
		data[n] = input
		n++
	}

	result := terkecil1(data, n)

	fmt.Printf("The smallest value among the first %d elements is: %d\n", n, result)
}
