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
	href='../resources/css/klaiver.css' />
<script src="../resources/js/jquery-1.11.2.js"></script>
<script
	src='http://ajax.googleapis.com/ajax/libs/jquery/1.4.2/jquery.min.js'></script>
<script>
function newpage(id) {
	var email = id;
	var sessionid = $("#sessionid").val();
	var popUrl ="";
	if(email == sessionid){
		popUrl = "../cboard/readPage_home?userid=" + email;
	}else if(email != sessionid){
		popUrl = "../search/search_home?userid=" + email;
	}
	
	window.open(popUrl, "_blank");
}
</script>
<script type="text/javascript">
	function reload() {
		if (window.opener) {
			opener.location.reload();
		}
	}
</script>
</head>
<body onload="javascript:reload();">
	<!------------==============================  콘텐츠 영역  ==============================------------>
	<section id="container">
		<div id="msg_box2">
			<ul id="follow_list2">
				<c:forEach items="${myCompanyfolloer}" var="myCompanyfolloer">
					<div class="scrolling" data-fid="${myCompanyfolloer.fcid}">
						<input type="hidden" id="email" value="${myCompanyfolloer.fcmyid}">
          			     <input type="hidden" id="sessionid" value="${sessionScope.login}">
					</div>
					<li><span class="profile_img" style='cursor: pointer;'
						onclick="newpage('${myCompanyfolloer.fcfollowid}');"><img
							src="../resources/img/${myCompanyfolloer.profimg}" /></span>

						<div>
							<div>
								<span class="user" style='cursor: pointer;'
									onclick="newpage('${myCompanyfolloer.fcfollowid}');">
									${myCompanyfolloer.first}${myCompanyfolloer.last}<br>${myCompanyfolloer.company}</span>
								
								<c:if test="${myCompanyfolloer.flag eq true && sessionScope.login ne myCompanyfolloer.fcfollowid}">
									<span class="follow_btn"><a
										href="../follow/deleteCom?userid=${sessionScope.login}&followid=${myCompanyfolloer.fcfollowid}">Following</a></span>
								</c:if>
								<c:if test="${myCompanyfolloer.flag eq true && sessionScope.login eq myCompanyfolloer.fcfollowid}">
									<span class="follow_btn"></span>
								</c:if>
								<c:if test="${myCompanyfolloer.flag eq false&& sessionScope.login ne myCompanyfolloer.fcfollowid}">
									<span class="follow_btn"><a
										href="../follow/insertComFollow?userid=${sessionScope.login}&followid=${myCompanyfolloer.fcfollowid}">Follow</a></span>
								</c:if>
								<c:if test="${myCompanyfolloer.flag eq false&& sessionScope.login eq myCompanyfolloer.fcfollowid}">
									<span class="follow_btn"></span>
								</c:if>
							</div>
						</div></li>
				</c:forEach>
			</ul>
			<div class="scrollLocation"></div>
		</div>
	</section>
</body>

<script>
	$(document).ready(function() {
			var scrollEventFlag = false;
			var lastScrollTop = 0;
			$(window).scroll(function() {
				var currentScrollTop = $(window).scrollTop();
				var scrollpercent = (document.body.scrollTop + document.documentElement.scrollTop)
									/ (document.documentElement.scrollHeight - document.documentElement.clientHeight);
				console.log(scrollpercent);
				if (scrollpercent >= 0.99&& !scrollEventFlag) {
					console.log(scrollpercent);
					scrollEventFlag = true;
					var lastbno = $(".scrolling:last").attr("data-fid");
					var email = $("#email").val();
					console.log(lastbno);
					console.log(email);
					$.ajax({
						type : 'POST',
						url : '../member/comViewfollowingScroll',
						headers : {
						"Content-Type" : "application/json",
						"X-HTTP-Method-Override" : "POST"
						},
						dataType : 'json',
						data : {
							fid : lastbno,
							email : email
						},
						success : function(data) {
							var str = "";
							var followbtn="";
							if (data != "") {
								$(data).each(function() {
									var fcid = this.fcid;
									var fcmyid = this.fcmyid;
									var fcfollowid = this.fcfollowid;
									var first = this.first;
									var last = this.last;
									var compname_kr = this.compname_kr;
									var compname_en = this.compname_en;
									var pimage = this.pimage;
									var company = this.company;
									var title = this.title;
									var profimg = this.profimg;
									var flag = this.flag;
									var id = $("#email").val();
									var sessionid = $("#sessionid").val();
									if(this.flag == true && sessionid != fcfollowid){
										str +="<ul id='follow_list2'>" 
											+"<div class='scrolling' data-fid='"+fcid+"'>"
											+"<input type='hidden' id='email' value='"+id+"'></div>"
											+"<li><span class='profile_img' style='cursor: pointer;' onclick=newpage('"+fcfollowid+"');>"
											+"<img src='../resources/img/"+profimg+"' /></span>"
											+"<div>"
											+"<div>"
											+"<span class='user' style='cursor: pointer;'onclick=newpage('"+fcfollowid+"');>"
											+first+last+"<br>"+company+"</span>"
											+"<span class='follow_btn'>"
											+"<a href='../follow/deleteCom?userid="+sessionid+"&followid="+fcfollowid+"'>Following</a></span>"
											+"</div>"
											+"</div></li></ul>";
									}else if(this.flag == false){
										str +="<ul id='follow_list2'>" 
											+"<div class='scrolling' data-fid='"+fcid+"'>"
											+"<input type='hidden' id='email' value='"+id+"'></div>"
											+"<li><span class='profile_img' style='cursor: pointer;' onclick=newpage('"+fcfollowid+"');>"
											+"<img src='../resources/img/"+profimg+"' /></span>"
											+"<div>"
											+"<div>"
											+"<span class='user' style='cursor: pointer;'onclick=newpage('"+fcfollowid+"');>"
											+first+last+"<br>"+company+"</span>"
											+"<span class='follow_btn'>"
											+"<a href='../follow/insertComFollow?userid="+sessionid+"&followid="+fcfollowid+"'>Follow</a></span>"
											+"</div>"
											+"</div></li></ul>";
									}else if(this.flag == true && sessionid == fcfollowid){
										str +="<ul id='follow_list2'>" 
											+"<div class='scrolling' data-fid='"+fcid+"'>"
											+"<input type='hidden' id='email' value='"+id+"'></div>"
											+"<li><span class='profile_img' style='cursor: pointer;' onclick=newpage('"+fcfollowid+"');>"
											+"<img src='../resources/img/"+profimg+"' /></span>"
											+"<div>"
											+"<div>"
											+"<span class='user' style='cursor: pointer;'onclick=newpage('"+fcfollowid+"');>"
											+first+last+"<br>"+company+"</span>"
											+"</div>"
											+"</div></li></ul>";
									}
								});// each
								$(".scrollLocation").append(str);
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
</html>