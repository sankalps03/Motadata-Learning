package main

import (
	"fmt"
)

func main() {

	numbers := []int{1, 2, 3, 4, 5}

	set2 := []int{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}

	fmt.Println(numbers)

	newnumber := append(numbers, 6, 7, 8)

	fmt.Println(newnumber)

	fmt.Println(numbers)

	anothernew := append(numbers, newnumber...)

	fmt.Println(anothernew)

	copy(numbers, set2)

	fmt.Println(numbers)

	for i := 0; i < len(anothernew); i++ {

		fmt.Println(anothernew[i])

	}

}
