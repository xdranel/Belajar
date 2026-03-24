package main

import "fmt"

func addNormal(a, b int) int {
	c := a + b
	return c
}

func changingValue(c, d *int) {
	fmt.Scan(c, d)

	fmt.Println("C = ", c, "D = ", d)
}

func main() {
	// var a, b int
	// fmt.Println("A = ", a, "B = ", b)
	// fmt.Scan(&a, &b)
	// fmt.Println(a, " + ", b)
	// fmt.Println("A = ", a, "B = ", b)

	c := 10
	d := 5
	fmt.Println("C = ", c, "D = ", d)
	changingValue(&c, &d)
	fmt.Println("C = ", c, "D = ", d)
}
