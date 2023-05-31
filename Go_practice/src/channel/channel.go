package main

import "fmt"

func calcSquares(number int, squreOutput chan int) {

	sum := 0

	for number != 0 {

		digit := number % 10

		sum += digit

		number /= 10

		fmt.Println(sum)
	}

	squreOutput <- sum // Write sum variable to channel

}

func main() {

	number := 233

	squareOutput := make(chan int)

	go calcSquares(number, squareOutput)

	squareAnswer := <-squareOutput // receive the message from channel

	fmt.Println("Answer = ", squareAnswer)

}
