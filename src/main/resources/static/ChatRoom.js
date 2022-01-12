let chatMessage = {
    sender : "",
    message : "",
    type : ""
};
let c_data;

let sendMsg= document.getElementById('sendMsg');
let sendBtn = document.getElementById("sendBtn");


window.onload = function() {


    let socket = new SockJS("/ws/chat");
    let stompClient = Stomp.over(socket);


	
    stompClient.connect({}, onConnected, connectError);

    sendMsg.addEventListener('keydown',function(event){
        if(event.keyCode==13){
            sendMessage(sendMsg.value);
        }
    })
    //onKeyDown(keycode값),  onKeyPress(ASCII값) :  키를누르면 이벤트발생후 문자 입력, onKeyUp : 키를 누르면 문자입력후 이벤트발생
    //keydown : 누르는 순간 발생 keyup : 눌렀다 떼는 순간 발생
    sendBtn.addEventListener('click',function(event){
        console.log(sendMsg.value);
        sendMessage(sendMsg.value);
    })

    function onConnected() {
        stompClient.subscribe('/topic/', responseMessage); // subscribe 파라미터 : 1. 구독할 토픽 url 2. 콜백메소드
        chatMessage.sender = sender;
        chatMessage.message = " 님이 입장하였습니다.";
        chatMessage.type = "Enter";
        let cmsg= JSON.stringify(chatMessage);


        stompClient.send("/app/chat.send",{"Type" : "Enter"},cmsg);// send 파라미터 : 1. 매핑url, 2. header정보(content-type 등) 3. 보낼 message
        console.log("보냄");
    }

    function sendMessage(msg){
        chatMessage.sender =sender;
        chatMessage.message = msg;
        chatMessage.type = "Chat";
        let cmsg= JSON.stringify(chatMessage);

        stompClient.send("/app/chat.send", {"Type" : "Send"}, cmsg);
        console.log("보냄");

    }


    function connectError() {
        alert("오류");
    }
    function disConnected() {
        console.log("연결이 종료되었습니다.");
    }
    function responseMessage(response) {

        let addSender=document.createElement("td");
        let addMsg =document.createElement("td");
        let parentTbody = document.getElementById("msg_box");
        let addBox=document.createElement("tr");
        let data=JSON.parse(response.body);
        if(data.type=="Enter"){
            //스타일 변경 중앙정렬
        }
        if(data.sender==sender){
            console.log("me");
            //스타일 변경
        }
        addSender.innerHTML = data.sender;
        addMsg.innerHTML = data.message;
        parentTbody.appendChild(addBox);
        parentTbody.appendChild(addSender);
        parentTbody.appendChild(addMsg);

        console.log("수신");
    }
}