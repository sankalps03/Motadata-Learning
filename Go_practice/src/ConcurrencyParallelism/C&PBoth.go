package main

import (
	"fmt"
	"runtime"
	"strconv"
	"sync"
	"time"
)

func printTime(msg string) {
	fmt.Println(msg, " |", time.Now().Format("15:04:05"))
}

func writeMail(wg *sync.WaitGroup, count int) {

	var goId = "go id : " + strconv.Itoa(getGoroutineid())

	printTime("Done writing mail #" + strconv.Itoa(count) + "  " + goId)

	wg.Done()
}

func listenForever() {
	for {
		printTime("Listening...")
	}
}

func getGoroutineid() int {

	var buf [64]byte

	n := runtime.Stack(buf[:], false)

	id := -1

	fmt.Sscanf(string(buf[:n]), "goroutine %d", &id)

	return id
}

func main() {
	var waitGroup sync.WaitGroup

	count := 100

	waitGroup.Add(count)

	go listenForever()

	time.Sleep(time.Nanosecond * 10)

	for i := 0; i < count; i++ {

		go writeMail(&waitGroup, i)

	}

	waitGroup.Wait()
}
