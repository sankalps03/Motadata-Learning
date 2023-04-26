<html>
<head>

    <title>Long Ping</title>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>

<body>

<h1> Long Ping</h1>


<div id="output"> </div>

<input id="ipAddress" type="text">

<button onclick="send()">Send</button>

<script>


    var wsURI = "wss://" + document.location.host + document.location.pathname + "chat";


    if (window.location.protocol == 'http:'){

        wsURI = "ws://" + document.location.host + document.location.pathname + "chat";

    }
    if(window.location.protocol == 'https:') {

        wsURI = "wss://" + document.location.host + document.location.pathname + "chat";

    }

    var websocket = new WebSocket(wsURI);


    function onMessage(event) {

        if(event.data == "SEND"){

            var sessionName = window.localStorage.getItem("name");

            if(sessionName != null){

                websocket.send(sessionName)
            }

        }

        console.log(event);

        display(event.data);


    }

    websocket.onmessage = function (evnt) {
        onMessage(evnt);

    }

    function onOpen() {

        console.log("opened connection: " + wsURI);




    }

    websocket.onopen = function () {

        onOpen();

    }

    function onClose() {

        console.log("closed connection: " + wsURI)
    }

    websocket.onclose = function () {

        onClose();

    }

    function display(dataString) {


        document.getElementById("output").innerHTML += dataString + "</br>";

    }

    function send(){

        var message = document.getElementById("ipAddress").value;

        console.log("Sending " + message);

        window.localStorage.setItem("name",message)

        websocket.send(message);

        $.ajax({
            type:'POST',
            url:'http://localhost:8080/ping',
            data:{ipAddress : message},
            success:function(response)
            {

            }

        });
        display("pinging: " + message);


    }



</script>
</body>

</html>
