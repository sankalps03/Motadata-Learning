package main

import "fmt"

func first() {
	fmt.Println("I am in First")
}

func Second() {
	fmt.Println(" I am in Second")
}

func Third() {
	fmt.Println("I am in Third")
}

func output() {
	for i := 0; i < 5; i++ {
		defer fmt.Printf("%d\n", i)
	}
}

func main() {
	// LIFO property
	defer Third()

	first()
	//output()
	defer Second()

	output()

	panic("Some Panic occured")
}
