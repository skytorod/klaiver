<%-- <%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page session="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'></script>
<script
	src='http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.5/jquery-ui.min.js'></script>
<style>
/* 
        레이아웃 전체 가운데 정렬 태그  
        - margin:0 auto 0 auto;(시계방향으로 위, 오른쪽, 아래, 왼쪽)
        왼쪽, 오른쪽 바깥여백을 auto로 주면 중앙정렬이 된다. 
        
        전체를 감싸주는 태그 #wrap 
        */
#wrap {
	width: 800px;
	margin: 0 auto 0 auto;
}

#mainright {
	float: right;
	width: 30%;
}

#mainleft {
	float: left;
	width: 70%;
}

#footer {
	clear: left;
	width: 800px;
	height: 60px;
}
</style>


<script type="text/javascript">
	function autoDate() {
		var tDay = new Date();
		var tMonth = tDay.getMonth() + 1;
		var tDate = tDay.getDate();
		var tHour = tDay.getHours();
		var tMinutes = tDay.getMinutes();
		if (tMonth < 10)
			tMonth = "0" + tMonth;
		if (tDate < 10)
			tDate = "0" + tDate;
		document.getElementById("regdate").value = tDay.getFullYear() + "년"
				+ tMonth + "월" + tDate + "일" + tHour + ":" + tMinutes;
	}
	function addLoadEvent(func) {
		var oldonload = window.onload;
		if (typeof window.onload != 'function') {
			window.onload = func;
		} else {
			window.onload = function() {
				if (oldonload) {
					oldonload();
				}
				func();
			}
		}
	}

	addLoadEvent(function() {
		autoDate();
	});
</script>

<body>


	<div id="mainleft">
		<form method="post">
			<div>
				<div>
					<label>글쓴이</label> <input type="text" name="userid" id="userid"
						value="${sessionScope.login}" readonly="readonly" />
				</div>
				<div>
					<label>내용</label>
					<textarea name="content" rows="3"></textarea>
				</div>
				<div>
					<input type="file" name="image" />
				</div>

				<div>
					<input type="text" name="regdate" id="regdate" readonly="readonly"/>
				</div>
			</div>
			<button type="submit">올리기</button>
		</form>
		<div>

			<c:forEach items="${list}" var="ScrollBoardVO">
			<ul class="title_box">
				<li>
					<div class="scrolling" data-sbid="${ScrollBoardVO.sbid}">
						<input type="hidden" value="${ScrollBoardVO.sbid}">
					</div>
				</li>	
                <li class="name"><a href="../member/individualfollow?email=${ScrollBoardVO.userid}">${ScrollBoardVO.userid}</a></li>
                <li class="tt">소원을 빌어봅니다.</li>
                <li class="cts">${ScrollBoardVO.content}</li>
                <li class="contents_pt"><img src="../resources/${ScrollBoardVO.image}" /></li>
                <li class="hashtag"><span>${ScrollBoardVO.regdate}</span></li>
                
                <li><div class="line"></div></li>
            </ul>
			</c:forEach>
			<div class="scrollLocation"></div>
		</div>
		<!-- Modal -->

	</div>
	<div id="mainright">
		<jsp:include page="../follow/followlist.jsp" flush="true" />
	</div>
</body>
<script>
	$(document)
			.ready(
					function() {
						var scrollEventFlag = false;
						var lastScrollTop = 0;
						$(window)
								.scroll(
										function() {
											var currentScrollTop = $(window)
													.scrollTop();
											var scrollpercent = (document.body.scrollTop + document.documentElement.scrollTop)
													/ (document.documentElement.scrollHeight - document.documentElement.clientHeight);
											console.log(scrollpercent);
											if (scrollpercent >= 0.999
													&& !scrollEventFlag) {
												console.log(scrollpercent);
												scrollEventFlag = true;
												var lastbno = $(
														".scrolling:last")
														.attr("data-sbid");
												var sessionid = $("#userid").val();
												console.log(lastbno);
												console.log(sessionid);
												$.ajax({
															type : 'POST',
															url : '/scrollboard/infiniteScrollDown',
															headers : {
																"Content-Type" : "application/json",
																"X-HTTP-Method-Override" : "POST"
															},
															dataType : 'json',
															data : {
																sbid : lastbno,
																sessionid : sessionid
															},
															success : function(
																	data) {
																var str = "";
																if (data != "") {
																	$(data)
																			.each(
																					function() {
																						//console.log(this);
																						//console.log(this.sbid);
																						str += "<div class=" + "'title_box'" + ">"
																								+ "<div class=" +  "'scrolling'" + " data-sbid='" + this.sbid +"'>"
																								/* + "<input type='hidden' value='"+this.sbid+"'>" 
																								<a href="../member/individualfollow?email=${ScrollBoardVO.userid}">${ScrollBoardVO.userid}</a>
																								*/
																								+ "</div>"
																								+ "<div><a href='../member/individualfollow?email="+this.userid+"'>"
																								+this.userid
																								+ "</a></div>"
																								+ "<div>"
																								+ this.content
																								+ "</div>"
																								+ "<div>"
																								+ "<img src='../resources/"+this.image+"' width='250'	height='250'>"
																								+ "</div>"
																								+ "<div>"
																								+ this.regdate
																								+ "</div>"
																								+ "</div>";
																					});// each
																	// 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을  뿌려준다.   
																	$(
																			".scrollLocation")
																			.append(
																					str);
																} else { // 9. 만약 서버로 부터 받아온 데이터가 없으면 그냥 아무것도 하지말까..
																	alert("더 불러올 데이터가 없습니다.");
																}
															}
														});
											} else if (scrollpercent < 0.999) {
												scrollEventFlag = false;
											} else if (false) {
												lastScrollTop = currentScrollTop;
											}

										});// scroll event
					});
</script>


</html> --%>