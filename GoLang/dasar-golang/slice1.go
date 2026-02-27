package main

import "fmt"

func main() {
	names := [...]string{
		"John", "Jane", "Jake", "Daniel", "Felix", "Joko",
	}

	slice1 := names[3:6]
	fmt.Println(slice1)

	slice2 := names[:2]
	fmt.Println(slice2)

	slice3 := names[2:]
	fmt.Println(slice3)

	var slice4 []string = names[:]
	fmt.Println(slice4)

	days := [...]string{"Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu", "Minggu"}

	daySlice1 := days[5:] // Sabtu , Minggu
	fmt.Println(daySlice1)

	daySlice1[0] = "Sabtu Baru"
	fmt.Println(daySlice1)
	daySlice1[1] = "Minggu Baru"
	fmt.Println(days)

	daysSLice2 := append(daySlice1, "Libur Baru")
	daysSLice2[0] = "Sabtu Lama"
	// daysBaru := [...]string{"Sabtu Baru", "Minggu Baru", "Libur Baru"}
	fmt.Println(daySlice1)
	fmt.Println(daysSLice2)
	fmt.Println(days)

	var newSlice []string = make([]string, 2, 5)
	newSlice[0] = "Joko"
	newSlice[1] = "Jono"
	// newSlice[2] = "Prastyo" // Error, Harus Menggunakan Append

	fmt.Println(newSlice)
	fmt.Println(len(newSlice))
	fmt.Println(cap(newSlice))

	newSlice2 := append(newSlice, "Elang", "Dani", "Farid", "Felix")
	fmt.Println(newSlice2)
	fmt.Println(len(newSlice2))
	fmt.Println(cap(newSlice2))

	newSlice2[0] = "Budi"
	fmt.Println(newSlice2)
	fmt.Println(newSlice)

	fromSlice := days[:]
	toSlice := make([]string, len(fromSlice), cap(fromSlice))

	copy(toSlice, fromSlice)

	fmt.Println(fromSlice)
	fmt.Println(toSlice)

	iniArray := [...]int{1, 2, 3, 4, 5}
	iniSlice := []int{1, 2, 3, 4, 5}

	fmt.Println(iniArray)
	fmt.Println(iniSlice)
}
