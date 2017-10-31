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

<title>Klaiver</title>
<link href="../resources/images/favicon.png" rel="shortcut icon"
	type="image/png" />
<link rel='stylesheet' type='text/css'
	href='http://fonts.googleapis.com/css?family=Noto+Sans' />
<link rel='stylesheet' type='text/css'
	href='./resources/css/klaiver.css' />
<script src="./resources/js/jquery-1.11.2.js"></script>
<script type="text/javascript">
function sendmemID(id,kr,en) {
	opener.setmemID(id);
	opener.setmemIDcom(kr,en);
	window.close();
}
</script>
<script>
         $(document).ready(function () {
             $('#icon li').mouseenter(function () {
                 $(this).find('.cover').stop().fadeIn(200);
             });

             $('#icon li').mouseleave(function () {
                 $(this).find('.cover').stop().fadeOut(200);
             });
             $("#search1").keypress(
			            function(e) {
			               if (e.which == '13') {
			                  if ($("#search1").val() != "") {
			                     var str = $("#search1").val();
			                     var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
			             	     if(regExp.test(str)){
			             	    	 var keyword = str.replace(regExp, "");
			             	    	document.location.href = "./companylist?keyword="+keyword;
			             	    }else{
			             	    	document.location.href = "./companylist?keyword="+str;
			             	    }
			                     
			                  } else {
			                     alert("검색어를 입력하세요");

			                  }
			                  return false;
			               }
			            });
			         $("#search_btn1").click(function() {
			               if ($("#search1").val() != "") {
			                  var keyword = $("#search1").val();
			                  var str = $("#search1").val();
			                  var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
			          	     if(regExp.test(str)){
			          	    	 var keyword = str.replace(regExp, "");
			          	    	document.location.href = "./companylist?keyword="+keyword;
			          	    }else{
			          	    	document.location.href = "./companylist?keyword="+str;
			          	    }
			                  return false;
			               } else {
			                  alert("검색어를 입력하세요");
			                  return false;
			               }
			            });
         });
    </script>
</head>
<body>
	<!------------==============================  콘텐츠 영역  ==============================------------>
	<header>
		<form id="Integrated_search1" name="Integrated_search" method="get">
			<input type="text" id="search1" placeholder="ex)기업명, 기업영어명, 주소" /> 
			<input type="image" id="search_btn1" src="./resources/images/search_btn.png" alt="검색" />
		</form>
	</header>
	
	
	<section id="container">
		<c:forEach items="${companylist}" var="companylist">
			<div><a href="javascript:sendmemID('${companylist.companyCode}','${companylist.comname_kr}','${companylist.comname_en}')">
			<input type="hidden" value="${companylist.companyCode}">
			${companylist.comname_kr}(${companylist.comname_en}) ${companylist.country}</a></div>
		</c:forEach>
		<div class="paging_navigation">
					<c:if test="${pageMaker.prev}">
						<span class="back_p"><a
							href="./companylist${pageMaker.makeSearch(pageMaker.firstPageNo)}">&lt;&lt;</a></span>
					</c:if>
					<c:if test="${pageMaker.prev}">
						<span class="back_p"><a
							href="./companylist${pageMaker.makeSearch(pageMaker.startPage - 1)}">&lt;</a></span>
					</c:if>
					<c:forEach begin="${pageMaker.startPage }"
						end="${pageMaker.endPage }" var="idx">
						<span class="p_list_num active"
							<c:out value="${pageMaker.cri.page == idx?'class =active':''}"/>>
							<a href="companylist${pageMaker.makeSearch(idx)}">${idx}</a>
						</span>
					</c:forEach>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<span class="next_p"><a
							href="./companylist${pageMaker.makeSearch(pageMaker.endPage +1)}">&gt;</a></span>
					</c:if>
					<c:if test="${pageMaker.next && pageMaker.endPage > 0}">
						<span class="next_p"><a
							href="./companylist${pageMaker.makeSearch(pageMaker.finalPageNo)}">&gt;&gt;</a></span>
					</c:if>
				</div>
	</section>
</body>

</html>