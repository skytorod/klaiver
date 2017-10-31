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

<title>Klaiver 회사페이지(about)</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>
<script>
	// 이미지 클릭시 원본 크기로 팝업 보기
	function doImgPop(img) {
		if (img != '../resources/img/noimg.jpg') {
			img1 = new Image();
			img1.src = (img);
			imgControll(img);
		}
	}

	function imgControll(img) {
		if ((img1.width != 0) && (img1.height != 0)) {
			viewImage(img);
		} else {
			controller = "imgControll('" + img + "')";
			intervalID = setTimeout(controller, 20);
		}
	}
	function viewImage(img) {
		W = img1.width;
		H = img1.height;
		O = "width=" + W + ",height=" + H + ",scrollbars=yes";
		imgWin = window.open("", "", O);
		imgWin.document.write("<body topmargin=0 leftmargin=0>");
		imgWin.document.write("<img src=" + img
				+ " onclick='self.close()' style='cursor:pointer;'>");
		imgWin.document.close();
	}
</script>
<script>
	function myComfollower(code) {
		var code = code;
		var popUrl = "../member/soloComfollower?code=" + code;
		var windowW = 400; // 창의 가로 길이
		var windowH = 400; // 창의 세로 길이
		var left = Math.ceil((window.screen.width - windowW) / 2);
		var top = Math.ceil((window.screen.height - windowH) / 2);
		window.open(popUrl, "목록보기", "l top=" + top + ", left=" + left
				+ ", height=" + windowH + ", width=" + windowW);
	}
	function myComfollowing(email) {
		var email = email;
		var popUrl = "../member/comViewfollowing?email=" + email;
		var windowW = 400; // 창의 가로 길이
		var windowH = 400; // 창의 세로 길이
		var left = Math.ceil((window.screen.width - windowW) / 2);
		var top = Math.ceil((window.screen.height - windowH) / 2);
		window.open(popUrl, "목록보기", "l top=" + top + ", left=" + left
				+ ", height=" + windowH + ", width=" + windowW);
	}
