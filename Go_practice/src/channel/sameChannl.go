package main

import (
	"fmt"
	"time"
)

func main() {
	c := make(chan int)

	go func() {
		c <- 1
		c <- 2

	}()

	go func() {
		c <- 3
		c <- 4

	}()

	time.Sleep(time.Second)

	for i := 0; i < 4; i++ {
		select {
		case s1 := <-c:
			fmt.Println("I am in case S1 ", s1)
		case s2 := <-c:
			fmt.Println("I am in case S2", s2)
		}
	}
}
