package main

import "fmt"

func main() {

	var name string

	fmt.Print("Enter your name: ")
	fmt.Scan(&name)

	fmt.Printf("Name: %s", name)

}