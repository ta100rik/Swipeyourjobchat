const socket = new WebSocket('wss://api.swipeyourjob.nl:8080/chat');
https://api.swipeyourjob.nl:8080/
// Connection opened
socket.addEventListener('open', function (event) {
    socket.send('Hello asd!');
});

// Listen for messages
socket.addEventListener('message', function (event) {
    console.log('Message from server ', event.data);

    $(`#greetings`).append(`<tr><td>${event.data}</td></tr>`);
});

function sendmessage(message){
	socket.send(message);
}

// var stompClient = null;

// function setConnected(connected) {
//     $("#connect").prop("disabled", connected);
//     $("#disconnect").prop("disabled", !connected);
//     if (connected) {
//         $("#conversation").show();
//     }
//     else {
//         $("#conversation").hide();
//     }
//     $("#greetings").html("");
// }

// function connect() {
//     var socket = new SockJS('http://api.swipeyourjob.nl:8080/chat');
//     stompClient = Stomp.over(socket);
//     stompClient.connect({}, function (frame) {
//         setConnected(true);
//         console.log('Connected: ' + frame);
//         stompClient.subscribe('http://api.swipeyourjob.nl:8080/chat', function (greeting) {
//             showGreeting(JSON.parse(greeting.body).content);
//         });
//     });
// }

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     setConnected(false);
//     console.log("Disconnected");
// }

// function sendName() {
//     stompClient.send("http://localhost:8080/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
// }

// function showGreeting(message) {
//     $("#greetings").append("<tr><td>" + message + "</td></tr>");
// }

// $(function () {
//     $("form").on('submit', function (e) {
//         e.preventDefault();
//     });
//     $( "#connect" ).click(function() { connect(); });
//     $( "#disconnect" ).click(function() { disconnect(); });
//     $( "#send" ).click(function() { sendName(); });
// });

