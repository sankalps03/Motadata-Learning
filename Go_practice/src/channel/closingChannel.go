package main

import "fmt"

func producer(channel chan int) {

	for i := 0; i < 10; i++ {

		channel <- i
	}
	close(channel)
}

func main() {

	channel := make(chan int)

	go producer(channel)

	for {
		v, ok := <-channel

		if ok == false {
			break
		}

		fmt.Println("Received : ", v, ok)
	}

}
