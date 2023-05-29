package main

import "fmt"

type Hobby interface {
	myStereotype() string
}

type Human struct {
}

func (h Human) myStereotype() string {

	return "I'm a Human, only an abstract concept, and I can have no hobby."
}

type Man struct {
	Human
}

func (m Man) myStereotype() string {

	return "I'm a Man and I'm going fishing."
}

type Woman struct {
	Human
}

func (m Woman) myStereotype() string {

	return "I'm a Woman and I'm going shopping."
}

type Dog struct {
}

func (m Dog) myStereotype() string {

	return "bow bow bow, I'm chasing sticks."
}

func main() {
	h := new(Human)

	m := new(Man)

	w := new(Woman)

	d := new(Dog)

	hobbyArr := [...]Hobby{h, m, w, d}

	for n, _ := range hobbyArr {

		fmt.Println("My hobby?  Well,", hobbyArr[n].myStereotype())

	}
}
