package main

import "fmt"

func callByValue(num int) {

	num = 30
	fmt.Println(num)

}

func callByReference(num *int) {

	*num = 10
	fmt.Println(*num)

}

func main() {

	var number int

	callByValue(number)

	callByReference(&number)

}
