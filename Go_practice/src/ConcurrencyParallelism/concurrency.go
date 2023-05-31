package main

import (
	"fmt"
	"log"
	"os"
	"runtime"
	"runtime/pprof"
	"sync"
	"time"
)

var wg sync.WaitGroup

var threadProfile = pprof.Lookup("threadcreate")

func concurrencyInAction() {

	defer wg.Done()

	fmt.Println("go id :", getGoroutineID(), "pid : ", os.Getpid())

	time.Sleep(time.Second * 20)

	//for i := 0; i < 5; i++ {
	//
	//	for j := 0; j < 100e6; j++ {
	//
	//	}
	//}

}

func getGoroutineID() int {

	var buf [64]byte

	n := runtime.Stack(buf[:], false)

	id := -1

	fmt.Sscanf(string(buf[:n]), "goroutine %d", &id)

	return id
}

func main() {
	count := 10

	wg.Add(count)

	log.Println("Before thread count : ", threadProfile.Count())

	for i := 0; i < count; i++ {

		go concurrencyInAction()
	}

	wg.Wait()

	log.Println("After thread count : ", threadProfile.Count())
}
