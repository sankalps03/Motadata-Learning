package main

import "fmt"

func main() {

	i := 1

	for i <= 3 {

		println(i)

		i++
	}

	for j := 1; j <= 9; j++ {

		if j%2 == 0 {

			continue

		} else if j == 7 {

			break

		}

		println(j)

	}

	nums := []int{2, 3, 4}

	sum := 0

	for _, value := range nums { // "_ " is to ignore the index
		sum += value
	}
	fmt.Println("sum:", sum)

}
