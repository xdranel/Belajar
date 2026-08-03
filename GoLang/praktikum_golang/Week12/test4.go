package main

import "fmt"

func InsertionSort(arr []int) {
	n := len(arr)

	for i := 1; i < n; i++ {
		key := arr[i]
		j := i - 1

		for j >= 0 && arr[j] > key {
			arr[j+1] = arr[j]
			j--
		}

		arr[j+1] = key
	}
}

func main() {
	numbers := []int{29, 10, 14}
	fmt.Println("Before sorting:", numbers)

	InsertionSort(numbers)
	fmt.Println("After sorting: ", numbers)
}
