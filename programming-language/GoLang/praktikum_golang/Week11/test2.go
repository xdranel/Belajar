package main

import "fmt"

const maxInt = 1234

type arrInt [maxInt]int

func binarySearch1(T arrInt, n int, X int) bool {
	found := false
	var med int
	kr := 0
	kn := n - 1
	for kr <= kn && !found {
		med = (kr + kn) / 2
		if X > T[med] {
			kr = med + 1
		} else if X < T[med] {
			kn = med - 1
		} else {
			found = true
		}
	}
	return found
}

func binarySearch2(T arrInt, n int, X int) int {
	found := -1
	var med int
	kr := 0
	kn := n - 1
	for kr <= kn && found == -1 {
		med = (kr + kn) / 2
		if X > T[med] {
			kr = med + 1
		} else if X < T[med] {
			kn = med - 1
		} else {
			found = med
		}
	}
	return found
}

func binarySearch3(T arrInt, X int) int {
	low := 0
	high := len(T) - 1

	for low <= high {
		mid := low + (high-low)/2

		if T[mid] == X {
			return mid
		}

		if T[mid] < X {
			low = mid + 1
		} else {
			high = mid - 1
		}
	}
	return -1
}

func binarySearchString(arr []string, target string) int {
	low := 0
	high := len(arr) - 1

	for low <= high {
		mid := low + (high-low)/2

		if arr[mid] == target {
			return mid
		}

		if arr[mid] < target {
			low = mid + 1
		} else {
			high = mid - 1
		}
	}
	return -1
}

func main() {
	var datas arrInt
	datas = arrInt{10, 20, 30, 40}
	n := len(datas)

	// nums := []int{10, 22, 35, 47, 50, 63, 75, 88, 99}
	// words := []string{"apple", "banana", "cherry", "date", "elderberry", "fig"}

	target := 30
	result := binarySearch2(datas, n, target)
	fmt.Printf("String Search: Found '%d' at index %d\n", target, result)
}
