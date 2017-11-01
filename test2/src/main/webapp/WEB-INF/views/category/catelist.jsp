<%@page import="com.test.domain.PageMaker"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="description" content="실시간 기업정보 서비스, 페이지, 정보공유" />

<title>Klaiver 카테고리 회사검색</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>
<script>
	$(document)
			.ready(
					function() {
						$('#icon li').mouseenter(function() {
							$(this).find('.cover').stop().fadeIn(200);
						});

						$('#icon li').mouseleave(function() {
							$(this).find('.cover').stop().fadeOut(200);
						});
						$("#search")
								.keypress(
										function(e) {
											if (e.which == '13') {
												if ($("#search").val() != "") {
													var str = $("#search")
															.val();
													var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
													if (regExp.test(str)) {
														var keyword = str
																.replace(
																		regExp,
																		"");
														document.location.href = "../search/alllist?keyword="
																+ keyword;
													} else {
														document.location.href = "../search/alllist?keyword="
																+ str;
													}

												} else {
													alert("검색어를 입력하세요");

												}
												return false;
											}
										});
						$("#search_btn")
								.click(
										function() {
											if ($("#search").val() != "") {
												var keyword = $("#search")
														.val();
												var str = $("#search").val();
												var regExp = /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
												if (regExp.test(str)) {
													var keyword = str.replace(
															regExp, "");
													document.location.href = "../search/alllist?keyword="
															+ keyword;
												} else {
													document.location.href = "../search/alllist?keyword="
															+ str;
												}
												return false;
											} else {
												alert("검색어를 입력하세요");
												return false;
											}
										});
					});
</script>
<script type="text/javascript">
	$(document)
			.ready(
					function() {
						var formObj = $("form[role='form']");
						console.log(formObj);
						$("#searchBtn")
								.click(
										function() {
											self.location = "catelist${pageMaker.makeQuery(1)}&searchType="
													+ $("#searchtype").val()
													+ "&keyword="
													+ $("#keyword1").val()
													+ "&keyword1="
													+ $("#keywordInput").val()
													+ "&keyword2="
													+ $("#keyword2").val();
										});
						$("#keywordInput")
								.keypress(
										function(e) {
											if (e.which == '13') {
												if ($("#keywordInput").val() != "") {
													var keyword = $(
															"#keywordInput")
															.val();
													document.location.href = "catelist${pageMaker.makeQuery(1)}&searchType="
															+ $("#searchtype")
																	.val()
															+ "&keyword="
															+ $("#keyword1")
																	.val()
															+ "&keyword1="
															+ $("#keywordInput")
																	.val()
															+ "&keyword2="
															+ $("#keyword2")
																	.val();
												} else {
													alert("검색어를 입력하세요");

												}
												return false;
											}
										});
					});
	function gocompany(userid) {
		var id = userid;
		location.href = "../search/searchNews?userid=" + id;
	}
	function gomycompany(userid) {

		var id = userid;
		location.href = "../cboard/news?userid=" + id;
	}
</script>
<script type="text/javascript">
	function reload() {
		if (window.opener) {
			window.opener.document.location.href = window.opener.document.URL;
		}
	}
</script>

