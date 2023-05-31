package main

import (
	"fmt"
	"sync"
	"time"
)

func blockingFunction(channel chan int, wg *sync.WaitGroup) {

	ans := <-channel

	fmt.Println(ans)

	wg.Done()
}

func main() {

	wg := sync.WaitGroup{}

	ch := make(chan int, 2)

	wg.Add(1)

	go blockingFunction(ch, &wg)

	wg.Wait()

	time.Sleep(time.Second * 3)

	ch <- 100

}
