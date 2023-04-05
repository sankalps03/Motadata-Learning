<html>
<head>

    <title>Chat App</title>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">


</head>

<body>

<h1> Group Chat</h1>


<input id="username" placeholder="Your username">

<div id="output"> </div>

<input id="message" type="text">

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


    function setMyUserName(user){

        var json = {
            "userName": user,
            "type":"setUserName"
        };

        websocket.send(JSON.stringify(json));
    }

    function onMessage(event) {

        console.log(event);

        display(event.data);


    }

    websocket.onmessage = function (evnt) {
        onMessage(evnt);

    }

    function onOpen() {

        console.log("opened connection: " + wsURI);

        var myusername = prompt("Enter username for the chat");


        if (myusername != null){

            setMyUserName(myusername);

        }else {

            console.log("no username closing connection");

            websocket.close();
        }


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

        var data = JSON.parse(dataString);

        var contentMessage = "<p>User " + data.userName + " says: " + data.content + "</p>";

        document.getElementById("output").innerHTML += contentMessage + "</br>";

    }

    function send(){

        var message = document.getElementById("message").value;

        var username = document.getElementById("username").value;

        var json = {
            "content": message,
            "userName": username,
            "type": username.length > 0 ? "privateMessage" : "broadcast"
        };
        console.log("Sending " + message);

        websocket.send(JSON.stringify(json));
    }



</script>
</body>

</html>
