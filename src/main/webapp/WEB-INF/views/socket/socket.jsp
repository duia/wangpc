<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>webSocket测试</title>
</head>
<body>
<div id="content" class="content">
	<div class="panel panel-default">
		<div class="panel-body">
			<div class="page-header" id="tou">
	    	webSocket及时聊天Demo程序
			</div>
			<div class="well" id="msg">
			</div>
			<div class="col-lg">
				<div class="input-group">
			    	<input type="text" class="form-control" placeholder="发送信息..." id="message">
			      	<span class="input-group-btn">
			        	<button class="btn btn-default" type="button" id="send" >发送</button>
			      	</span>
			    </div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
		</div>
	</div>
	<div>
		<div>
			<button id="connect" onclick="connect();">Connect</button>
			<button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
		</div>
		<div id="conversationDiv">
			<p>
				<label>notice content?</label>
			</p>
			<p>
				<textarea id="name" rows="5"></textarea>
			</p>
			<button id="sendName" onclick="sendName();">Send</button>
			<button id="sendName2" onclick="sendName2();">Send2</button>
			<p id="response"></p>
		</div>
	</div>
</div>
<script src="/static/js/common/sockjs-1.1.1.min.js"></script>
<script src="/static/js/common/stomp.js"></script>
<script type="text/javascript" src="/static/js/jquery-2.1.3.min.js"></script>
<script type="text/javascript">

     var stompClient = null;
     function setConnected(connected) {
         document.getElementById('connect').disabled = connected;
         document.getElementById('disconnect').disabled = !connected;
         document.getElementById('conversationDiv').style.visibility = connected ? 'visible' : 'hidden';
         document.getElementById('response').innerHTML = '';
     }
     // 开启socket连接
     function connect() {
         var socket = new SockJS('/socket');
         stompClient = Stomp.over(socket);
         stompClient.connect({}, function (frame) {
             setConnected(true);
             console.log('notice socket connected!');
             stompClient.subscribe('/topic/notice', function (data) {
                 $('#msg').append(data.body+'<br>');
             });
             /* stompClient.subscribe('/user/topic/notice', function (data) {
                 $('#msg').append(data.body+'<br>');
                 $.gritter.add({
                     title: data.body,
                     image: '${imageServicePath}/'+'${sessionScope.login_user.smallImg}',
                     sticky: false,
                     time: 5000,
                     speed:800,
                     class_name: 'my-sticky-class'
                 });
             }); */
         });
     }
     // 断开socket连接
     function disconnect() {
         if (stompClient != null) {
             stompClient.disconnect();
         }
         setConnected(false);
         console.log("Disconnected");
     }
     // 向‘/app/change-notice’服务端发送消息
     function sendName() {
         var value = document.getElementById('name').value;
         stompClient.send("/app/change-notice", {}, value);
     }
     function sendName2() {
         var value = document.getElementById('name').value;
         stompClient.send("/app/user-change-notice", {}, value);
     }
     connect();

</script>
</body>
</html>	