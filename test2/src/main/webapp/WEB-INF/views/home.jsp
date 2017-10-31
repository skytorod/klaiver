<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page session="true"%>
<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta charset="utf-8" />
<meta name="description" content="실시간 기업정보 서비스, 페이지, 정보공유" />

<title>Klaiver</title>
<link href="./resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='./resources/css/klaiver.css' />
<script src="./resources/js/jquery-1.11.2.js"></script>
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
														document.location.href = "./search/alllist?keyword="
																+ keyword;
													} else {
														document.location.href = "./search/alllist?keyword="
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
													document.location.href = "./search/alllist?keyword="
															+ keyword;
												} else {
													document.location.href = "./search/alllist?keyword="
															+ str;
												}
												return false;
											} else {
												alert("검색어를 입력하세요");
												return false;
											}
										});
						$("#searchcateBtn")
								.click(
										function() {
											if ($("#ks_id option:selected")
													.val() == "") {
												alert("산업을 선택하세요");
											} else {
												self.location = "category/catelist?keyword="
														+ $(
																"#ks_id option:selected")
																.val()
														+ "&keyword2="
														+ $("#userid").val();
											}
										});
					});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("select[name='mc_number']").val() != "")
			getid();

		$("select[name='mc_number']").on("change", getid);
	});

	function getid() {
		var selected_mc_number = $("select[name='mc_number']").val();

		$.getJSON("./home/" + selected_mc_number, function(list) {
			var htmlStr = "<option value=''>업종</option>";
			$(list).each(
					function(key, val) {
						console.log("val.kc_number: " + val.kc_number);
						console.log("val.ks_name :" + val.ks_name);
						htmlStr += "<option value='"+ val.ks_name +"'>"
								+ val.ks_name + "</option>";
					});
			$("select[name='ks_id']").html(htmlStr);

		});
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
				<a href="./home"><img src="./resources/images/ci.png"
					alt="Klaiver" /></a>
			</div>
			<form id="Integrated_search" name="Integrated_search" method="get"
				action="#">
				<input type="text" id="search" name="qa_text"
					placeholder="ex)기업명, 대표자명, 이메일, 산업군" /> <input type="image"
					id="search_btn" src="./resources/images/search_btn.png" alt="검색" />
			</form>
			<ul id="icon">
				<li class="icon1"><a
					href="./cboard/news?userid=${sessionScope.login}"><div
							class="cover"></div> <img src="./resources/images/icon1.png"
						alt="개인페이지" />
				</a></li>
				<li class="icon2"><a
					href="./cboard/companylist?userid=${sessionScope.login}"><div
							class="cover"></div> <img src="./resources/images/icon2.png"
						alt="기업페이지" /></a></li>
				<li class="icon3"><a href="./klogin/logout"><div
							class="cover"></div> <img src="./resources/images/icon3.png"
						alt="로그아웃" /></a></li>
			</ul>
			<%-- <ul id="icon">
				<li class="icon1"><a
					href="./member/individual?keyword=${sessionScope.login}"><div
							class="cover"></div> <img src="./resources/images/icon1.png"
						alt="개인페이지" /> <c:if test="${fn:length(updatenewpost) ne 0}">new</c:if>
				</a></li>
				<li class="icon2"><a
					href="./cboard/companylist?userid=${sessionScope.login}"><div
							class="cover"></div> <img src="./resources/images/icon2.png"
						alt="기업페이지" /></a></li>
				<li class="icon3"><a href="./klogin/logout"><div
							class="cover"></div> <img src="./resources/images/icon3.png"
						alt="로그아웃" /></a></li>
			</ul> --%>
		</header>

		<!------------==============================  콘텐츠 영역  ==============================------------>
		<section id="container">
			<div id="paper">
				<div id="ad">
					<img src="./resources/images/maina.jpg" />
				</div>
				<!----메인광고---->

				<!----상세검색---->
				<form id="detailed_search" name="detailed_search" method="get"
					action="#">
					<input type="hidden" value="${sessionScope.login}" id="userid">
					<select class="category" name="mc_number" id="mc_number">
						<option value="">산업군</option>
						<c:forEach items="${list}" var="CategoryVO">
							<option value="${CategoryVO.mc_number}">${CategoryVO.mc_name}</option>
						</c:forEach>
					</select> <select class="category" name="ks_id" id="ks_id">
						<option value="">업종</option>
					</select>
					<button class="search" type="button" id="searchcateBtn">검색</button>
				</form>

				<!----기업정보 콘텐츠---->
				<ul id="wrap">
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_11.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">bnl</div>
								<div class="_add">전 세계에 통합된 플라스틱 베어링 솔류션 공금 기업</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_01.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">경남기업</div>
								<div class="_add">건설 전문업체</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_02.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">sunb</div>
								<div class="_add">선박 엔진용 배관, 대형 철의장 제작업체</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_03.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">nitrozen</div>
								<div class="_add">온라인 게임 개발 수입 수출 기업</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_04.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">NWA</div>
								<div class="_add">노스트웨스트항공사는 미국내 5대 항공사 업체</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_05.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">groupm</div>
								<div class="_add">세계 최대의 미디어 투자 그룹</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_06.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">KDDI</div>
								<div class="_add">일본의 전기통신 업체</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_07.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">삼환기업</div>
								<div class="_add">건설, 건축, 플랜트 전문업체</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_08.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">SOFTHEAD</div>
								<div class="_add">모바일게임 개발 기업</div>
							</div>
					</a></li>
					<li class="contents"><a href="#"> <span
							class="contents_img"><img
								src="./resources/images/contents_09.jpg" /></span>
							<div class="contents_memo">
								<div class="_subject">PIVOTEC</div>
								<div class="_add">보안솔류션 기업</div>
							</div>
					</a></li>
				</ul>
			</div>

			<!------------==============================  메세지 박스  ==============================------------>
			<%-- <div id="msg_box">
				<p>회사 Follow</p>
				<ul id="follow_list">
					<c:forEach items="${followcompanylist}" var="FollowCVO">
						<li><a
							href="./search/search_home?code=${FollowCVO.followcode}"> <span
								class="profile_img"><img
									src="./resources/img/${FollowCVO.pimage}" /></span>
								<div class="follow">
									<div>
										<c:if test="${fn:length(FollowCVO.compname_kr) > 10}">
											<c:out value="${fn:substring(FollowCVO.compname_kr,0,10)}" />....
												<c:if test="${fn:length(FollowCVO.compname_en) > 10}">
												<br>(<c:out
													value="${fn:substring(FollowCVO.compname_en,0,10)}" />....)
           										</c:if>
											<br>(<c:out value="${FollowCVO.compname_en}" />)
										</c:if>
										<c:if test="${fn:length(FollowCVO.compname_kr) <10}">
											<c:out value="${FollowCVO.compname_kr}" />
											<br>
											(<c:out value="${FollowCVO.compname_en}" />)
									</c:if>
									</div>
								</div>
						</a></li>
					</c:forEach>
				</ul>
			</div>
			<div id="msg_box1">
				<p>개인 Follow</p>
				<ul id="follow_list1">
					<c:forEach items="${followlist}" var="FollowVO">
						<li><a
							href="./member/individualfollow?email=${FollowVO.followid}">
								<span class="profile_img"><img
									src="./resources/img/${FollowVO.profimg}" /></span>

								<div class="follow">
									<div class="user">
										<c:if test="${fn:length(FollowVO.last) > 5}">
									${FollowVO.first}<c:out
												value="${fn:substring(FollowVO.last,0,5)}" />....
								</c:if>
										<c:if test="${fn:length(FollowVO.last) < 5}">
									${FollowVO.first}${FollowVO.last}
								</c:if>
										<br>
										<c:if test="${fn:length(FollowVO.company) > 7}">
											<c:out value="${fn:substring(FollowVO.company,0,5)}" />....
								</c:if>
										<c:if test="${fn:length(FollowVO.company) < 7}">
									${FollowVO.company}
								</c:if>
										&nbsp;(${FollowVO.title})
										<c:if test="${FollowVO.new_post eq 'true'}">
											<div class="new">new</div>
										</c:if>
									</div>
								</div>
						</a></li>

					</c:forEach>
				</ul>
			</div> --%>
		</section>
</body>
</html>