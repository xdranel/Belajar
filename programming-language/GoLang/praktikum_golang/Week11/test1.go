package main

import "fmt"

const maxArr = 1234

type arrStr [maxArr]string

func SeqSearch1(T arrStr, n int, X string) bool {
	found := false
	j := 0
	for j < n && !found {
		found = T[j] == X
		j = j + 1
	}
	return found
}

func SeqSearch2(T arrStr, n int, X string) (bool, int) {
	found := false
	idx := -1
	j := 0
	for j < n && !found {
		if T[j] == X {
			found = true
			idx = j
			break
		}
		j++
	}
	return found, idx
}

func SeqSearch3(T arrStr, n int, X string) int {
	for j := 0; j < n; j++ {
		if T[j] == X {
			return j
		}
	}
	return -1
}

func SeqSearch4(T arrStr, X string) int {
	for j, v := range T {
		if v == X {
			return j
		}
	}
	return -1
}

func main() {
	var data arrStr
	var n int

	fmt.Println("Masukan Kata: ")
	for {
		var input string
		fmt.Scan(&input)
		if input == "STOP" {
			break
		}
		data[n] = input
		n++
	}

	var search string
	fmt.Print("Kata Yang Dicari: ")
	fmt.Scan(&search)
	resultStr, resultInt := SeqSearch1(data, n, search)
	// resultStr, resultInt := SeqSearch4(data, search)

	if resultStr {
		fmt.Printf("Kata '%s' ditemukan pada index: %d\n", data[resultInt], resultInt)
	} else {
		fmt.Println("Kata Tidak Ditemukan, index terakhir: ", resultInt)
	}
}
