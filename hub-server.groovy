//=============================================================================================
//                                                                              ST Go Client
//
//  Committed as a reference for sending out GET requests on the LAN to a custom device
//  handler. raspPi is the handler, defined in another file.
//=============================================================================================    
preferences {
  page(
        name:       "pageSeven",
        title:      "IP Address & Port",
        install:    true,
        uninstall:  true) {
    
    section {
        image       "http://image005.flaticon.com/1/png/512/3/3751.png"
        paragraph   "Input the IPv4 address of the Raspberry Pi running the Go server by which the WOL routine is exectued."
        input name: "ipAddr",
        title:      "IP Address?",
        type:       "string",
        multiple:   false,
        required:   false
        
        paragraph   "This is the Raspberry Pi device type."
        input       "raspPi", "capability.Sensor",
        multiple:   false,
        required:   true,
        title:      "Which device?"
        
        
        paragraph   "Finally, input the port number on which the Go server receives requests."
        input name: "portNum",
        title:      "Port number?",
        type:       "string",
        multiple:   false,
        required:   false
        } }

//--------------------------------------------------------------------------------- INITIALIZE
def initialize() {
    def ipPort              =   "$ipAddr" + ":" + "$portNum"
    state.raspPi            =   ipPort
    }

//=============================================================================================
//                                                                              PC_HANDLER()
//
// An example of sending out GET requests on the LAN
//============================================================================================= 
def myPCHandler(evt) {

    if (evt.value == "on") {
        
        pcSwitch.on()
        sendHubCommand(new physicalgraph.device.HubAction(
            method:     "GET",
            path:       "/wakeonlan",
            headers:    [
            HOST:       state.raspPi
            ]))
        } 
            
    else if (evt.value == "off") {
        
        pcSwitch.off()
        
        sendHubCommand(new physicalgraph.device.HubAction(
            method:     "GET",
            path:       "/shutdown",
            headers:    [
            HOST:       state.raspPi
            ]))
        }
    }
