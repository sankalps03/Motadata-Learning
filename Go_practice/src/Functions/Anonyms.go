package main

import "fmt"

var sum = 0

func findSquare(num int) int {

	square := num * num

	return square
}

func main() {

	sum := func(number1 int, number2 int) int {

		return number1 + number2
	}

	result := findSquare(sum(6, 9))

	fmt.Println("Result is:", result)

}
