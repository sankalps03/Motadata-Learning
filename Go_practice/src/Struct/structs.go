package main

import "fmt"

func main() {

	type Person struct {
		name string
		age  int
	}

	person1 := Person{"John", 25}

	fmt.Println(person1)

	var person2 Person

	person2 = Person{

		age: 29,

		name: "Sara",
	}

	fmt.Println(person2)

	fmt.Println(person2.name)

}
