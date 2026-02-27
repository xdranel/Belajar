package main

import "fmt"

func soal1() {
	const correctUsername = "Admin"
	const correctPassword = "Admin"

	var username, password string
	failAttempts := 0

	for {
		fmt.Print("Masukkan username: ")
		fmt.Scanln(&username)
		fmt.Print("Masukkan password: ")
		fmt.Scanln(&password)

		if username == correctUsername && password == correctPassword {
			break
		} else {
			failAttempts++
		}
	}

	fmt.Printf("%d percobaan gagal login\n", failAttempts)
}

func soal2() {
	var number int

	fmt.Print("Masukkan bilangan bulat positif: ")
	fmt.Scanln(&number)

	if number <= 0 {
		fmt.Println("Input harus bilangan bulat positif.")
		return
	}

	for number > 0 {
		digit := number % 10

		fmt.Println(digit)

		number /= 10
	}
}

func soal3() {
	var x, y int

	fmt.Print("Masukkan bilangan pertama (x): ")
	fmt.Scanln(&x)
	fmt.Print("Masukkan bilangan kedua (y): ")
	fmt.Scanln(&y)

	if x < y {
		fmt.Println("x harus lebih besar atau sama dengan y.")
		return
	}

	quotient := 0

	for x >= y {
		x -= y
		quotient++
	}

	fmt.Println("Hasil dari x div y adalah:", quotient)
}

func main() {
	// soal1()
	// soal2()
	// soal3()
}
