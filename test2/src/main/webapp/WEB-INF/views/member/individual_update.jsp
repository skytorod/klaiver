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
    
    <title>Klaiver 개인 수정 페이지</title>
    <link href="../resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='../resources/css/klaiver.css' />

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
    function viewfollower(email){
    	var email = email;
     	var popUrl = "../member/soloViewfollower?email="+email;
 		var windowW = 400;  // 창의 가로 길이
        var windowH = 400;  // 창의 세로 길이
        var left = Math.ceil((window.screen.width - windowW)/2);
        var top = Math.ceil((window.screen.height - windowH)/2);
		window.open(popUrl,"목록보기","l top="+top+", left="+left+", height="+windowH+", width="+windowW);
    }
    function viewfollowing(email){
    	var email = email;
     	var popUrl = "../member/soloViewfollowing?email="+email;
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
    <script>
    $(document).ready(function () {
        $("#submit").click(function() {
 			var form = $("#commonForm")[0];
 			
 			var formData = new FormData(form); 
 			// 파일
 			formData.append("testu1", $("#imageFile")[0].files[0]);
 			// 문자열
 			formData.append("testu2", "String 문자열");
 			// 숫자
 			formData.append("testu3", "12345");
 			// 내용
 			formData.append("testu4", "context");
 			
 			$.ajax({
 				url: './individual_update', 
 				// 앞에서 지정한 formData
 				data: formData, 
 				processData: false, 
 				contentType: false, 
 				type: 'POST',
 				success: function(data){ 
 					var id =$("#session").val();
 					location.href="../member/individual?keyword="+id;
 					} 
 				});
 		});
        $("#delimg").click(function() {
        	var fakeimg = $("#fakeimg").val();
        	var three = $("#three").val();
        	var result = confirm('삭제 하시겠습니까?');
        	if(result) { 
        		if(fakeimg=='noimg.jpg'){
            		alert("이미지가 없습니다.");
            		location.reload();
            	}else{
            		$.ajax({
         				url: './individual_delimg', 
         				type: 'POST',
         				data: {fakeimg :fakeimg,
         					three :three
         				},
         				success: function(data){ 
         					location.href="../member/individual_update";
         					} 
         				});
            	}	
        	}
        	
        });
 });
    </script>
        
        
    <script>
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
    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            var select = $('.select-script select');
            select.change(function () {
                var select_name = $(this).children('option:selected').text();
                $(this).siblings("label").text(select_name);
            });

        });
	</script>
	<script type="text/javascript">
	$(document).ready(function() {
		if ($("select[name='mc_number']").val() != "")
			getkid();

		$("select[name='mc_number']").on("change", getkid);
	});

	function getkid() {
		var selected_mc_number = $("select[name='mc_number']").val();
		
		$.getJSON("./category/individual_update/" + selected_mc_number, function(list) {
			var htmlStr = "<option value=''>---</option>";
			$(list).each(
					function(key, val) {
						console.log("val.kc_number: "+val.kc_number);
						console.log("val.ks_name :"+val.ks_name);
						htmlStr += "<option value='"+ val.ks_name +"'>"
								+ val.ks_name + "</option>";
					});
			$("select[name='industry']").html(htmlStr);

		});
	}
</script>
<script type="text/javascript">
$(document).ready(function() {
	if ($("select[name='country']").val() != "")
		getcid();

	$("select[name='country']").on("change",getcid);
});

