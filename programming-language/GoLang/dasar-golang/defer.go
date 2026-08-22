package main

import "fmt"

func logging() {
	fmt.Println("Selesai Memangil Function")
}

func runApplication() {
	defer logging()
	fmt.Println("Run Application")
}

func main() {
	runApplication()
}
