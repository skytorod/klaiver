﻿<%@page import="com.test.domain.PageMaker"%>
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
						$('#search_add li').mouseenter(function() {
							$(this).find('.cover').stop().fadeIn(200);
						});

						$('#search_add li').mouseleave(function() {
							$(this).find('.cover').stop().fadeOut(200);
						});
						$("#search").keypress(
					            function(e) {
					               if (e.which == '13') {
					                  if ($("#search").val() != "") {
					                     var str = $("#search").val();
					                     var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
					             	     if(regExp.test(str)){
					             	    	 var keyword = str.replace(regExp, "");
					             	    	document.location.href = "../unsearch/alllist?keyword="
					                            + keyword;
					             	    }else{
					             	    	document.location.href = "../unsearch/alllist?keyword="
					                            + str;
					             	    }
					                     
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
					                  var str = $("#search").val();
					                  var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
					          	     if(regExp.test(str)){
					          	    	 var keyword = str.replace(regExp, "");
					          	    	document.location.href = "../unsearch/alllist?keyword="
					                         + keyword;
					          	    }else{
					          	    	document.location.href = "../unsearch/alllist?keyword="
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
	function gocompany() {
		alert("로그인 후 사용할수있습니다.");
	}
	function goproduct(keyword) {
		alert("로그인 후 사용할수있습니다.");
	}
	function gocompluse(keyword) {

		var keyword = keyword;
		location.href = "../unsearch/list?keyword=" + keyword;
	}
	function gosolopluse(keyword) {

		var keyword = keyword;
		location.href = "../unsearch/sololist?keyword=" + keyword;
	}
	function goproductpluse(keyword) {

		var keyword = keyword;
		location.href = "../unsearch/productlist?keyword=" + keyword;
	}
</script>

</head>
<body>
	<header>
		<div id="ci">
			<a href="../"><img src="../resources/images/ci.png"
				alt="Klaiver" /></a>
		</div>
		<form id="Integrated_search" name="Integrated_search" method="post">
			<input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군" />
			<input type="image" id="search_btn"
				src="../resources/images/search_btn.png" alt="검색" />
		</form>
		<ul id="gologin">
			<li><a
				href="../">로그인하러가기 </a></li>
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
					<span id="search_name">${cri.keyword}</span>에 대한 검색결과입니다.
				</div>
				<div class="search_result">
					<span id="count">&nbsp;</span>
				</div>
				<ul id="search_add">
					<li class="search2"><a
						href="../unsearch/alllist?keyword=${cri.keyword}"><div
								class="cover"></div> <img src="../resources/images/search_2.png"
							alt="개인별검색" /></a></li>
					<li class="search_alt"><a
						href="../unsearch/alllist?keyword=${cri.keyword}"><span
							class="t_s">전체</span>|</a></li>
					<li class="search3"><a
						href="../unsearch/list?keyword=${cri.keyword}"><div
								class="cover"></div> <img src="../resources/images/search_3.png"
							alt="기업별검색" /></a></li>
					<li class="search_alt"><a
						href="../unsearch/list?keyword=${cri.keyword}"><span class="t_s">기업</span>|</a></li>

					<li class="search2"><a
						href="../unsearch/sololist?keyword=${cri.keyword}"><div
								class="cover"></div> <img src="../resources/images/search_2.png"
							alt="개인별검색" /></a></li>
					<li class="search_alt"><a
						href="../unsearch/sololist?keyword=${cri.keyword}"><span
							class="t_s">개인</span>|</a></li>

					<li class="search4"><a href="../unsearch/productlist?keyword=${cri.keyword}"><div class="cover"></div>
							<img src="../resources/images/search_4.png" alt="포스트검색" /></a></li>
					<li class="search_alt"><a href="../unsearch/productlist?keyword=${cri.keyword}"><span class="t_s">제품</span></a></li>
				</ul>
				
			</div>

			<!----기업 콘텐츠---->
			<div id="contents_wrap">
				<ul id="search_list">
						<li class="title">
							<div>회사</div>
						</li>
					<c:if test="${fn:length(list) eq 0}">
						<li>
							<div>검색결과가 없습니다</div>
						</li>
					</c:if>
					
					<c:forEach items="${list}" var="SearchVO">
							<li>
								<div>
									<div class="search_img" style='cursor: pointer;'
										onclick="gocompany();">
										<img src="../resources/img/${SearchVO.pimage}" /> <input
											type="hidden" id="myid" value="${sessionScope.login}">
										<input type="hidden" id="email" value="${SearchVO.email}">
										<input type="hidden" id="userid" value="${SearchVO.userid}">
									</div>
									<div class="search_info">
										<div class="search_info_name">
											<span class="k_name" style='cursor: pointer;'
												onclick="gocompany();">${SearchVO.compname_kr}</span>
											| <span class="e_name" style='cursor: pointer;'
												onclick="gocompany();">${SearchVO.compname_en}</span>
											
										</div>
										<div class="search_info_add">
											<span class=" business_type" style='cursor: pointer;'
												onclick="gocompany();">${SearchVO.businessname}</span>
											| <span class="city" style='cursor: pointer;'
												onclick="gocompany();">${SearchVO.country}</span>
										</div>

									</div>
								</div>
							</li>
					</c:forEach>
				</ul>
				<div id="msg_box2" style='cursor: pointer;' onclick="gocompluse('${cri.keyword}');"><p class="ss">더보기</p></div>
				<ul id="search_list">
						<li class="title">
							<div >개인</div>
						</li>
					<c:if test="${fn:length(listsolo) eq 0}">
						<li>
							<div>검색결과가 없습니다</div>
						</li>
					</c:if>
					<c:forEach items="${listsolo}" var="IndividualVO">
							<li>
								<div>
									<div class="search_img" style='cursor: pointer;'
										onclick="gocompany();">
										<img src="../resources/img/${IndividualVO.profimg}" /> <input
											type="hidden" id="myid" value="${sessionScope.login}">
										<input type="hidden" id="email" value="${IndividualVO.email}">
									</div>
									<div class="search_info">
										<div class="search_info_name">
											<span class="k_name" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.first}${IndividualVO.last}</span>
										</div>
										<div class="search_info_name">
											<span class="k_name" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.email}</span>
											| <span class="k_name" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.company}</span>
											| <span class="e_name" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.industry}</span>
											
										</div>
										<div class="search_info_add">
											<span class=" business_type" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.city}</span>
											| <span class="city" style='cursor: pointer;'
												onclick="gocompany();">${IndividualVO.country}</span>
										</div>

									</div>
								</div>
							</li>
					</c:forEach>
				</ul>
				<div id="msg_box2" style='cursor: pointer;' onclick="gosolopluse('${cri.keyword}');"><p class="ss">더보기</p></div>
				<ul id="search_list">
						<li class="title">
							<div >제품</div>
						</li>
					<c:if test="${fn:length(productlist) eq 0}">
						<li>
							<div>검색결과가 없습니다</div>
						</li>
					</c:if>
					<c:forEach items="${productlist}" var="product">
							<li>
								<div>
									<div class="search_img" style='cursor: pointer;'
										onclick="goproduct('${product.companyCode}');"  style="width:20%">
										<img src="../resources/img/${product.image}" />
										<input type="hidden" id="pid" value="${product.pid}">
									</div>
									<div class="search_info"  style="width:73%;">
										<div class="search_info_name">
											<span class="k_name" style='cursor: pointer;'
												onclick="goproduct('${product.companyCode}');">${product.product}(${product.product_en})</span>
										</div>
										<div class="search_info_name">
											<span class="k_name" style='cursor: pointer;'
												onclick="goproduct('${product.companyCode}');">${product.pinfo}</span>
										</div>
									</div>
								</div>
							</li>
					</c:forEach>
				</ul>
				<div id="msg_box2" style='cursor: pointer;' onclick="goproductpluse('${cri.keyword}');"><p class="ss">더보기</p></div>
			
			</div>
		</div>

	</section>
</body>
</html>