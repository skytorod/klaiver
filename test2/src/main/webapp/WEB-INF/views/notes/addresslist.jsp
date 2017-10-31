<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="description" content="실시간 기업정보 서비스, 페이지, 정보공유" />

<title>Klaiver</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />

<script src="../resources/js/jquery-1.11.2.js"></script>
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'></script>

<!--  회원 체크  -->
<script>

  $(document).ready(function(){
	  $("input[name=company_check]:checkbox").change(function(){
			var valueArr = new Array();
		    var list = $("input[name='company_check']");
		    for(var i = 0; i < list.length; i++){
		        if(list[i].checked){ //선택되어 있으면 배열에 값을 저장함
		            valueArr.push(list[i].value);
		        }
		    }
		   
		    $('#address_btn').click(function(){
		    	 //var jsonlist = JSON.stringify(valueArr);
				 //sent_id : $("#sent_id").val(),
				 //alert(valueArr);
       			 var emails = $("#recv_id",opener.document).val() + "," + valueArr;
       			 $("#recv_id",opener.document).val(emails);
		    	 
       		     self.close();
			  });   
	    });
	  
	  //alert($("#follow_list input[name='company_check']").length);
	  $("#follow_list input[name='company_check']").each(setEmailList);
	  $("#follow_list1 input[name='company_check']").each(setEmailList);
  });
  
  
  function setEmailList(i, val){
	  var emails = $("#recv_id",opener.document).val().split(',');
	  $.each(emails, function(j, email){
		  if($(val).val() == email){
			  $(val).parents("li").remove();
		  }
	  });
  }
  
</script>

</head>
<body>

	검색 :
	<input type="text" id="address_serch">
	<button>검색</button>

	<div id="address_box">
		<p>회사 Follow</p>
		<ul id="follow_list">
			<c:forEach items="${followcompanylist}" var="FollowCVO">
				<li><a
					href="../notes/addresslist?userid=${FollowCVO.fcfollowid}"> 
					<span class="profile_img">
					<input type="hidden" id="sent_id" name="sent_id" value="${senderid}">
					     <input type="hidden" id="recv_idlist" name="recv_idlist" value="${recv_idlist}" />
						 <input type="checkbox" name="company_check" id="company_check" value="${FollowCVO.company}">
						  <img src="../resources/img/${FollowCVO.pimage}" /></span>
						<div class="follow">
							<div class="user">${FollowCVO.company}</div>
						</div>
				</a></li>
			</c:forEach>
		</ul>
	</div>
	<br />
	<br />
	<br />
	<div id="address_box1">
		<p>개인 Follow</p>
		<ul id="follow_list1">
			<c:forEach items="${followlist}" var="FollowVO">
				<li><a href="../notes/addresslist?email=${FollowVO.followid}">
						<span class="profile_img">
						<input type="hidden" id="sent_id" name="sent_id" value="${senderid}">
						 <input type="hidden" id="recv_idlist" name="recv_idlist" value="${recv_idlist}" />
						 <input type="checkbox" name="company_check" id="company_check" value="${FollowVO.followid}">
							<img src="../resources/img/${FollowVO.profimg}" /></span>
						<div class="follow">
							<div class="user">${FollowVO.first}${FollowVO.last}<br>${FollowVO.company}</div>
						</div>
				</a></li>
			</c:forEach>
		</ul>
	</div>
	<br/>
	<br/>
	<br/>
	<button id="address_btn" name="address_btn">확인</button>
	<button onclick="self.close()">취소</button>
	
</body>
</html>