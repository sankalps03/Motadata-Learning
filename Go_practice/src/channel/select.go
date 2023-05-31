package main

import "fmt"

func server1(channel chan string) {
	//time.Sleep(time.Second * 2)
	channel <- "From server 1"

}

func server2(channel chan string) {
	//time.Sleep(time.Second * 1)
	channel <- "From Server 2"
}

func main() {

	channel1 := make(chan string)

	channel2 := make(chan string)

	go server1(channel1)

	go server2(channel2)

	select {
	case s1 := <-channel1:
		fmt.Println(s1)

	case s2 := <-channel2:
		fmt.Println(s2)

		//default:
		//	fmt.Println("No data received")
	}

}
