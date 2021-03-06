﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
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

<title>Klaiver 전체 검색</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>

<script>
	$(document).ready(function() {
		$('#icon li').mouseenter(function() {
			$(this).find('.cover').stop().fadeIn(200);
		});

		$('#icon li').mouseleave(function() {
			$(this).find('.cover').stop().fadeOut(200);
		});
	});
</script>
<script>
	$(document)
			.ready(
					function() {
						var check = $("#check").val();
						if (check == 0) {
							alert("등록된 회사페이지가 없습니다");
							self.close();
						}
						$('#search_add li').mouseenter(function() {
							$(this).find('.cover').stop().fadeIn(200);
						});

						$('#search_add li').mouseleave(function() {
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

						/* $("#gocompany").click(function() {
							var userid = $("#userid").val();
							window.open("./search_home?userid="+userid, "_blank");
						}); */
					});
	function gomycompany(code) {
		window.open("./check_company?code=" + code, "_blank");
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
						class="cover"></div> <img src="../resources/images/icon1.png"
					alt="개인페이지" /> <c:if test="${fn:length(updatenewpost) ne 0}">new</c:if>
			</a></li>
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

			<div id="search_box">
				<div class="search_result">
					<span id="search_name">&nbsp;</span>
				</div>
				<div class="search_result">
					<span id="count">&nbsp;</span>
				</div>

			</div>
			<!----기업 콘텐츠---->
			<input type="hidden" id="check" value="${fn:length(list)}">
			<div id="contents_wrap">
				<ul id="search_list">
					<li><c:forEach items="${list}" var="list">
							<div>
								<div>
									<span style='cursor: pointer;'
										onclick="gomycompany('${list.companyCode}');">
										${list.comname_kr}(${list.comname_en})
										${list.first}${list.last} (${list.title})${list.country}</span>
								</div>
							</div>
						</c:forEach></li>
				</ul>

			</div>
		</div>

		<!------------==============================  메세지 박스  ==============================------------>
		<!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp"%>

		<!-- 		팔로우리스트  			-->
	</section>
</body>
</html>