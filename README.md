# SmartThings Client in Go

The bones of a client that parses GET requests sent from a Groovy server residing on my SmartThings hub.

## Usage: Server

* Included is an example of what's required for sending GET requests on the LAN. Requests are sent to raspPi, a custom device handler, which can then execute any scripts Linux is capable of. 
* Examples included in the file (under myPCHandler) will send magic packets over the LAN to wake up an ethernet-connected PC, or send a shutdown request to an EventGhost server running on a Windows PC.
* Additional examples include triggering the recording function on a WiFi camera (the appropriate GET request was observed while running the web client and inspecting the network tab), then shipping off a GET request to the RPi. An RTSP stream is established using a headless instance of VLC, and user-directed (voice/motion-activated) recording can thus be implemented.
* Integration with the Amazon Echo was achieved by tying virtual switches to the Alexa/SmartThings smartapp, then running specified routines based on switch state. 
