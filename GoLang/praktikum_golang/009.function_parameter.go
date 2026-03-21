package main

import (
	"fmt"
	"strings"
)

// type Filter func(string) string

// Higher-Order Functions
// Strategy Pattern -> Middleware logic(log, login auth etc)
func sayHelloWithFilter(name string, filter func(string) string) {
	fmt.Println("Hello ", filter(name))
}

func sayHelloWithTwoInputs(first string, last string, filter func(string, string) string) {
	result := filter(first, last)
	fmt.Println("Hello", result)
}

func sayHelloWithReturnError(name string, filter func(string) (string, error)) {
	result, err := filter(name) // We must catch BOTH returns
	if err != nil {
		fmt.Println("Error:", err)
		return
	}

	fmt.Println("Hello", result)
}

func upper(s string) string {
	return strings.ToUpper(s)
}

func spamFilter(name string) string {
	if name == "Anjing" {
		return "..."
	} else {
		return name
	}
}

func fullNameFilter(first string, last string) string {
	return first + " " + last
}

func validationFilter(name string) (string, error) {
	if name == "" {
		return "", fmt.Errorf("name cannot be empty")
	}
	return name, nil
}

func main() {
	sayHelloWithFilter("Eko", spamFilter)

	filter := spamFilter
	sayHelloWithFilter("Anjing", filter)

	// sayHelloWithFilter("Joko", upper)

	// sayHelloWithTwoInputs("Budi", "Santoso", fullNameFilter)

	// sayHelloWithReturnError("Budi", validationFilter)
}
