package main

import "fmt"

func displayValue(i ...interface{}) {

	fmt.Println(i)
}

func main() {

	a := "Welcome to Programiz"
	b := 20
	c := false

	displayValue(a)

	displayValue(b)

	displayValue(c)

	displayValue(a, b)

	displayValue(a, b, c)

}
