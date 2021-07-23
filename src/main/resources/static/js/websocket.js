/** 웹 소켓 전역 객체 */
var stompClient = null;

/**
 * 해당 클라이언트 연결 or 연결 해제
 * @param boolean connected
 */
function setConnected(connected) {
    if (connected) {
        // TODO 연결할 ajax 하자
    } else {
        // TODO 연결해제 할 ajax 하자
    }
}

/**
 * 연결 해제
 */
function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

/**
 * 연결 시도 함수
 * @param callback 콜백 함수
 */
function connect(callback) {
    var socket = new SockJS('/devwebTalk');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        // 연결 성공
        setConnected(true);
        console.log('Connected: ' + frame);

        if(callback instanceof Function) {
            callback();
        }
    });
}

function connectSuccess() {
    stompClient.subscribe('/topic/public', function (data) {
        console.log('[subscribe] : ' + data);
        // TODO 연결 후 콜백
        showGreeting(JSON.parse(data.body).content);
    });
}







