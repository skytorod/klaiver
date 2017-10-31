<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
                                                      <!-- 보내는   페이지 -->
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Klaiver</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>

  <!-- 보내기 -->
	<script type="text/javascript">
	  $(document).ready(function(){
		  // var sendform = $('form[name="sendform"]').serialize();
		 
		 $('#recipient_btn').click(function(){
			$.ajax({
				url : './sender',
				type : 'post',  
				data : {
					recv_id : $("#recv_id").val(),
					sent_id : $("#sent_id").val(),
					title   : $("#title").val(),
					note    : $("#note").val(),
				},
				success : function(data){
					if($("#note").val()!= ""){						
						alert("전송 성공");  	
						$('#sendform')[0].reset();
					}else {
					    alert("내용이 없습니다.")
					    return false;
					}
				},
				error:function(request,status,error){
				    var html = "code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error;				    
				    $("#error").html(html);
				}
			});			
		 });
	  });
	</script>
       
    <!--  주소록 -->
   <script>
   $(document).ready(function(){
	   $("#addresslist_btn").click(function(){
		   var email = $("#userids").val();
			var popUrl = "../notes/addresslist?email="+email;
			var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
			window.open(popUrl, "보내는page", popOption);
	   });
   });
   </script>
   
   <script>
         $(document).ready(function () {
             $('#icon li').mouseenter(function () {
                 $(this).find('.cover').stop().fadeIn(l200);
             });

             $('#icon li').mouseleave(function () {
                 $(this).find('.cover').stop().fadeOut(200);
             });
             $("#search").keypress(
    					function(e) {
    						if (e.which == '13') {
    							if ($("#search").val() != "") {
    								var keyword = $("#search").val();
    								document.location.href = "../search/list?keyword="
    										+ keyword;
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
    							document.location.href = "../search/list?keyword="+ keyword;
    							return false;
    						} else {
    							alert("검색어를 입력하세요");
    							return false;
    						}
    					});
         });
    </script>
</head>
	<header>
		<div id="ci">
			<a href="../home"><img src="../resources/images/ci.png"
				alt="Klaiver" /></a>
		</div>
		<form id="Integrated_search" name="Integrated_search" method="get"
			action="#">
			<input type="text" id="search" name="qa_text" placeholder="Search" />
			<input type="image" id="search_btn"
				src="../resources/images/search_btn.png" alt="검색" />
		</form>
		<ul id="icon">
			<li class="icon1"><a
				href="../member/individual?keyword=${sessionScope.login}"><div
						class="cover"></div>
					<img src="../resources/images/icon1.png" alt="개인페이지" /></a></li>
			<li class="icon2"><a
				href="../cboard/read_home?userid=${sessionScope.login}"><div
						class="cover"></div>
					<img src="../resources/images/icon2.png" alt="기업페이지" /></a></li>
			<li class="icon3"><a href="../klogin/logout"><div
						class="cover"></div>
					<img src="../resources/images/icon3.png" alt="로그아웃" /></a></li>
		</ul>
	</header>
	
	<!-- 쪽지 부분 -->
	
     <br/><br/><br/>
     	<a href="../notes/senderlist?userid=${sessionScope.login}">쪽지 보관함</a>
     	<br/><br/><br/>
     
	<form id="sendform" name="sendform" >
		받는 사람 <input type="text" id="recv_id" name="recv_id" value="${NotesVO.recv_id}"readonly="readonly">
		<input type="hidden" id="sent_id" name="sent_id" value="${sessionScope.login}">
		  
		<input type="hidden" name="userids" id="userids" value="${sessionScope.login}"/>
		&nbsp&nbsp&nbsp  <button id="addresslist_btn" name="addresslist_btn" onclick="location.href='../notes/addresslist=${sessionScope.login}'">주소록</button> <br /> <br />
		제목 :<input type="text" id="title" name="title" > <br />
		<textarea name="note" id="note" cols="40" rows="10"></textarea>
		<br /> <br />
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<input type="button" id="recipient_btn" value="보내기">

	</form>
	
	<div id="error"></div>
</body>
</html>