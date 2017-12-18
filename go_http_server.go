package main

import (
	"net/http"
	"fmt"
	"os/exec"
	)

func main() {
	http.HandleFunc("/", handler)
	http.ListenAndServe(":8082", nil)
	}

func handler(output http.ResponseWriter, input *http.Request) {

	instruction := "Instructed to " + input.URL.Path[1:] + "."

	fmt.Printf(instruction)

	if input.URL.Path[1:] == "wakeonlan" {
		fmt.Println("Broadcasting magic packets over the LAN.")
		cmd := exec.Command("./wol.sh")
		err := cmd.Start()

		if err != nil {
			fmt.Println("There was a problem executing the script.")
			}
		}

	if input.URL.Path[1:] == "shutdown" {

		fmt.Println("Shutting down the PC.")
		cmd := exec.Command("./shutdown_PC.sh")
		err := cmd.Start()		

		if err != nil {
			fmt.Println("There was a problem executing the Shutdown script.")
			}

		}

	if input.URL.Path[1:] == "startRecording" {

		fmt.Println("Starting the recording.")
		cmd := exec.Command("./startRecording.sh")
		err := cmd.Start()

		if err != nil {

			fmt.Println("There was a problem executing the start recording script.")
			}

		}

	}