function getcid() {
	var selected_ = $("select[name='country']").val();
	var cityvalue=$("#cityvalue").val();
	$.getJSON("./country/individual_update/"+selected_, function(clist) {
		var htmlStr = "<option value='"+cityvalue+"'>"+cityvalue+"</option>";
		$(clist).each(function(key, val) {
					console.log("val.city_number: " + val.city_number);
					console.log("val.city :" + val.city);
					htmlStr += "<option value='"+ val.city +"'>"
							+ val.city + "</option>";
				});
		$("select[name='city']").html(htmlStr);

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
  	  <input type="hidden" id="session" value="${sessionScope.login}">
        <div id="ci"><a href="../home"><img src="../resources/images/ci.png" alt="Klaiver"/></a></div>
        <form id="Integrated_search" name="Integrated_search" method="post">
            <input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군" />
            <input type="image" id="search_btn" src="../resources/images/search_btn.png" alt="검색"/>
        </form>
        <ul id="icon">
           <li class="icon1"><a href="../member/individual?keyword=${sessionScope.login}">
			<div class="cover"></div><img src="../resources/images/icon1.png" alt="개인페이지"/>
           <c:if test="${fn:length(updatenewpost) ne 0}">new</c:if>
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
            <!----개인페이지 공통---->
            <div id="top_bg">
                <div id="color_box"></div>    
            </div>
			<form id="commonForm" name="commonForm" method="post" encType="multipart/form-data" onsubmit="return false">
            <div id="individual_profile">
                <c:forEach items="${IndividualVO}" var="IndividualVO">
                <div id="individual_img">
                 
                     <img src="../resources/img/${IndividualVO.profimg}"  onclick="doImgPop('../resources/img/${IndividualVO.profimg}')"/> 
                     <br><input type="button" id="delimg"  value="이미지 삭제"><input type="hidden" id="three"  value="${IndividualVO.three_id}">
                </div> <!--개인프로필이미지-->
                <div id="individual_info"> <!--개인정보-->
                   
                    <div id="individual_name">${IndividualVO.first} ${IndividualVO.last}  ( ${IndividualVO.email} ) <a href="../member/individual_update"><img src="../resources/images/set.png" /></a></div>
                    <div id="individual_type">${IndividualVO.company} ( ${IndividualVO.title} ) </div>
                    <div id="individual_loc">${IndividualVO.country}, ${IndividualVO.city}</div>
                    <div>프로필사진 <input type="file" id="imageFile"  /><input type="hidden" id="fakeimg"name="fakeimg" value="${IndividualVO.profimg}">
                    </div>
                </div>
                
                
                <div id="commu">
                     <div id="follow_num_home1"> <!--팔로워/팔로잉-->
                        <p>개인 팔로워 <span id="follower_num_home"> <a href="#" onclick="viewfollower('${IndividualVO.email}');"> ${followercount} </a></span>명</p>
                        <p>개인 팔로잉 <span id="following_num_home"><a href="#" onclick="viewfollowing('${IndividualVO.email}');"> ${followingcount} </a></span>명</p>
                    </div>
                </div>
                </c:forEach>
            </div> 
            
            
            <!----개인 콘텐츠---->
            <div id="contents_wrap">
            
                <div id="_intro"> <!--개인소개 영역-->
                	
                    <div class="title_box_">자기소개</div>
                    <!--개인소개 편집-->
                    <c:forEach items="${IndividualVO}" var="IndividualVO">
					<textarea id="intro_edit" name="introduce" wrap="hard" onkeyup="resize(this)">${IndividualVO.introduce}</textarea>
                    </c:forEach>
                   
                 </div>
              
                <div id="editing"> <!--정보공유 영역-->
                    <div id="edit_tt">개인정보 편집</div>
                    <c:forEach items="${IndividualVO}" var="IndividualVO">
                        <div class="info_box">
                        	<input type="hidden" name="email" value="${IndividualVO.email}">
                            <input class="info half" type="text" id="F_name" name="first" value="${IndividualVO.first}" placeholder="성" />
                            <input class="info half" type="text" id="L_name" name="last" value="${IndividualVO.last}" placeholder="이름" />
                            <input class="info" type="text" id="company" name="company"  value="${IndividualVO.company}" placeholder="회사명" />
                            <input class="info" type="text" id="position" name="title"  value="${IndividualVO.title}" placeholder="직함" />
                            <div class="select-box select-script select-size2">
				                <label for="selectbox3">산업군을 선택하세요</label>
				              		 <select class="info half"  name="mc_number" id="mc_number" title="산업군">
										<option value="">산업군을 선택하세요</option>
										<c:forEach items="${list}" var="CategoryVO">
											
											<option value="${CategoryVO.mc_number}">${CategoryVO.mc_name}</option>
							  		  	 </c:forEach>
                   					 </select>
			                </div>
                            <div class="select-box select-script select-size2">
				                <label for="selectbox4">${IndividualVO.industry}</label>
				            	 <select class="info half"  name="industry" id="ks_id" title="산업" >
                        			<option value="${IndividualVO.industry}">${IndividualVO.industry}</option>
                   				 </select>
			                </div>
			                
                            <div class="select-box select-script select-size2">
				                <label for="selectbox1">${IndividualVO.country}</label>
				                <select class="info" name="country" id="country">
				                <option value="${IndividualVO.country}">${IndividualVO.country}</option>
							<c:forEach items="${clist}" var="CountryVO">
								<option value="${CountryVO.country}">${CountryVO.country}</option>
							</c:forEach>
                    		</select>
				                
			                </div>
			                <input type="hidden" id="cityvalue" value="${IndividualVO.city}">
                            <div class="select-box select-script select-size2">
				                <label for="selectbox2">${IndividualVO.city}</label>
				                <select class="info"  name="city" id="city_id">
				                <option value="${IndividualVO.city}">${IndividualVO.city}</option>
                    			</select>
			                </div>
			                
                    
			                
                        </div>
                        
                    </c:forEach>
                    <button class="previous" onclick="history.go(-1);" >이전</button>
                     <button class="save" id ="submit">저장</button>
                </div>
                </div> 
                
                 </form>
            </div>

        <!------------==============================  메세지 박스  ==============================------------>
        <!-- 		팔로우리스트  			-->
		<%@ include file="../follow/followlist.jsp" %>
		
<!-- 		팔로우리스트  			-->
    </section>
</body>
</html>