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

<title>Klaiver 회사페이지(home)</title>
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
var jb = jQuery.noConflict();
</script>
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
      }else{ 
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
$(document).ready(function(){
	// submitButton 클릭 이벤트
	$("#submitButton").click(function(){
		var form = $("#formFile")[0];
		var formData = new FormData(form); 
		// 파일
		formData.append("test1", $("#imageFile")[0].files[0]);
		// 문자열
		formData.append("test2", "String 문자열");
		// 숫자
		formData.append("test3", "12345");
		// 내용
		formData.append("test4", "context");
		
		$.ajax({
			url: '../cboard/cominsert', 
			// 앞에서 지정한 formData
			data: formData, 
			processData: false, 
			contentType: false, 
			type: 'POST', 
			success: function(data){ 
				location.reload();
				} 
			});
	});
	
});
</script>
<script>
         $(document).ready(function () {
             $('#icon li').mouseenter(function () {
                 $(this).find('.cover').stop().fadeIn(200);
             });

             $('#icon li').mouseleave(function () {
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
<script>
  	function mod(str){
  		 event.preventDefault();
      	 var cbid = str;
      	var popUrl = "../cboard/newsUpdate?cbid="+cbid;
		var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
		window.open(popUrl, "수정page", popOption);
     	}
      	function remove(idx){
      		var cbid=idx;
      		var result = confirm('삭제 하시겠습니까?');
      		if(result) { 
      			$.ajax({
   				url : "../cboard/newsremove?sbid="+cbid,
   				type : "post",
   				data : {
   					cbid : idx
   				}
   			});
      			
      		}
      		location.reload();
      	}
      	function replymod(str){
       var crid = str;
   	var popUrl = "../cboard/newsRepUpdate?crid="+crid;
		var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
		window.open(popUrl, "수정page", popOption);
       }
      	function replyremove(idx){
      		var result = confirm('삭제 하시겠습니까?');
      		if(result) { 
      			$.ajax({
   				url : "../cboard/replyremove?crid="+idx,
   				type : "post",
   				data : {
   					crid : idx
   				}
   			});
      			location.reload();
      		}

      		
      	}
      	function myComfollower(code){
      		var code = code;
      	 	var popUrl = "../member/soloComfollower?code="+code;
      			var windowW = 400;  // 창의 가로 길이
      	    var windowH = 400;  // 창의 세로 길이
      	    var left = Math.ceil((window.screen.width - windowW)/2);
      	    var top = Math.ceil((window.screen.height - windowH)/2);
      		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
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
<body id="body" onload="javascript:reload();">
	<header>
		<div id="ci">
			<a href="../home"><img src="../resources/images/ci.png"
				alt="Klaiver" /></a>
		</div>
		<form id="Integrated_search" name="Integrated_search" method="get">
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
						<li class="on"><a href="searchNews?code=${k_homeVO.companyCode}">News</a></li>
						<li><a href="search_home?code=${k_homeVO.companyCode}">Home</a></li>
						<li><a href="search_about?code=${k_homeVO.companyCode}">About</a></li>
						<li><a href="search_product?code=${k_homeVO.companyCode}">Product</a></li>
						<li><a href="search_contact?code=${k_homeVO.companyCode}">Contact</a></li>
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
						<button type="submit" id="message" name="message"
							onclick="location.href='../notes/senderlist?userid=${sessionScope.login}'">Message</button>
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
				<div id="share_info">
					<!--정보공유 영역-->
					<div id="share_input">
						<!--입력박스-->
						<form id="formFile" method="POST" encType="multipart/form-data">
							<c:forEach items="${IndividualVO}" var="IndividualVO">
								<span class="personal_img"> <img
									src="../resources/img/${IndividualVO.profimg}"
									onclick="doImgPop('../resources/img/${IndividualVO.profimg}')" />
								</span>

								<ul class="title_box">
									<c:forEach items="${sessionidName}" var="sessionidName">
										<li class="name"><input type="hidden" name="cbwarea"
											id="cbwarea" value="${k_homeVO.companyCode}" /> <input
											type="hidden" name="cbreadid" id="cbreadid"
											value="${sessionScope.login}" /> <input type="hidden"
											name="cbuserid" id="cbuserid" value="${sessionScope.login}" />
											<a href="../member/individual?keyword=${sessionScope.login}">
												${sessionidName.company}(${sessionidName.first}${sessionidName.last})</a></li>
									</c:forEach>
									<li><textarea class="record" name="cbcontent" wrap="hard"
											onkeyup="resize(this)"></textarea></li>
									<li class="hashtag"><span><input type="file"
											id="imageFile" /></span></li>
									<li><span></span></li>
									<li><div class="line"></div></li>
								</ul>

							</c:forEach>
							<input type="button" id="submitButton" class="enter"
								name="edit_save" value="글등록">
						</form>
					</div>


					<input type="hidden" id="manager" value="${manager}">
					<!--등록된 컨텐츠 박스-->
					<c:forEach items="${scrolllist}" var="ScrollBoardVO"
						varStatus="status">
						<div class="data_box">
							<ul class="title_box">
								<li>
									<div class="scrolling" data-cbid="${ScrollBoardVO.cbid}">
										<input type="hidden" id="ssbid" value="${ScrollBoardVO.cbid}">
										<input type="hidden" id="code" value="${code}"> <input
											type="hidden" id="id" value="${sessionScope.login}">
									</div>
								</li>
								<li class="name"><span class="personal_img"><img
										src="../resources/img/${ScrollBoardVO.profimg}"
										onclick="doImgPop('../resources/img/${ScrollBoardVO.profimg}')" /></span>
									<c:if test="${manager eq ScrollBoardVO.cbuserid}">
										<a
											href="../member/individualfollow?email=${ScrollBoardVO.cbuserid}">
											<font color="RED">${ScrollBoardVO.company}
												(${ScrollBoardVO.first}${ScrollBoardVO.last})</font>
										</a>
									</c:if> <c:if test="${manager ne ScrollBoardVO.cbuserid}">
										<a
											href="../member/individualfollow?email=${ScrollBoardVO.cbuserid}">
											${ScrollBoardVO.company}
											(${ScrollBoardVO.first}${ScrollBoardVO.last}) </a>
									</c:if> <input type="hidden" id="idx${ScrollBoardVO.cbid}"
									value="${ScrollBoardVO.cbid}" /> <c:if
										test="${ScrollBoardVO.cbuserid ne sessionScope.login && manager eq sessionScope.login}">
										<button class="enter" name="edit_save"
											onclick="remove(${ScrollBoardVO.cbid})">삭제</button>
									</c:if> <c:if
										test="${ScrollBoardVO.cbuserid eq sessionScope.login && manager eq sessionScope.login}">
										<button class="enter" name="edit_save"
											onclick="remove(${ScrollBoardVO.cbid})">삭제</button>
										<button class="enter" name="edit_save"
											onclick="mod(${ScrollBoardVO.cbid});">수정</button>
									</c:if> <c:if
										test="${ScrollBoardVO.cbuserid eq sessionScope.login && manager ne sessionScope.login }">
										<button class="enter" name="edit_save"
											onclick="remove(${ScrollBoardVO.cbid})">삭제</button>
										<button class="enter" name="edit_save"
											onclick="mod(${ScrollBoardVO.cbid});">수정</button>
									</c:if></li>
								<li class="hashtag"><span>${ScrollBoardVO.cbregdate}</span></li>
								<c:if test="${!empty ScrollBoardVO.cbimage}">
									<li class="contents_pt"><img
										src="../resources/img/${ScrollBoardVO.cbimage}"
										onclick="doImgPop('../resources/img/${ScrollBoardVO.cbimage}')" /></li>
								</c:if>
								<li class="contents_pt"><br></li>
								<li class="cts">${ScrollBoardVO.cbcontent}</li>

								<li><div class="line"></div></li>
							</ul>
							<form class="repForm" id="repForm" method="POST"
								action="../cboard/comreply">
								<ul class="title_box">
									<li><input type="hidden" name="comboardid" id="comboardid"
										value="${ScrollBoardVO.cbid}"> <input type="hidden"
										name="comrep_userid" id="comrep_userid"
										value="${sessionScope.login}"> <textarea
											class="record" name="comreplytext" id="comreplytext"
											onkeyup="resize(this)"
											onkeypress="if(event.which=='13')form.submit()"></textarea></li>

									<li>
										<button class="comment_enter replytextbtn" name="edit_save"
											id="repsubmit">
											<img src="../resources/images/btn_07.jpg" /> 댓글달기
										</button>
									</li>
									<li><div class="line"></div></li>

								</ul>
							</form>
						</div>
						<div class="data_box_rep">
							<ul class="title_box">
								<c:forEach items="${replylist}" var="replylist">
									<c:if test="${ScrollBoardVO.cbid == replylist.comboardid}">
										<li><div>
												<span class="personal_img"><img
													src="../resources/img/${replylist.profimg}"
													onclick="doImgPop('../resources/img/${replylist.profimg}')" /></span>

												<c:if test="${manager eq replylist.comrep_userid}">
													<a
														href="../member/individualfollow?email=${replylist.comrep_userid}">
														<font color="RED">${replylist.company}(${replylist.first}${replylist.last})</font>
													</a>
												</c:if>
												<c:if test="${manager ne replylist.comrep_userid}">
													<a
														href="../member/individualfollow?email=${replylist.comrep_userid}">
														${replylist.company}(${replylist.first}${replylist.last})
													</a>
												</c:if>
											</div> <br></li>
										<li>${replylist.comreplytext}</li>
										<li><div>
												<c:if
													test="${replylist.comrep_userid ne sessionScope.login && manager eq sessionScope.login}">
													<input type="button" value="삭제" style="margin-right: 10px"
														onclick="replyremove(${replylist.crid});">
												</c:if>
												<c:if
													test="${replylist.comrep_userid eq sessionScope.login && manager eq sessionScope.login}">
													<input type="button" value="수정" style="margin-right: 10px"
														onclick="replymod(${replylist.crid});">
													<input type="button" value="삭제" style="margin-right: 10px"
														onclick="replyremove(${replylist.crid});">
												</c:if>
												<c:if
													test="${replylist.comrep_userid eq sessionScope.login && manager ne sessionScope.login}">
													<input type="button" value="수정" style="margin-right: 10px"
														onclick="replymod(${replylist.crid});">
													<input type="button" value="삭제" style="margin-right: 10px"
														onclick="replyremove(${replylist.crid});">
												</c:if>
												<c:if
													test="${replylist.comrep_userid ne sessionScope.login && manager eq sessionScope.login}">
													&nbsp;
												</c:if>
												<span style="float: right;">${replylist.comrep_regdate}</span>
											</div></li>
									</c:if>
									<li class="re_reply${replylist.crid}"></li>

								</c:forEach>

							</ul>
						</div>
					</c:forEach>

					<div class="scrollLocation"></div>

				</div>
			</div>
		</div>

		<!------------==============================  메세지 박스  ==============================------------>
		<!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp"%>

		<!-- 		팔로우리스트  			-->
	</section>
</body>

<script>
	jb(document).ready(function() {
			var scrollEventFlag = false;
			var lastScrollTop = 0;
			jb(window).scroll(function() {
				
				
											var currentScrollTop = jb(window)
													.scrollTop();
											var scrollpercent = (document.body.scrollTop + document.documentElement.scrollTop)
													/ (document.documentElement.scrollHeight - document.documentElement.clientHeight);
											console.log(scrollpercent);
											if (scrollpercent >= 0.99
													&& !scrollEventFlag) {
												console.log(scrollpercent);
												scrollEventFlag = true;
												var lastbno = jb(".scrolling:last").attr("data-cbid");
												var code = jb("#code").val();
												var sessionid = jb("#id").val(); 
												var manager = jb("#manager").val(); 
												console.log(lastbno);
												jb.ajax({
															type : 'POST',
															url : '../cboard/newsDownScroll',
															headers : {
																"Content-Type" : "application/json",
																"X-HTTP-Method-Override" : "POST"
															},
															dataType : "json",
															data : {
																cbid : lastbno,
																code : code
															},
															success : function(data) {
																var str = "";
																var idx=0;
																if (data != "") {
																	jb(data).each(function() {
																		var cbid = this.cbid;
																		console.log(this);
																		console.log(this.cbid);
																		idx++;
																		if(this.cbimage != null && this.cbimage != "" && this.cbimage != "undefined"){
																			var imageStr = "<img src='../resources/img/"+this.cbimage+"' onclick=doImgPop('../resources/img/"+this.cbimage+"')>";
																		}else{
																			imageStr="<br>";
																		}
																		
																		if(this.cbuserid != sessionid && manager == sessionid){
																			var btnstr ="<button type='submit' class='enter' name='edit_save' onclick='remove("+this.cbid+");'>삭제</button>" 
																		}else if(this.cbuserid == sessionid && manager ==sessionid){
																			btnstr ="<button type='submit' class='enter' name='edit_save' onclick='remove("+this.cbid+");'>삭제</button>" 
																				+"<button type='submit' class='enter' name='edit_save' onclick='mod("+this.cbid+");'>수정</button>"
																		}else if(this.cbuserid == sessionid && manager !=sessionid){
																			btnstr ="<button type='submit' class='enter' name='edit_save' onclick='remove("+this.cbid+");'>삭제</button>" 
																					+"<button type='submit' class='enter' name='edit_save' onclick='mod("+this.cbid+");'>수정</button>"
																		}else if(this.cbuserid != sessionid && manager != sessionid){
																			btnstr =" ";
																		}
																		if(manager==this.cbuserid){
																			var managercolor = "<font color='RED'>"+this.company+"("+this.first+this.last+")</font>"
																		}else{
																			managercolor =this.company+"("+this.first+this.last+")";
																		}
																		str += "<div class='data_box'>"	
																			+"<ul class='title_box'>"
																			+"<li>"
																			+ "<div class='scrolling' data-cbid='" + this.cbid +"'>"
																			+ "<input type='hidden'id='ssbid' value='" + this.cbid +"'>"
																			+ "<input type='hidden' id='code' value='"+this.cbwarea+"'>"
																			+ "</div></li>"
																			+ "<li class='name'>"
																			+"<span class='personal_img'><img src='../resources/img/" + this.profimg +"'onclick=doImgPop('../resources/img/"+this.profimg+"')></span>"
																			+"<a href='../member/individualfollow?email="+this.cbuserid+"'>"
																			+managercolor
																			+ "</a>"
																			+ btnstr
																			+"</li>"
																			+ "<li class='hashtag'><span>"
																			+ this.cbregdate
																			+ "</span></li>"
																			+ "<li class='contents_pt'>"
																			+ imageStr
																			+ "</li>"
																			+"<li class='contents_pt'></li>"
																			+ "<li class='cts'>"
																			+ this.cbcontent
																			+ "</li>"
																			+"<form id='repForm' method='POST' action='../cboard/comreply'>"
																			+"<ul class='title_box'>"
																			+"<li><input type='hidden' name='comboardid' id='comboardid' value='" + this.cbid +"'>"
																			+"<input type='hidden' name='comrep_userid' id='comrep_userid' value='"+sessionid+"'>"
																			+"<textarea class='record comment_re' name='comreplytext' id='comreplytext' onkeyup='resize(this)'onkeypress='if(event.which==13)form.submit()'></textarea></li>"
																			+"<li>"
																			+"<button type='submit' class='comment_enter' name='edit_save' onfocus='this.value=''' id='rep'>"
																			+"<img src='../resources/images/btn_07.jpg' /> 댓글달기</button>"
																			+"</li>"
																			+"<li><div class='line'></div></li>"
																			+"</ul></form>"
																			+"</div>";
															});// each
																	// 8. 이전까지 뿌려졌던 데이터를 비워주고, <th>헤더 바로 밑에 위에서 만든 str을  뿌려준다.   
															 
														jb(".scrollLocation").append(str);
														jb.ajax({
															type : 'POST',
															url : '../cboard/comDownScrollrep',
															data : {
																comboardid : lastbno
															},
															success : function(data1) {
																	if (data1 != null) {
																		var rep_str="";
																		jb(data1).each(function() {
																			var crid = this.crid;
																			var comboardid = this.comboardid;
																			var	comreplytext = this.comreplytext;
																			var comrep_userid = this.comrep_userid;
																			var comrep_regdate = this.comrep_regdate; 
																			var profimg = this.profimg;
																			var company = this.company;
																			var first = this.first;
																			var last = this.last;
																			if(comrep_userid != sessionid && manager == sessionid){
																				var buttonadd="<input type='button' value='삭제' style='margin-right:10px' onclick='replyremove("+crid+");'>"
																			}else if(comrep_userid ==sessionid && manager == sessionid){
																				buttonadd="<input type='button' value='수정' style='margin-right:10px' onclick='replymod("+crid+");'>"
																						+"<input type='button' value='삭제' style='margin-right:10px' onclick='replyremove("+crid+");'>"
																			}else if(comrep_userid ==sessionid && manager != sessionid){
																				buttonadd="<input type='button' value='수정' style='margin-right:10px' onclick='replymod("+crid+");'>"
																						+"<input type='button' value='삭제' style='margin-right:10px' onclick='replyremove("+crid+");'>"
																			}else if(comrep_userid !=sessionid && manager != sessionid){
																				buttonadd ="  ";
																			}
																			if(manager==this.comrep_userid){
																				var color = "<font color='RED'>"+company+"("+first+last+")</font>"
																			}else{
																				color = company+"("+first+last+")";
																			}
																			
																			rep_str +="<div class='data_box_rep'>"	
																				+"<li><div><div><span class='personal_img'>"
																				+"<img src='../resources/img/"+profimg+"'  onclick=doImgPop('../resources/img/"+profimg+"')></span>"
																				+"<a href='../member/individualfollow?email="+comrep_userid+"'>"+ color +"</a></div></li>"
																				+"<li><br><div>"
																				+"<li>"+comreplytext+"</li>"
																				+buttonadd
																				+"<span style='float: right;'>"+comrep_regdate+"</span></div></li>"
																				+"<li class='re_reply"+crid+"'></li></div>";
																			
																		});
																	}
																	jb(".scrollLocation").append(rep_str);
																}
															}); 
														} 
													}
												});
												
											} else if (scrollpercent < 0.99) {
												scrollEventFlag = false;
											} else if (false) {
												lastScrollTop = currentScrollTop;
											}

										});// scroll event
					});
</script>
</html>