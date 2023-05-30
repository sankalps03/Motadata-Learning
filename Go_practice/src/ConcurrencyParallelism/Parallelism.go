package main

import (
	"fmt"
	"log"
	"runtime"
	"runtime/pprof"
	"sync"
	"time"
)

var Wg sync.WaitGroup

var ThreadProfile = pprof.Lookup("threadcreate")

func parallelismInAction() {

	runtime.LockOSThread()

	defer Wg.Done()

	fmt.Println("go id :", getGoroutineId())

	time.Sleep(time.Second * 2)

	for i := 0; i < 5; i++ {

		for j := 0; j < 100e6; j++ {

		}
	}

	time.Sleep(time.Second * 2)

	runtime.UnlockOSThread()
}

func getGoroutineId() int {
	var buf [64]byte
	n := runtime.Stack(buf[:], false)
	id := -1
	fmt.Sscanf(string(buf[:n]), "goroutine %d", &id)
	return id
}

func main() {
	count := 10

	Wg.Add(count)

	log.Println("Before thread count : ", ThreadProfile.Count())

	for i := 0; i < count; i++ {

		go parallelismInAction()

	}

	Wg.Wait()

	log.Println("After thread count : ", ThreadProfile.Count())
}
