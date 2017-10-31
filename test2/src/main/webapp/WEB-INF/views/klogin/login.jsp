<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- <form action='./login' method='post'> -->
		로그인<br/>
		이메일 : <input type="text" id='email' name='email'><br/>
		패스워드 : <input type="password" id='password' name='password'><br/>
		<br/>
		<input type="button" id="buttonLogin" name="buttonLogin" value='로그인'>	
		&nbsp;&nbsp;
		<input type="button" onClick="location.href='../signup/namepage';" value="회원가입">
	<!-- </form> -->
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>

$(document).ready(function(){
	$('#buttonLogin').click(login);
	$("#password").keypress(function(e) {					
		if (e.which=='13'){
			if ($("#password").val()!="") {
				login();
			}
			return false;
		}
	});
});

function login() {
	$.ajax({
		url:'./login',
		type:'POST',
		data:{
			email 		: $('#email').val(),
			password 	: $('#password').val()
		},
		success:function(message){
			
			if(message == 'success'){
				location.href="../";
			}
			else if(message == 'fault'){
				alert('등록되지 않은 이메일이거나 패스워드가 맞지 않습니다');
			}
		},
		error:function(){
			alert('error');
		}
	});
}
</script>
</html>