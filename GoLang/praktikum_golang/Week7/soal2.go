package main

import "fmt"

func faktorial(n int) int {
	if n == 0 || n == 1 {
		return 1
	} else {
		return n * faktorial(n-1)
	}
}

func pangkat(base, exp int) int {
	if exp == 0 {
		return 1
	} else {
		return base * pangkat(base, exp-1)
	}
}

func tampilHasil(n, k, fn, pk int) {
	fmt.Printf("Faktorial : %d! = %d\n", n, fn)
	fmt.Printf("Pangkat   : 2^%d = %d\n", k, pk)
}

func main() {
	var n, k int
	fmt.Scan(&n, &k)
	tampilHasil(n, k, faktorial(n), pangkat(2, k))
}
