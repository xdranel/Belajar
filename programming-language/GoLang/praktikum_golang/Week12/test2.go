package main

import "fmt"

func selectionSort3(arr []int) {
	n := len(arr)

	for i := 0; i < n-1; i++ {
		minIdx := i

		for j := i + 1; j < n; j++ {
			if arr[j] < arr[minIdx] {
				minIdx = j
			}
		}

		if minIdx != i {
			temp := arr[i]
			arr[i] = arr[minIdx]
			arr[minIdx] = temp
		}
	}
}

func main() {
	numbers := []int{29, 10, 14}
	fmt.Println("Before sorting:", numbers)

	selectionSort3(numbers)
	fmt.Println("After sorting: ", numbers)
}
