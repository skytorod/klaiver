<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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


</head>
<body>
	<header>
		<ul id="icon">
			<li class="icon3"><a href="../klogin/logout"><div
						class="cover"></div> <img src="../resources/images/icon3.png"
					alt="로그아웃" /></a></li>
		</ul>
	</header>
	<div>
		<form method="post">
			<c:forEach items="${mlist}" var="mlist">
				<div><input type="hidden" name="companyCode" value="${mlist.companyCode}">${mlist.companyCode}</div>
				<div>${mlist.userid}</div>
				<div>${mlist.first}${mlist.last}</div>
				<div>${mlist.title}</div>
				<div>${mlist.repname}</div>
				<div>${mlist.country}</div>
				<div>
					<select name="manager">
						<option value="">관리자설정</option>
						<option value="1">Master</option>
						<option value="2">SubMaster</option>
						<option value="3">회원</option>
						<option value="0">기본설정</option>
					</select>
				</div>
				<div><input type="submit" value="저장"></div>
			</c:forEach>
		</form>
	</div>
	<!-- 관리자 아이디정보 -->

</body>
</html>