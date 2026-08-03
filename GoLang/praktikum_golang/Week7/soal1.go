package main

import "fmt"

func celsiusToFahrenheit(c float64) float64 {
	return (c * 9 / 5) + 32
}

func celsiusToKelvin(c float64) float64 {
	return c + 273.15
}

func tampilHasil(c, f, k float64) {
	fmt.Printf("Celsius    : %.2f\n", c)
	fmt.Printf("Fahrenheit : %.2f\n", f)
	fmt.Printf("Kelvin     : %.2f\n", k)
}

func main() {
	var c float64
	fmt.Scan(&c)
	tampilHasil(c, celsiusToFahrenheit(c), celsiusToKelvin(c))
}
