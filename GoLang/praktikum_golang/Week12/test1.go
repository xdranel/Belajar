package main

import "fmt"

type arrInt [5]int

func selectionSort1(T *arrInt, n int) {
	var t, i, j, idx_min int
	i = 1
	for i <= n-1 {
		fmt.Println(i)
		fmt.Println(n)
		idx_min = i - 1
		j = i
		for j < n {
			if T[idx_min] > T[j] {
				idx_min = j
			}
			j = j + 1
		}
		t = T[idx_min]
		T[idx_min] = T[i-1]
		T[i-1] = t
		i = i + 1
	}
}

func selectionSort2(T *arrInt, n int) {
	for i := 0; i < n-1; i++ {
		idx_min := i

		for j := i + 1; j < n; j++ {
			if T[idx_min] > T[j] {
				idx_min = j
			}
		}

		if idx_min != i {
			T[idx_min], T[i] = T[i], T[idx_min]
		}
	}
}

func main() {
	numbers := arrInt{28, 10, 14}
	fmt.Println("Before sorting:", numbers)

	selectionSort1(&numbers, len(numbers))
	fmt.Println("After sorting: ", numbers)
}