</head>
<body onload="javascript:reload();">
	<header>
		<div id="ci">
			<a href="../home"><img src="../resources/images/ci.png"
				alt="Klaiver" /></a>
		</div>
		<form id="Integrated_search" name="Integrated_search" method="post">
			<input type="text" id="search" name="qa_text"
				placeholder="ex)기업명, 대표자명, 이메일, 산업군" /> <input type="image"
				id="search_btn" src="../resources/images/search_btn.png" alt="검색" />
		</form>
		<ul id="icon">
			<li class="icon1"><a
				href="../member/individual?keyword=${sessionScope.login}"><div
						class="cover"></div>
					<img src="../resources/images/icon1.png" alt="개인페이지" /> <c:if
						test="${fn:length(updatenewpost) ne 0}">new</c:if> </a></li>
			<li class="icon2"><a
				href="../cboard/companylist?userid=${sessionScope.login}"><div
						class="cover"></div> <img src="../resources/images/icon2.png"
					alt="기업페이지" /></a></li>
			<li class="icon3"><a href="../klogin/logout"><div
						class="cover"></div> <img src="../resources/images/icon3.png"
					alt="로그아웃" /></a></li>
		</ul>
	</header>

	<!------------==============================  콘텐츠 영역  ==============================------------>
	<section id="container">
		<div id="paper">
			<!----서치페이지 공통---->
			<div id="top_bg">
				<div id="color_box"></div>
			</div>
			<div id="contents_wrap">
				<div id="search_box">
					<div class="search_result">
						<span id="search_name">${cri.keyword} | ${cri.keyword1}</span>에 대한
						검색결과입니다.
					</div>
					<div class="search_result">
						검색결과 : <span id="count">${listcount}</span>개
					</div>
					<div class="select-box select-script category3"
						<c:out value="${cri.searchType eq 'address'?'selected':''}"/>>
						<input type="hidden" id="searchtype" value="add"> <input
							type="text" id="keywordInput" placeholder="지역검색 ex) 서울,부산......"
							name="keyword" /> <input type="hidden" id="keyword1"
							value="${keyword1}"> <input type="hidden" id="keyword2"
							value="${sessionScope.login}">
					</div>
					<button class="area_search" id="searchBtn">검색</button>
				</div>

				<!----기업 콘텐츠---->
				<div id="contents_wrap">
					<ul id="search_list">
						<c:if test="${fn:length(list) eq 0}">
							<li>
								<div>검색결과가 없습니다</div>
							</li>
						</c:if>
						<c:forEach items="${list}" var="SearchVO">
							<c:if test="${sessionScope.login ne SearchVO.email}">
								<li>
									<div>
										<div class="search_img">

											<img src="../resources/img/${SearchVO.profimg}"
												style='cursor: pointer;'
												onclick="gocompany('${SearchVO.email}');" /> <input
												type="hidden" id="myid" value="${sessionScope.login}">
											<input type="hidden" id="email" value="${SearchVO.email}">
											<input type="hidden" id="userid" value="${SearchVO.userid}">
										</div>
										<div class="search_info">
											<div class="search_info_name">
												<span class="k_name" style='cursor: pointer;'
													onclick="gocompany('${SearchVO.email}');">${SearchVO.compname_kr}</span>
												| <span class="e_name" style='cursor: pointer;'
													onclick="gocompany('${SearchVO.email}');">${SearchVO.compname_en}</span>

												<c:if test="${SearchVO.flag eq true}">
													<span class="follow_btn"><a
														href="../follow/delete?userid=${sessionScope.login}&followid=${SearchVO.email}">Following</a></span>
												</c:if>
												<c:if test="${SearchVO.flag eq false}">
													<span class="follow_btn"><a
														href="../follow/insertFollow?userid=${sessionScope.login}&followid=${SearchVO.email}">Follow</a></span>
												</c:if>
											</div>
											<div class="search_info_add" style='cursor: pointer;'
												onclick="gocompany('${SearchVO.email}');">
												<span class=" business_type">${SearchVO.businessname}</span>
												| <span class="city">${SearchVO.address}</span>
											</div>

										</div>
									</div>
								</li>
							</c:if>
							<c:if test="${sessionScope.login eq SearchVO.email}">
								<li>
									<div>
										<div class="search_img">

											<img src="../resources/img/${SearchVO.profimg}"
												style='cursor: pointer;'
												onclick="gomycompany('${SearchVO.email}');" /> <input
												type="hidden" id="myid" value="${sessionScope.login}">
											<input type="hidden" id="email" value="${SearchVO.email}">
											<input type="hidden" id="userid" value="${SearchVO.userid}">
										</div>
										<div class="search_info">
											<div class="search_info_name">
												<span class="k_name" style='cursor: pointer;'
													onclick="gomycompany('${SearchVO.email}');">${SearchVO.compname_kr}</span>
												| <span class="e_name" style='cursor: pointer;'
													onclick="gomycompany('${SearchVO.email}');">${SearchVO.compname_en}</span>
											</div>
											<div class="search_info_add" style='cursor: pointer;'
												onclick="gomycompany('${SearchVO.email}');">
												<span class=" business_type">${SearchVO.businessname}</span>
												| <span class="city">${SearchVO.address}</span>
											</div>

										</div>
									</div>
								</li>
							</c:if>
						</c:forEach>
					</ul>

					<div class="paging_navigation">
						<c:if test="${pageMaker.prev}">
							<span class="back_p"><a
								href="./catelist${pageMaker.makeSearch(pageMaker.firstPageNo)}">&lt;&lt;</a></span>
						</c:if>
						<c:if test="${pageMaker.prev}">
							<span class="back_p"><a
								href="./catelist${pageMaker.makeSearch(pageMaker.startPage - 1)}">&lt;</a></span>
						</c:if>
						<c:forEach begin="${pageMaker.startPage }"
							end="${pageMaker.endPage }" var="idx">
							<span class="p_list_num active"
								<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
								<a href="catelist${pageMaker.makeSearch(idx)}">${idx}</a>
							</span>
						</c:forEach>
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<span class="next_p"><a
								href="./catelist${pageMaker.makeSearch(pageMaker.endPage +1)}">&gt;</a></span>
						</c:if>
						<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
							<span class="next_p"><a
								href="./catelist${pageMaker.makeSearch(pageMaker.finalPageNo)}">&gt;&gt;</a></span>
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<!------------==============================  메세지 박스  ==============================------------>
		<!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp"%>

		<!-- 		팔로우리스트  			-->
	</section>
</body>
</html>