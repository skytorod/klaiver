<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<%  
    response.setHeader("Cache-Control","no-store");  
    response.setHeader("Pragma","no-cache");  
    response.setDateHeader("Expires",0);  
 
    if (request.getProtocol().equals("HTTP/1.1")) {
        response.setHeader("Cache-Control", "no-cache");
    }
%>
<head>
    <meta charset="utf-8" />    
	<meta name="naver-site-verification" content="cd4b9b6e077b4ce24b39fe41469087c945900c46"/>    
    <meta name="description" content="실시간 기업정보 서비스, 페이지, 정보공유" />
    <title>Klaiver 회원가입 | 로그인 | 전체 검색</title>
    <link href="./resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='./resources/css/main.css' />

    <script src="./resources/js/jquery-1.11.2.js"></script>
<script>

$(document).ready(function(){
	    
	$('#login_btn').click(login);
	$("#pw").keypress(function(e) {					
		if (e.which=='13'){
			if ($("#pw").val()!="") {
				login();
			}
			return false;
		}
	});
	$("#search").keypress(
            function(e) {
               if (e.which == '13') {
                  if ($("#search").val() != "") {
                     var str = $("#search").val();
                     var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
             	     if(regExp.test(str)){
             	    	 var keyword = str.replace(regExp, "");
             	    	document.location.href = "./unsearch/alllist?keyword="
                            + keyword;
             	    }else{
             	    	document.location.href = "./unsearch/alllist?keyword="
                            + str;
             	    }
                     
                  } else {
                     alert("검색어를 입력하세요");

                  }
                  return false;
               }
            });
         $("#search_btn").click(
            function() {
               if ($("#search").val() != "") {
                  var keyword = $("#search").val();
                  var str = $("#search").val();
                  var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
          	     if(regExp.test(str)){
          	    	 var keyword = str.replace(regExp, "");
          	    	document.location.href = "./unsearch/alllist?keyword="
                         + keyword;
          	    }else{
          	    	document.location.href = "./unsearch/alllist?keyword="
                         + str;
          	    }
                  return false;
               } else {
                  alert("검색어를 입력하세요");
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
<script type="text/javascript">
//비번 확인
$(function() {
	$('#join_btn').click(function() {
		var password=$('#new_pw').val();
		var checkpass=$('#confirm_pw').val();
		var email = $('#mail').val();
		var checkemail=$('#confirm_mail').val();
		if(password!=password || email!=checkemail){
			alert("입력 정보를 확인해주세요.");
			return false;
		}else if(password == '' || checkpass== ''){
			alert("비밀번호 또는 비밀번호 확인을 해주세요");
			return false;
		}else if(email == '' || checkemail== ''){
			alert("이메일 또는 이메일 확인을 해주세요");
			return false;
		}else{
			$("#stage1").submit();
		}
	});
	$('#new_pw').keyup(function() {
		$('font[name=check]').text('');
	}); //#user_pass.keyup

	$('#confirm_pw').keyup(function() {
		if ($('#new_pw').val() != $('#confirm_pw').val()) {
			$('font[name=check]').text('');
			$('font[name=check]').html("중복확인해주세요");
		} else {
			$('font[name=check]').text('');
			$('font[name=check]').html("확인되었습니다");
		}
	}); //#chpass.keyup
	$('#confirm_mail').keyup(function() {
		if ($('#mail').val() != $('#confirm_mail').val()) {
			$('font[name=mailcheck]').text('');
			$('font[name=mailcheck]').html("메일확인해주세요");
		} else {
			$('font[name=mailcheck]').text('');
			$('font[name=mailcheck]').html("확인되었습니다");
		}
	}); //#chpass.keyup
	
});

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
        <form id="Integrated_search" name="Integrated_search" method="get" action="#">
            <input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군" />
            <input type="image" id="search_btn" src="./resources/images/search_btn.png" alt="검색"/>
        </form>
        

        <!------------==============================  회원가입 1단계  ==============================------------>
        
        <div id="Form_box">
            <p><img src="./resources/images/ci.png" alt="Klaiver"/></p>
            <p><strong>Klaiver 무료 회원가입</strong></p>
            <p>전 세계 기업의 실시간 정보를 만나보세요.</p>
            <form id="stage1" name="commonForm" method="post">
                <div class="info_box">
                    <input class="info info" type="text" id="L_name" name="username" placeholder="이름" />
                    <input class="info" type="email" id="mail" name="email" placeholder="이메일" />
                    <input class="info" type="email" id="confirm_mail" name="confirm_mail" placeholder="이메일 재입력" />
                    <font name="mailcheck" size="2" color="red"></font>
                    <input class="info" type="password" id="new_pw" name="password" placeholder="비밀번호" />
                    <input class="info" type="password" id="confirm_pw" name="confirm_pw" placeholder="비밀번호 재입력" />
                    <font name="check" size="2" color="red"></font>
                </div>
                <p><a href="#">가입하면 <strong>Klaiver</strong>의 <strong>약관</strong> 및 <strong>개인정보처리방침</strong>에 동의하게 됩니다.</a></p>
                <button class="join_btn"  id="join_btn">회원가입</button>
            </form>
        </div>
    </section>
</body>
</html>