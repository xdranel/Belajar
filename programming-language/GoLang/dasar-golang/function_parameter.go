package main

import "fmt"

func sayHelloTo(name string, age int) {
	fmt.Println("Hello", name, "You are", age, "years old")
}

func main() {
	sayHelloTo("Denis", 20)
}
