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
    <title>Klaiver 회사정보 수정페이지(contact)</title>
    <link href="../resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='../resources/css/klaiver.css' />
     <link rel='stylesheet' type='text/css' href='../resources/css/join.css' />
    <script src="../resources/js/jquery-1.11.2.js"></script>
    <script>
    // 이미지 클릭시 원본 크기로 팝업 보기
    function doImgPop(img) {
		if(img!='../resources/img/noimg.jpg'){
			img1 = new Image();
			img1.src = (img);
			imgControll(img);
		}
	}
      
    function imgControll(img){ 
     if((img1.width!=0)&&(img1.height!=0)){ 
        viewImage(img); 
      } 
      else{ 
         controller="imgControll('"+img+"')"; 
         intervalID=setTimeout(controller,20); 
      } 
    }
    function viewImage(img){ 
     W=img1.width; 
     H=img1.height; 
     O="width="+W+",height="+H+",scrollbars=yes"; 
     imgWin=window.open("","",O); 
     imgWin.document.write("<body topmargin=0 leftmargin=0>");
     imgWin.document.write("<img src="+img+" onclick='self.close()' style='cursor:pointer;'>");
     imgWin.document.close();
    }
    </script>
               <script>
               function myComfollower(code){
               	var code = code;
                	var popUrl = "../member/soloComfollower?code="+code;
            		var windowW = 400;  // 창의 가로 길이
                   var windowH = 400;  // 창의 세로 길이
                   var left = Math.ceil((window.screen.width - windowW)/2);
                   var top = Math.ceil((window.screen.height - windowH)/2);
           		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
               }
    function myComfollowing(email){
    	var email = email;
     	var popUrl = "../member/soloComfollowing?email="+email;
 		var windowW = 400;  // 창의 가로 길이
        var windowH = 400;  // 창의 세로 길이
        var left = Math.ceil((window.screen.width - windowW)/2);
        var top = Math.ceil((window.screen.height - windowH)/2);
		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
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
             
         });
    </script>
<script type="text/javascript">
$(document).ready(function () {
	 var formObj = $("form[role='form']");
		console.log(formObj);

		$("#modify").click(function() {
					formObj.submit();
				});
		$("#search").keypress(
	            function(e) {
	               if (e.which == '13') {
	                  if ($("#search").val() != "") {
	                     var str = $("#search").val();
	                     var regExp =  /[ \{\}\[\]\/?.,;:|\)*~`!^\-_+┼<>\#$%&\'\"\\(\=]/gi;
	             	     if(regExp.test(str)){
	             	    	 var keyword = str.replace(regExp, "");
	             	    	document.location.href = "../search/alllist?keyword="
	                            + keyword;
	             	    }else{
	             	    	document.location.href = "../search/alllist?keyword="
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
	          	    	document.location.href = "../search/alllist?keyword="
	                         + keyword;
	          	    }else{
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
	function reload() {
		if (window.opener) {
			window.opener.document.location.href = window.opener.document.URL;
		}
	}
</script>

</head>
<body onload="javascript:reload();">
    <header>
        <div id="ci"><a href="../home"><img src="../resources/images/ci.png" alt="Klaiver"/></a></div>
        <form id="Integrated_search" name="Integrated_search" method="get" >
            <input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군"  />
            <input type="image" id="search_btn" src="../resources/images/search_btn.png" alt="검색"/>
        </form>
        <ul id="icon">
       <li class="icon1"><a
					href="../cboard/news?userid=${sessionScope.login}"><div
							class="cover"></div> <img src="../resources/images/icon1.png"
						alt="개인페이지" />
				</a></li>
           <li class="icon2"><a
				href="../cboard/companylist?userid=${sessionScope.login}"><div
						class="cover"></div> <img src="../resources/images/icon2.png"
					alt="기업페이지" /></a></li>
           <li class="icon3"><a href="../klogin/logout"><div class="cover"></div><img src="../resources/images/icon3.png" alt="로그아웃"/></a></li>
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
					<c:forEach items="${IndividualVO}" var="IndividualVO">
						<img src="../resources/img/${IndividualVO.profimg}"
							onclick="doImgPop('../resources/img/${IndividualVO.profimg}')" />
					</c:forEach>
				</div>
				<!--기업프로필이미지-->
				<div id="company_info">
					<!--기업정보-->
					<c:forEach items="${IndividualVO}" var="IndividualVO">
						<div id="individual_name">${k_aboutVO.compname_kr}<br>
							${k_aboutVO.compname_en}<br> ${k_contactVO.address}
							<a href="../cboard/update_home?userid=${sessionScope.login}"><img
								src="../resources/images/set.png" /></a>
						</div>
						<ul id="page_move">
							<li><a href="news?userid=${IndividualVO.email}">Home</a></li>
							<li><a href="check_about?userid=${IndividualVO.email}">About</a></li>
							<li><a href="readPage_product?userid=${IndividualVO.email}">Product</a></li>
							<li class="on"><a href="check_contact?userid=${IndividualVO.email}">Contact</a></li>
						</ul>
					</c:forEach>
				</div>
				<div id="commu">
					<div id="follow_btn">
						<button type="submit" id="message" name="message">Message</button>
					</div>
				</div>
				<div id="follow_num_home1">
					<!--팔로워/팔로잉-->
					<p>
						팔로워 <span id="follower_num_home"> <a href="#"
							onclick="viewfollower('');">${followercount}</a>
						</span>
					</p>
					<p>
						팔로잉 <span id="following_num_home"> <a href="#"
							onclick="viewfollowing('');">${followingcount}</a>
						</span>
					</p>
				</div>

			</div>
            
            <!----기업 콘텐츠---->
            <div id="join_wrap">
            <form id="form4" name="joinForm"role='form' method="post" >
                    <div class="array">
                        <span>전화번호</span>
                        <input type="hidden" name="cid"	value="${k_contactVO.cid}"> 
                        <input type="hidden" name="userid"	value="${k_contactVO.userid}"> 
                        <input class="info_enter" type="text" id="input21" name="phonenumber"value="${k_contactVO.phonenumber}" />
                    </div> 
                    <div class="array">
                        <span>Fax</span>
                        <input class="info_enter" type="text" id="input22" name="fax"value="${k_contactVO.fax}"  />
                    </div>
                    <div class="array">
                        <span>Email</span>
                        <input class="info_enter" type="text" id="input23" name="cemail" value="${k_contactVO.cemail}"/>
                    </div>
                    <div class="array">
                        <span>주소</span>
                        <input class="info_enter" type="text" id="input24" name="address" value="${k_contactVO.address}"/>
                    </div>
                    <div class="array">
                        <span>조직도</span>
                        <input class="info_enter" type="text" id="input25" name="organizationChart" value="${k_contactVO.organizationChart}"/>
                    </div>                  
                
               		<button class="prev_page1" type="submit" >저장</button>
                
            </form>
        </div>
        </div>

        <!------------==============================  메세지 박스  ==============================------------>
                <!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp" %>
		
<!-- 		팔로우리스트  			-->
    </section>
</body>
</html>