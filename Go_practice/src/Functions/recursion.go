package main

import "fmt"

func factorial(num int) int {

	if num == 0 {

		return 1

	} else {

		return num * factorial(num-1)
	}
}

func main() {

	var number = 10

	var result = factorial(number)

	fmt.Println("The factorial of ", number, "is", result)
}
