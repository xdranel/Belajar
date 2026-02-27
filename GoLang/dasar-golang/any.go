package main

import (
	"fmt"
	"reflect"
)

func main() {
	var data any

	data = "Hello, Go!"
	fmt.Printf("Value: %v, Type: %T\n", data, data) // Value: Hello, Go!, Type: string

	data = 42
	fmt.Printf("Value: %v, Type: %T\n", data, data) // Value: 42, Type: int

	// Using a type assertion to get the underlying string value
	str, ok := data.(string)
	if ok {
		fmt.Println("Asserted as string:", str)
	} else {
		fmt.Println("Assertion failed, data is not a string.")
	}

	// Using a type switch to handle different types
	switch v := data.(type) {
	case int:
		fmt.Println("Data is an int:", v)
	case string:
		fmt.Println("Data is a string:", v)
	default:
		fmt.Printf("Data is an unknown type: %T\n", v)
	}

	scores := map[string]int{"Alice": 95}
	val, ok := scores["Bob"]
	if !ok {
		// val will be 0 (the zero value for int), but ok is false
		fmt.Println("%d Key not found!", val)
	}

	msg, ok := <-myChannel
	if !ok {
		fmt.Println("Channel closed! No more data.")
		return
	}
	fmt.Println("Received:", msg)

	v := reflect.ValueOf(data)
	if v.Kind() == reflect.String {
		fmt.Println("The value is:", v.String())
	}
}
