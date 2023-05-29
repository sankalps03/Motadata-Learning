package main

import "fmt"

func main() {

	var array1 = [5]int{1, 5, 8, 0, 3}

	fmt.Println(array1)

	var array2 = [...]string{"Hello", "programming"}

	fmt.Println(array2, array2[0])

	array3 := [5]int{0: 7, 3: 9}

	fmt.Println(array3)

	for index, item := range array1 {

		fmt.Printf("numbers[%d] = %d \n", index, item)
	}

}
