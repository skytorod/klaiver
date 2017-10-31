<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>
                                                      <!-- 보내는   페이지 -->
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
 
 <script src="../resources/js/jquery-1.11.2.js"></script>
	<script type="text/javascript">
	  $(document).ready(function(){
		  // var sendform = $('form[name="sendform"]').serialize();		 
		 $('#frecipient_btn').click(function(){
			 $.ajax({
				url : './fsender',
				type : 'post',
				data : {
					recv_id : $("#recv_id").val(),
					sent_id : $("#sent_id").val(),
					title   : $("#title").val(),
					note    : $("#note").val()					
				},
				success : function(data){
					if($("#note").val()!= ""){
						alert("전송 성공");  	
						self.close();
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
	
  <script>
   $(document).ready(function(){
	   $("#addresslist_bttn").click(function(){
		  var emaill = $("#useridssi").val();
		  var popUrll = "../notes/addresslist?email="+emaill;
		  var popOptionn = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
		  window.open(popUrll, "주소록page", popOptionn);
	   });
   });
   </script>
</head>
<body>
	<form id="rsendform" name="rsendform" >
		보내는 사람 <input type="text" id="recv_id" name="recv_id" value="${useremail}${NotesVO.recv_id}">
				<input type="hidden" id="sent_id" name="sent_id" value="${sessionScope.login}">
				<input type="hidden" name="useridssi" id="useridssi" value="${sessionScope.login}"/>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<input type="button" id="addresslist_bttn" name="addresslist_bttn" value="추가하기"><br /> <br />
		제목 :<input type="text" id="title" name="title" > <br />
		<textarea name="note" id="note" cols="40" rows="10"></textarea>
		<br /> <br />
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
		<input type="button" id="frecipient_btn" value="보내기">

	</form>
	
	<div id="error"></div>
</body>
</html>