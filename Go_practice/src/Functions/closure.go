package main

import "fmt"

func greet() func() string {

	name := "John"

	return func() string {

		name = "Hi " + name

		return name
	}
}

func main() {

	message := greet()

	fmt.Println(message())

}
