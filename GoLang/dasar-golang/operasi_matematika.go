package main

import "fmt"

func main() {
	a := 10
	b := 5
	c := 4
	d := 7

	x := a + b - c*d
	fmt.Println(x)

	// Augmented Assignment
	a += 5
	b -= 5
	c *= 5
	d /= 5
	fmt.Println(a, b, c, d)

	var i int = 10
	i += i // i = i + i atau i = 10 + 10
	fmt.Println(i)

	i += i // i = i + i atau i = 20 + 20
	fmt.Println(i)

	var o int = 5
	o += 10 // o = o + o atau o = 5 + 10
	fmt.Println(o)

	// Unary Operator
	// ++ Increment 1 : 20 + 1 = 21
	// -- Decrement -1 : 20 - 1 = 19
	// - Negative : -20
	// + Positive : 20
	// ! Opposite :

	var j int = 5
	j++
	j++
	fmt.Println(j)

	j--
	j--
	fmt.Println(j)
}
