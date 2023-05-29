package main

import "fmt"

func main() {

	subjectMarks := map[string]float32{"Java": 80, "Python": 81, "Golang": 85}

	fmt.Println("Marks: ", subjectMarks)

	subjectMarks["Java"] = 88

	subjectMarks["C"] = 80

	fmt.Println("Updated Marks: ", subjectMarks)

	delete(subjectMarks, "C")

	fmt.Println("Updated:", subjectMarks)

	fmt.Println("Marks obtained:")

	for subject, marks := range subjectMarks {

		fmt.Println(subject, ":", marks)
	}

	fmt.Println("Subjects:")

	for subject := range subjectMarks {

		fmt.Println(subject)
	}

}
