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
	$("#modboard").on("click", function() {
		var form = $("#formFile")[0];
		var formData = new FormData(form); 
		// 파일
		formData.append("test1", $("#imageFile")[0].files[0]);
		// 문자열
		formData.append("test2", "String 문자열");
		// 숫자
		formData.append("test3", "12345");
		// 내용
		formData.append("test4", "context");
		
		$.ajax({
			url: './individualBupdate', 
			// 앞에서 지정한 formData
			data: formData, 
			processData: false, 
			contentType: false, 
			type: 'POST', 
			success: function(data){ 
				opener.parent.location.reload();
				self.close();
				} 
			});
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
					
						<div id="share_input">
							<c:forEach items="${sessionimg}" var="sessionimg">
								<span class="personal_img"><img
									src="../resources/img/${sessionimg.profimg}" /></span>
							</c:forEach>
							<form id="formFile" method="post" encType="multipart/form-data">
							<ul class="title_box">
								<c:forEach items="${updateBoard}" var="updateBoard">
									<li class="name">
									<input type="hidden" name="sbid" id="sbid" value="${updateBoard.sbid}" /> 
									<input type="hidden" name="warea" id="warea" value="${updateBoard.warea}" /> 
									<input type="hidden" name="readid" id="readid" value="${updateBoard.readid}" /> 
									<input type="hidden" name="userid" id="userid" value="${updateBoard.userid}" />
									<input type="hidden" name="fakeimg" value="${updateBoard.image}" />
									${updateBoard.company}(${updateBoard.first}${updateBoard.last})</li>

									<li><textarea class="record" name="content" wrap="hard"
											onkeyup="resize(this)">${updateBoard.content}</textarea></li>
								</c:forEach>
									<li class="hashtag"><span><input type="file"
											id="imageFile" /></span></li>
									<li><span></span></li>
									<li><div class="line"></div></li>
							</ul>
							</form>
							<button class="enter" name="edit_save" id="modboard">수정</button>
						</div>
					
				</div>
			</div>
		</div>

	</section>
</body>

</html>