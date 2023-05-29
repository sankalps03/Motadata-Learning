package main

import (
	"fmt"
	"strings"
)

func main() {

	string1 := "Programiz"

	string2 := "Programiz Pro"

	string3 := "Programiz"

	fmt.Println(strings.Compare(string1, string2))

	fmt.Println(strings.Compare(string2, string3))

	fmt.Println(strings.Compare(string1, string3))

	result := strings.Contains(string2, "substring2")

	fmt.Println(result)

	replacedText := strings.Replace(string1, "r", "t", 3)

	fmt.Println("New String:", replacedText)

	var message = "I Love Golang"

	splittedString := strings.Split(message, " ")

	fmt.Println(splittedString)

	result1 := string1 == string3

	fmt.Println(result1)

}