</script>
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
														document.location.href = "../search/list?keyword="
																+ keyword;
													} else {
														document.location.href = "../search/list?keyword="
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
													document.location.href = "../search/list?keyword="
															+ keyword;
												} else {
													document.location.href = "../search/list?keyword="
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
<script>
	$(document)
			.ready(
					function() {
						$("#message")
								.click(
										function() {
											var email = $("#userids").val();
											var popUrl = "../notes/fsender?email="
													+ email;
											var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
											window.open(popUrl, "보내는page",
													popOption);
											window.close();
										});
					});
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
		<form id="Integrated_search" name="Integrated_search" method="get"
			action="#">
			<input type="text" id="search" name="qa_text"
				placeholder="ex)기업명, 대표자명, 이메일, 산업군" /> <input type="image"
				id="search_btn" src="../resources/images/search_btn.png" alt="검색" />
		</form>
		<ul id="icon">
			<li class="icon1"><a
				href="../member/individual?keyword=${sessionScope.login}">
					<div class="cover"></div> <img src="../resources/images/icon1.png"
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
			<!----기업페이지 공통---->
			<div id="top_bg">
				<div id="color_box"></div>
			</div>

			<div id="company_profile">
				<div id="company_img">

					<img src="../resources/img/${k_homeVO.pimage}"
						onclick="doImgPop('../resources/img/${k_homeVO.pimage}')" />
				</div>
				<!--기업프로필이미지-->
				<div id="company_info">
					<!--기업정보-->
					<div id="company_name">${k_aboutVO.compname_kr}</div>
					<div id="company_type">${k_aboutVO.businessname}</div>
					<div id="company_loc">${k_homeVO.country}</div>

					<ul id="page_move">
						<!--페이지 이동-->
						<li><a href="searchNews?code=${k_aboutVO.companyCode}">News</a></li>
						<li><a href="search_home?code=${k_aboutVO.companyCode}">Home</a></li>
						<li class="on"><a
							href="search_about?code=${k_aboutVO.companyCode}">About</a></li>
						<li><a href="search_product?code=${k_aboutVO.companyCode}">Product</a></li>
						<li><a href="search_contact?code=${k_aboutVO.companyCode}">Contact</a></li>
					</ul>
				</div>
				<div id="commu">
					<div id="follow_btn">
						<c:if test="${k_aboutVO.flag eq true}">
							<button id="follow" name="follow"
								onclick="location.href='../follow/deleteCom?userid=${sessionScope.login}&followcode=${k_homeVO.companyCode}'">Following</button>
						</c:if>
						<c:if test="${k_aboutVO.flag eq false}">
							<button id="follow" name="follow"
								onclick="location.href='../follow/insertComFollow?userid=${sessionScope.login}&followcode=${k_homeVO.companyCode}'">Follow</button>
						</c:if>
						<button type="submit" id="message" name="message">Message</button>
					</div>
				</div>
				<div id="follow_num_home1">
					<!--팔로워/팔로잉-->
					<p>
						회사 팔로워 <span id="follower_num_home"><a href="#"
							onclick="myComfollower('${k_homeVO.companyCode}');">${followerComcount}</a></span>명
					</p>
				</div>

			</div>

			<!----기업 콘텐츠---->
			<div id="contents_wrap">
				<p>기업정보</p>
				<div>
					<table>
						<tr>
							<td class="list">기업명</td>
							<td class="data"><span id="corporate_form">${k_aboutVO.compname_kr}</span></td>
						</tr>
						<tr>
							<td class="list">영문기업명</td>
							<td class="data"><span id="corporate_form">${k_aboutVO.compname_en}</span></td>
						</tr>
						<tr>
							<td class="list">기업형태</td>
							<td class="data"><span id="corporate_form">${k_aboutVO.businessType}</span></td>
						</tr>
						<tr>
							<td class="list">대표자명</td>
							<td class="data"><span id="owner_name">${k_aboutVO.repname}</span></td>
						</tr>
						<tr>
							<td class="list">업종명</td>
							<td class="data"><span id="business_type">${k_aboutVO.businessname}</span></td>
						</tr>
						<tr>
							<td class="list">주요상품</td>
							<td class="data"><span id="product">${k_aboutVO.main_product}</span></td>
						</tr>
						<tr>
							<td class="list">인증현황</td>
							<td class="data"><span id="certified">${k_aboutVO.certificationStatus}</span></td>
						</tr>
						<tr>
							<td class="list">종업원 수</td>
							<td class="data"><span id="staff">${k_aboutVO.employees}
									명</span></td>
						</tr>
						<tr>
							<td class="list">사업장 현황</td>
							<td class="data"><span id="business_place">${k_aboutVO.businessStatus}</span></td>
						</tr>
					</table>
				</div>
				<p>현황</p>
				<table class="table_">
					<tr class="t_list">
						<td>매출액</td>
						<td>당기순이익</td>
					</tr>
					<tr class="t_data">
						<td id="sales">${k_aboutVO.take}</td>
						<td id="profit">${k_aboutVO.netincome}</td>
					</tr>
					<tr class="t_list">
						<td>자본금</td>
						<td>설립일</td>
					</tr>
					<tr class="t_data">
						<td id="capital">${k_aboutVO.capital}</td>
						<td id="date">${k_aboutVO.establish}</td>
					</tr>
				</table>
				<p>안내</p>
				<table class="table_">
					<tr class="t_list">
						<td>주소</td>
						<td></td>
					</tr>
					<tr class="t_data">
						<td id="ad">${k_aboutVO.address}</td>
						<td id="web"></td>
					</tr>
				</table>
				<table class="table_">
					<tr class="t_list">
						<td>전화번호</td>
						<td>Fax</td>
					</tr>
					<tr class="t_data">
						<td id="tel">${k_aboutVO.phonenumber}</td>
						<td id="fax">${k_aboutVO.fax}</td>
					</tr>
				</table>
				<p>기업소개</p>
				<div class="white_box" id="company_intro">
					${k_aboutVO.aboutUs}</div>
			</div>
		</div>

		<!------------==============================  메세지 박스  ==============================------------>
		<!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp"%>

		<!-- 		팔로우리스트  			-->
	</section>
</body>
</html>