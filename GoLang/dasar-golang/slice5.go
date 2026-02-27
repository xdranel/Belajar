package main

import (
	"bytes"
	"fmt"
)

func main() {
	// If you don’t know the size of your data

	// #1
	// If you have no idea how much data is coming (e.g., reading lines from a user's terminal)
	// you can simply initialize an empty slice
	var data []int // Length 0, Capacity 0

	// #2
	// In Go, it is often better to slightly over-estimate than to let Go constantly re-allocate
	// If you expect "some" data: Use make([]T, 0, 16) or 32. This handles small collections without any resizing
	// If you expect "thousands" of items: Use make([]T, 0, 1024)

	// #3
	// If you are reading data from a source (like a file or a network socket)
	// where you don't know the total size, you don't grow a slice one by one. Instead, you use a Buffer
	var b bytes.Buffer // This manages an underlying []byte for you

	b.WriteString("Hello ")
	b.WriteString("World!")

	// Convert back to a slice only when finished
	result := b.Bytes()
	fmt.Println(string(result))

	// If the reason you don't know the size is because the data is arriving over time from different parts of your program
	// you might not want a slice at all

	// -> Use a Channel if :
	// You are processing data as it arrives (a stream).
	// You don't need to store it all in memory at once; you just want to pass it from a "Producer" to a "Consumer."

	// -> Use a Map if :
	// You don't care about the order, but you need to look up items by a unique key (like a User ID).
	// Maps also grow dynamically, but they are optimized for searching rather than ordered storage
}
