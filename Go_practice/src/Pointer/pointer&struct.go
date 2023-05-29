package main

import "fmt"

func main() {

	type Person struct {
		name string
		age  int
	}

	person1 := Person{"John", 25}

	var ptr *Person

	ptr = &person1

	fmt.Println(person1)

	fmt.Println(ptr)

	fmt.Println("Name:", ptr.name)

	fmt.Println("Age:", ptr.age)

}
