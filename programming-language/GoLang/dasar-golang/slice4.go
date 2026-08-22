package main

import "fmt"

var SmallData []byte

func leakMemory() {
	// Imagine this is a huge 1GB file loaded into memory
	massiveData := make([]byte, 1000000000)

	// We only want the first 5 bytes.
	// This 'SmallData' slice now points to the 1GB array!
	SmallData = massiveData[:5]

	// massiveData goes out of scope here...
	// BUT the 1GB array is NOT garbage collected because
	// SmallData is still pointing to it.
}

func fixLeak() {
	massiveData := make([]byte, 1000000000)

	// 1. Create a new independent slice
	SmallData = make([]byte, 5)

	// 2. Copy the data into the new memory
	copy(SmallData, massiveData[:5])

	// Now, the 1GB array has no pointers to it and can be deleted!
}

func main() {
	// leakMemory()
	// fixLeak()
	fmt.Println("SmallData length:", len(SmallData))
	// Even though len is 5, 1GB of RAM is still being held!
}
