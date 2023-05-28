package main

import "fmt"

func main() {
	k := 30
	switch k {
	case 10:
		fmt.Println("was <= 10")

		fallthrough

	case 20:
		fmt.Println("was <= 20")

		fallthrough

	case 30:
		fmt.Println("was <= 30")

		//fallthrough

	case 40:
		fmt.Println("was <= 40")

		fallthrough

	case 50:
		fmt.Println("was <= 50")

		fallthrough
	case 60:
		fmt.Println("was <= 60")

		fallthrough

	default:
		fmt.Println("default case")
	}
}
