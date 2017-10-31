<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="description" content="실시간 기업정보 서비스, 페이지, 정보공유" />
    
    <title> Klaiver 회원가입 | 로그인 </title>
    <link href="./resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='./resources/css/main.css' />

    <script src="./resources/js/jquery-1.11.2.js"></script>
<script>

$(document).ready(function(){
	var formObj = $("form[role='form']");
		console.log(formObj);
		$("#submit").click(function() {
			formObj.submit();
			alert("회원가입하셨습니다. 로그인하세요.");
		});
	$('#login_btn').click(login);
	$("#pw").keypress(function(e) {					
		if (e.which=='13'){
			if ($("#pw").val()!="") {
				login();
			}
			return false;
		}
	});
});

function login() {
	$.ajax({
		url:'./klogin/login',
		type:'POST',
		data:{
			email 		: $('#email').val(),
			password 	: $('#pw').val()
		},
		success:function(message){
			
			if(message == 'success'){
				alert('로그인');
				location.href="./home";
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
</head>
<body>
   <header>
        <div id="ci">
			<a href="./"><img src="./resources/images/ci.png"
				alt="Klaiver" /></a>
		</div>
        <div id="input_box">
            <input id="login_btn" type="button" title="로그인" value="로그인" />
            <span id="enter">
                <input type="password" id="pw" name="pw" placeholder="비밀번호" />
                <input type="email" id="email" name="email" placeholder="이메일" />
            </span>       
        </div>
    </header>
    <section id="container">
        <p id="side">SOCIAL NETWORKING SERVICE KLAIVER</p>

        <!------------==============================  회원가입 4단계  ==============================------------>
        <div id="Form_box">
            <p><img src="./resources/images/ci.png" alt="Klaiver"/></p>
            <p><strong>Klaiver 무료 회원가입</strong></p>
            <p>전 세계 기업의 실시간 정보를 만나보세요.</p>
            <form id="stage4" name="commonForm"role="form" method="post">
                <div class="info_box">
                    <p>확인을 누르시면 회원가입이 완료됩니다.</p>
                    <ul id="list_title">
                        <li>이름</li>
                        <li>아이디</li>
                    </ul>
                    <ul id="Input_information">
                        <li><span id="Span1">${jo.username}</span></li>
                        <li><span id="Span3">${jo.email}</span></li>
                    </ul>
                </div>
                <p><a href="#">가입하면 <strong>Klaiver</strong>의 <strong>약관</strong> 및 <strong>개인정보처리방침</strong>에 동의하게 됩니다.</a></p>
                <button class="previous" type="submit" onclick="history.go(-1);" >이전</button>
                <button class="save" type="submit" id="submit">확인</button>
            </form>
        </div>
    </section>
</body>
</html>