package main

import "fmt"

func main() {
	// Basic For-Loop
	for counter := 1; counter <= 10; counter++ {
		fmt.Println("Perulangan Ke", counter)
	}

	names := []string{"John", "Jake", "Jane"}
	for i := 0; i < len(names); i++ {
		fmt.Println(names[i])
	}

	// For-Range
	for index, name := range names {
		fmt.Println("Index", index, "=", name)
	}

	for _, name := range names {
		fmt.Println(name)
	}
}
