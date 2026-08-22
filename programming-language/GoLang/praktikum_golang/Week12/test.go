package main

import "fmt"

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
}
