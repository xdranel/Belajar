package main

import "fmt"

func getHello(name string) string {
	hello := "Hello " + name
	return hello
	// return "Hello " + name
}

func main() {
	result := getHello("John")
	fmt.Println(result)
}
