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

<title>Klaiver 개인페이지</title>
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
         $(document).ready(function () {
             $('#icon li').mouseenter(function () {
                 $(this).find('.cover').stop().fadeIn(200);
             });

             $('#icon li').mouseleave(function () {
                 $(this).find('.cover').stop().fadeOut(200);
             });
         });
    </script>
<script>
    function viewfollower(email){
    	var email = email;
     	var popUrl = "../cboard/soloViewfollower?email="+email;
 		var windowW = 400;  // 창의 가로 길이
        var windowH = 400;  // 창의 세로 길이
        var left = Math.ceil((window.screen.width - windowW)/2);
        var top = Math.ceil((window.screen.height - windowH)/2);
		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
    }
    function viewfollowing(email){
    	var email = email;
     	var popUrl = "../cboard/soloViewfollowing?email="+email;
 		var windowW = 400;  // 창의 가로 길이
        var windowH = 400;  // 창의 세로 길이
        var left = Math.ceil((window.screen.width - windowW)/2);
        var top = Math.ceil((window.screen.height - windowH)/2);
		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
    }
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
			url: './insert', 
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
    function strReplace(subject, search, replace) {
        return subject.split(search).join(replace);
    };
        $(document).ready(function () {
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
         function resize(obj) {
             obj.style.height = "1px";
             obj.style.height = (20 + obj.scrollHeight) + "px";
         }
         function autoDate() {
      		var tDay = new Date();
      		var tMonth = tDay.getMonth() + 1;
      		var tDate = tDay.getDate();
      		var tHour = tDay.getHours();
      		var tMinutes = tDay.getMinutes();
      		if (tMonth < 10)
      			tMonth = "0" + tMonth;
      		if (tDate < 10)
      			tDate = "0" + tDate;
      		document.getElementById("regdate").value = tDay.getFullYear() + "년"
      				+ tMonth + "월" + tDate + "일" + tHour + ":" + tMinutes;
      	}
      	function addLoadEvent(func) {
      		var oldonload = window.onload;
      		if (typeof window.onload != 'function') {
      			window.onload = func;
      		} else {
      			window.onload = function() {
      				if (oldonload) {
      					oldonload();
      				}
      				func();
      			}
      		}
      	}

      	addLoadEvent(function() {
      		autoDate();
      	}); 

    </script>
<script>
  	function mod(str){
  		 event.preventDefault();
      	 var sbid = str;
      	var popUrl = "../member/individualBupdate?sbid="+sbid;
		var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
		window.open(popUrl, "수정page", popOption);
     	}
      	function remove(idx){
      		var sbid=idx;
      		var result = confirm('삭제 하시겠습니까?');
      		if(result) { 
      			$.ajax({
   				url : "../member/remove?sbid="+sbid,
   				type : "post",
   				data : {
   					sbid : idx
   				}
   			});
      			location.reload();
      		}

      		
      	}
      	function replymod(str){
       var rid = str;
   	var popUrl = "../member/updaterep?rid="+rid;
		var popOption = "width=1000, height=360, resizable=no,location=no, scrollbars=no, status=yes;";
		window.open(popUrl, "수정page", popOption);
       }
      	function replyremove(idx){
      		var result = confirm('삭제 하시겠습니까?');
      		if(result) { 
      			$.ajax({
   				url : "../member/replyremove?rid="+idx,
   				type : "post",
   				data : {
   					rid : idx
   				}
   			});
      			location.reload();
      		}

      		
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
					href="../cboard/news?userid=${sessionScope.login}"><div
							class="cover"></div> <img src="../resources/images/icon1.png"
						alt="개인페이지" />
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
			<!----개인페이지 공통---->
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
						<div id="individual_name">
						<c:if test="${k_aboutVO eq null && k_contactVO eq null}">${IndividualVO.email}<br>${IndividualVO.username}<br></c:if>
						<c:if test="${k_aboutVO ne null || k_contactVO ne null}">
						${k_aboutVO.compname_kr}<br>
						${k_aboutVO.compname_en}<br>
						${k_contactVO.address}
						</c:if>
						<a href="../cboard/update_home?userid=${sessionScope.login}"><img
								src="../resources/images/set.png" /></a>
						</div>
					<ul id="page_move">
						<li class="on"><a href="news?userid=${IndividualVO.email}">Home</a></li>
						<li><a href="check_about?userid=${IndividualVO.email}">About</a></li>
						<li><a href="readPage_product?userid=${IndividualVO.email}">Product</a></li>
						<li><a href="check_contact?userid=${IndividualVO.email}">Contact</a></li>
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
					<c:forEach items="${IndividualVO}" var="IndividualVO">
					<p>
						팔로워 <span id="follower_num_home"> <a href="#"
							onclick="viewfollower('${IndividualVO.email}');">${followercount}</a>
						</span>
					</p>
					<p>
						팔로잉 <span id="following_num_home"> <a href="#"
							onclick="viewfollowing('${IndividualVO.email}');">${followingcount}</a>
						</span>
					</p>
					</c:forEach>
				</div>

			</div>

			<!----개인 콘텐츠---->
			<div id="contents_wrap">
				<form method="post" id="intro" action="../member/individual">
					<div id="_intro">
						<!--개인소개 영역-->
						<c:forEach items="${IndividualVO}" var="IndividualVO">
							<div class="title_box_">
								회사이력<input type="hidden" name="email" id="userid"
									value="${sessionScope.login}" />
							</div>
							<div id="personal_intro">
								<c:if test="${!empty IndividualVO.introduce}">
								${IndividualVO.introduce}
                    			</c:if>
							</div>
						</c:forEach>
					</div>
				</form>
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
										<li class="name"><input type="hidden" name="warea"
											id="warea" value="${IndividualVO.email}" /> <input
											type="hidden" name="readid" id="readid"
											value="${sessionScope.login}" /> <input type="hidden"
											name="userid" id="userid" value="${sessionScope.login}" /> <a
											href="../member/individual?keyword=${sessionScope.login}">
												${sessionidName.username}</a></li>
									</c:forEach>
									<li><textarea class="record" name="content" wrap="hard"
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



					<!--등록된 컨텐츠 박스-->
					<c:forEach items="${scrolllist}" var="ScrollBoardVO"
						varStatus="status">
						<div class="data_box">
							<ul class="title_box">
								<li>
									<div class="scrolling" data-sbid="${ScrollBoardVO.sbid}">
										<input type="hidden" id="ssbid" value="${ScrollBoardVO.sbid}">
									</div>
								</li>
								<li class="name"><span class="personal_img"><img
										src="../resources/img/${ScrollBoardVO.profimg}"
										onclick="doImgPop('../resources/img/${ScrollBoardVO.profimg}')" /></span>
									<a
									href="../member/individualfollow?email=${ScrollBoardVO.userid}">${ScrollBoardVO.company}
										(${ScrollBoardVO.first}${ScrollBoardVO.last}) </a> <input
									type="hidden" id="idx${ScrollBoardVO.sbid}"
									value="${ScrollBoardVO.sbid}" />
									<button class="enter" name="edit_save"
										onclick="remove(${ScrollBoardVO.sbid})">삭제</button> <c:if
										test="${ScrollBoardVO.userid eq sessionScope.login}">

										<button class="enter" name="edit_save"
											onclick="mod(${ScrollBoardVO.sbid});">수정</button>
									</c:if></li>
								<li class="hashtag"><span>${ScrollBoardVO.regdate}</span></li>
								<c:if test="${!empty ScrollBoardVO.image}">
									<li class="contents_pt"><img
										src="../resources/img/${ScrollBoardVO.image}"
										onclick="doImgPop('../resources/img/${ScrollBoardVO.image}')" /></li>
								</c:if>
								<li class="contents_pt"><br></li>
								<li class="cts">${ScrollBoardVO.content}</li>

								<li><div class="line"></div></li>
							</ul>
							<form class="repForm" id="repForm" method="POST"
								action="../member/reply">
								<ul class="title_box">
									<li><input type="hidden" name="boardid" id="boardid"
										value="${ScrollBoardVO.sbid}"> <input type="hidden"
										name="rep_userid" id="rep_userid"
										value="${sessionScope.login}"> <textarea
											class="record" name="replytext" id="replytext"
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
									<c:if test="${ScrollBoardVO.sbid == replylist.boardid}">
										<li><div>
												<span class="personal_img"><img
													src="../resources/img/${replylist.profimg}"
													onclick="doImgPop('../resources/img/${replylist.profimg}')" /></span>
												<a
													href="../member/individualfollow?email=${replylist.rep_userid}">
													${replylist.company}(${replylist.first}${replylist.last})</a>

											</div> <br></li>
										<li>${replylist.replytext}</li>
										<li><div>
												<c:if test="${replylist.rep_userid eq sessionScope.login}">
													<input type="button" value="수정" style="margin-right: 10px"
														onclick="replymod(${replylist.rid});">

												</c:if>
												<input type="button" value="삭제" style="margin-right: 10px"
													onclick="replyremove(${replylist.rid});">
												<c:if test="${replylist.rep_userid ne sessionScope.login}">
													&nbsp;
												</c:if>
												<span style="float: right;">${replylist.rep_regdate}</span>
											</div></li>
									</c:if>
									<li class="re_reply${replylist.rid}"></li>

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
											if (scrollpercent >= 0.99999
													&& !scrollEventFlag) {
												console.log(scrollpercent);
												scrollEventFlag = true;
												var lastbno = jb(
														".scrolling:last")
														.attr("data-sbid");
												var sessionid = jb("#userid").val();
												var id = jb("#id").val();
												console.log(lastbno);
												console.log(sessionid);
												
												
												jb.ajax({
															type : 'POST',
															url : '../member/DownScroll',
															headers : {
																"Content-Type" : "application/json",
																"X-HTTP-Method-Override" : "POST"
															},
															dataType : 'json',
															data : {
																sbid : lastbno,
																sessionid : sessionid
															},
															success : function(data) {
																
																var str = "";
																var idx=0;
																if (data != "") {
																	jb(data).each(function() {
																		var bid = this.sbid;
																		console.log(this);
																		console.log(this.sbid);
																		var id = jb("#userid").val();
																		idx++;
																		if(this.image != null && this.image != "" && this.image != "undefined"){
																			var imageStr = "<img src='../resources/img/"+this.image+"' onclick=doImgPop('../resources/img/"+this.image+"')>";
																		}else{
																			imageStr="<br>";
																		}
																		if(id==this.userid){
																			var btnstr = "<button type='submit' class='enter' name='edit_save' onclick='mod("+this.sbid+");'>수정</button>";
																		}else{
																			btnstr ="";
																		}
																		str += "<div class='data_box'>"	
																			+"<ul class='title_box'>"
																			+"<li>"
																			+ "<div class='scrolling' data-sbid='" + this.sbid +"'>"
																			+ "<input type='hidden'id='ssbid' value='" + this.sbid +"'>"
																			+ "</div></li>"
																			+ "<li class='name'>"
																			+"<span class='personal_img'><img src='../resources/img/" + this.profimg +"'   onclick=doImgPop('../resources/img/"+this.profimg+"')></span>"
																			+"<a href='../member/individualfollow?email="+this.userid+"'>"
																			+this.company+"("+this.first+this.last+")"
																			+ "</a>"
																			+"<button type='submit' class='enter' name='edit_save' onclick='remove("+this.sbid+");'>삭제</button>"
																			+ btnstr
																			+"</li>"
																			+ "<li class='hashtag'><span>"
																			+ this.regdate
																			+ "</span></li>"
																			+ "<li class='contents_pt'>"
																			+ imageStr
																			+ "</li>"
																			+"<li class='contents_pt'></li>"
																			+ "<li class='cts'>"
																			+ this.content
																			+ "</li>"
																			+"<form id='repForm' method='POST' action='../member/reply'>"
																			+"<ul class='title_box'>"
																			+"<li><input type='hidden' name='boardid' id='boardid' value='" + this.sbid +"'>"
																			+"<input type='hidden' name='rep_userid' id='rep_userid' value='"+sessionid+"'>"
																			
																			+"<textarea class='record comment_re' name='replytext' id='replytext' onkeyup='resize(this)'onkeypress='if(event.which==13)form.submit()'></textarea></li>"
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
															url : '../member/DownScrollrep',
															data : {
																boardid : lastbno,
																sessionid : sessionid
															},
															success : function(data1) {
																	if (data1 != null) {
																		var rep_str="";
																		jb(data1).each(function() {
																			var rid = this.rid;
																			var boardid = this.boardid;
																			var	replytext = this.replytext;
																			var rep_userid = this.rep_userid;
																			var rep_regdate = this.rep_regdate; 
																			var profimg = this.profimg;
																			var company = this.company;
																			var first = this.first;
																			var last = this.last;
																			if(id == rep_userid){
																				var buttonadd="<input type='button' value='수정' style='margin-right:10px' onclick='replymod("+rid+");'>"
																				
																			}else{
																				buttonadd="&nbsp;";
																			}
																			rep_str +="<div class='data_box_rep'>"	
																				+"<li><div><div><span class='personal_img'>"
																				+"<img src='../resources/img/"+profimg+"'  onclick=doImgPop('../resources/img/"+profimg+"')></span>"
																				+"<a href='../member/individualfollow?email="+rep_userid+"'>"+company+"("+first+last+")</a></div></li>"
																				+"<li><br><div>"
																				+"<li>"+replytext+"</li>"
																				+buttonadd
																				+"<input type='button' value='삭제' style='margin-right:10px' onclick='replyremove("+rid+");'>"
																				+"<span style='float: right;'>"+rep_regdate+"</span></div></li>"
																				+"<li class='re_reply"+rid+"'></li></div>";
																			
																		});
																	}
																	jb(".scrollLocation").append(rep_str);
																}
															}); 
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