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
      
   <script>
         $(document).ready(function () {
             $('#icon li').mouseenter(function () {
                 $(this).find('.cover').stop().fadeIn(200);
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
    
    <script>
    
	 $(document).ready(function(){
		$("input[name=del_chkbox]:checkbox").change(function(){
			var valueArr = new Array();
			var list =$("input[name='del_chkbox']");
			
			for(var i = 0; i<list.length; i++){
				if(list[i].checked){
					valueArr.push(list[i].value)
				}
			}
			
		   $("#storage_end").click(function(){
				$.ajax({
					url : "./remove",
					type : "post",
					data : {
						notes_id : valueArr
					},
					success : function(data){
					
					alert("삭제하였습니다");
					location.reload();							
					}
				}); 
			}); 
		});
	 });
	</script>
    
</head>
<header>
		<div id="ci">
			<a href="../home"><img src="../resources/images/ci.png" alt="Klaiver" /></a>
		</div>
		<form id="Integrated_search" name="Integrated_search" method="get" action="#">
			<input type="text" id="search" name="qa_text" placeholder="Search" />
			<input type="image" id="search_btn" src="../resources/images/search_btn.png" alt="검색" />
		</form>
		<ul id="icon">
			<li class="icon1"><a href="../member/individual?keyword=${sessionScope.login}">
			<div class="cover"></div>
					<img src="../resources/images/icon1.png" alt="개인페이지" /></a></li>
			<li class="icon2"><a href="../cboard/read_home?userid=${sessionScope.login}">
			<div class="cover"></div>
					<img src="../resources/images/icon2.png" alt="기업페이지" /></a></li>
			<li class="icon3"><a href="../klogin/logout"><div class="cover"></div>
					<img src="../resources/images/icon3.png" alt="로그아웃" /></a></li>
		</ul>
	</header>
	
<body>
<br/><br/><br/>
<a href="../notes/senderlist?userid=${sessionScope.login}">쪽지 보관함</a>&nbsp;&nbsp;<br/><br/>
<div>쪽지 영구 보관함 <button id="storage_end" name="storage_end">삭제</button></div> 
	<br/><br/>
	<!-- <div><input type="checkbox" id=selectall/>전체선택</div> -->
	<br/>
	<c:forEach items="${getlist}" var="getlist">
	    <a href="../notes/recipient?sentid=${getlist.sent_id}&recvid=${getlist.recv_id}" >
	    <div id="read_check">
	    <input type="checkbox" id="del_chkbox" name="del_chkbox" value="${getlist.notes_id}"/>
			<input type="hidden"id="notes_id" name="notes_id" value="${getlist.notes_id}">
			<input type="hidden" id="recv_read" name="recv_read" value="${getlist.recv_read}">
			받는 사람 : ${getlist.recv_id} &nbsp;
		       보낸 사람 : ${getlist.sent_id} &nbsp;
			제목 : ${getlist.title} &nbsp;
			내용 : ${getlist.note} &nbsp;
			보낸날짜 : ${getlist.date_sent} &nbsp;
		<c:if test="${getlist.recv_read ne '0'}">
		  <div onclick="recvid(${getlist.notes_id});">  [ 읽음 ]</div>
		</c:if>
		<c:if test="${getlist.recv_read eq '0'}">
		  <div onclick="recvid(${getlist.notes_id});">  [안 읽음 ]</div>
		</c:if>	
		</div>
		<br/>
		</a>
		<br/>
	</c:forEach>

</body>
</html>