package main

import "fmt"

type Task struct {
	ID   int
	Name string
}

func main() {
	// 1. Initialize a slice with make
	// We expect to have about 5 tasks, so we set capacity to 5.
	// Length is 0 because we haven't added tasks yet.
	todoList := make([]Task, 0, 5)

	// 2. Adding tasks using append
	todoList = append(todoList, Task{ID: 1, Name: "Learn Go Slices"})
	todoList = append(todoList, Task{ID: 2, Name: "Fix Memory Leaks"})
	todoList = append(todoList, Task{ID: 3, Name: "Build a Web Server"})

	fmt.Printf("Initial List: %v | Len: %d | Cap: %d\n", todoList, len(todoList), cap(todoList))

	// 3. Deleting an item (index 1: "Fix Memory Leaks")
	// This is a common Go pattern to remove an element from a slice
	todoList = append(todoList[:1], todoList[2:]...)

	fmt.Printf("After Deletion: %v | Len: %d | Cap: %d\n", todoList, len(todoList), cap(todoList))
}
