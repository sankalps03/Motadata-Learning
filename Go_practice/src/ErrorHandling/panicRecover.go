package main

func demo() {
	defer func() {

		recover()

		defer func() {

			recover() // this one recovers panic 2
		}()

		defer recover() // no-op

		panic(2)
	}()
	panic(1)
}

func main() {
	demo()
}
