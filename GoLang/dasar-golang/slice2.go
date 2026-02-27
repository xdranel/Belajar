package main

import "fmt"

func main() {
	// make function is the most common way to create a slice
	// when you know you'll have data soon but don't have it yet
	s := make([]int, 3, 5)

	s[0] = 100
	s[1] = 200
	s[2] = 300
	fmt.Printf("Slice s: %v, Len: %d, Cap: %d\n", s, len(s), cap(s))

	s = append(s, 400)
	fmt.Printf("After append: %v, Len: %d, Cap: %d\n", s, len(s), cap(s))

	// create a slice from an array (or another slice) using the [low:high] syntax
	names := [4]string{"Alice", "Bob", "Charlie", "David"}

	window := names[1:3]
	fmt.Println("Slice window:", window)

	window[0] = "Boby"
	fmt.Println("Original Array after change:", names)
}
