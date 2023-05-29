package main

import "fmt"

func main() {

	var num int

	var ptr *int

	fmt.Println("Value of pointer:", ptr)

	num = 22

	fmt.Println("Address of num:", &num)

	fmt.Println("Value of num:", num)

	ptr = &num

	fmt.Println("\nAddress of pointer ptr:", ptr)

	fmt.Println("Content of pointer ptr:", *ptr)

	num = 11

	fmt.Println("\nAddress of pointer ptr:", ptr)

	fmt.Println("Content of pointer ptr:", *ptr)

	*ptr = 2

	fmt.Println("\nAddress of num:", &num)

	fmt.Println("Value of num:", num)

	var ptrr = new(int)

	*ptrr = 20

	fmt.Println("ptrr", ptr)

	fmt.Println("ptrr", *ptr)

	x := 101
	y := 101

	ptr1 := &x

	ptr2 := &y

	ptr3 := &x

	fmt.Println(ptr2 == ptr1)

	fmt.Println(ptr3 == ptr1)

	fmt.Println(*ptr2 == *ptr1)

}
