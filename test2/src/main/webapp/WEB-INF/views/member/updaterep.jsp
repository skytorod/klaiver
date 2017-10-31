<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />

<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>

<script>
	$(document).ready(function() {
		$("body").scrollTop($(document).height());
		$("#modrep").on("click", function() {
			var text = $("#replytext").val();
			var rid = $("#rid").val();
			$.ajax({
			type : "POST",
			url : "../member/updaterep",
			data : {
				rid : rid,
				replytext : text
				},
				success : function(data) {
				alert("수정되었습니다.");
				opener.parent.location.reload();
				self.close(); 
				}
			});
		});
		$("#replytext").keypress(function(e) {	
    		if (e.which=='13'){
    			var text = $("#replytext").val();
    			var rid = $("#rid").val();
    			$.ajax({
    			type : "POST",
    			url : "../member/updaterep",
    			data : {
    				rid : rid,
    				replytext : text
    				},
    				success : function(data) {
    				alert("수정되었습니다.");
    				opener.parent.location.reload();
    				self.close(); 
    				}
    			});
    		} 
    		
    	});
	});
</script>
<script>
	function resize(obj) {
		obj.style.height = "1px";
		obj.style.height = (20 + obj.scrollHeight) + "px";
		$("body").scrollTop($(document).height());
	}
</script>

</head>
<body>


	<!------------==============================  콘텐츠 영역  ==============================------------>
	<section id="container">
		<div id="paper">
			<div id="contents_wrap">
				<div id="share_info">
					<!--정보공유 영역-->
					<form role="formrep">
						<div id="share_input">
							<c:forEach items="${getReplyupdate}" var="getReplyupdate">
								<c:forEach items="${sessionimg}" var="sessionimg">
									<span class="personal_img"><img
										src="../resources/img/${sessionimg.profimg}" /></span>
								</c:forEach>
								<ul class="title_box">
									<li class="name"><input type="hidden"id="rid" name="rid" value="${getReplyupdate.rid}">
									<input type="hidden" name="boardid" value="${getReplyupdate.boardid}">
									<input type="hidden" name="rep_userid" value="${getReplyupdate.rep_userid}">
									${getReplyupdate.company}(${getReplyupdate.first}${getReplyupdate.last})</li>
									<li><textarea class="record" id="replytext" name="replytext"
									 onkeyup="resize(this)">${getReplyupdate.replytext}</textarea></li>
								</ul>
							</c:forEach>
							<button class="enter" name="edit_save" id="modrep">수정</button>
						</div>
					</form>
				</div>
			</div>
		</div>

	</section>
</body>

</html>