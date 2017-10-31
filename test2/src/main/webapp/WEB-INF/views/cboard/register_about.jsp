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
    
    <title>Klaiver 회사정보 입력페이지(about)</title>
    <link href="../resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='../resources/css/join.css' />

    <script src="../resources/js/jquery-1.11.2.js"></script>
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
         function resize(obj) {
             obj.style.height = "1px";
             obj.style.height = (20 + obj.scrollHeight) + "px";
             $("body").scrollTop($(document).height());
         }
    </script>
<script type="text/javascript">
	$(document).ready(function() {
		if ($("select[name='mc_number']").val() != "")
			getid();

		$("select[name='mc_number']").on("change", getid);
	});

	function getid() {
		var selected_mc_number = $("select[name='mc_number']").val();

		$.getJSON("../cboard/register_about/" + selected_mc_number, function(list) {
			var htmlStr = "<option value=''>업종</option>";
			$(list).each(
					function(key, val) {
						console.log("val.kc_number: " + val.kc_number);
						console.log("val.ks_name :" + val.ks_name);
						htmlStr += "<option value='"+ val.ks_name +"'>"
								+ val.ks_name + "</option>";
					});
			$("select[name='businessname']").html(htmlStr);

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
        <div id="ci"><a href="../home"><img src="../resources/images/ci.png" alt="Klaiver"/></a></div>
        <form id="Integrated_search" name="Integrated_search" method="get" action="#">
            <input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군"  />
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
            <!----기업등록 공통---->
            <div id="top_bg">
                <div id="color_box"></div>    
            </div>

            <div id="company_profile">
                <div id="company_img"></div> <!--기업프로필이미지-->
                <div id="company_info"> <!--기업정보-->
                    <div id="company_name">About</div>
                    <div id="company_type"></div>

                </div>
            </div>
        </div>    
            <!----기업 콘텐츠---->
           
        <div id="join_wrap">
            <form id="form2" name="joinForm" method="post">
                <div class="data_box">
                    <div class="array">
                        <span>기업명</span>
                        <input type="hidden" name="userid"
							value="${sessionScope.login}" /> 
                        <input class="info_enter" type="text" id="input1" name="compname_kr" />
                    </div> 
                    <div class="array">
                        <span>영문 기업명</span>
                        <input class="info_enter" type="text" id="input2" name="compname_en" />
                    </div>
                    <div class="array">
                        <span>기업형태</span>
                        <input class="info_enter" type="text" id="input3" name="businessType" placeholder="ex. 개인, 상장, 대중소기업" />
                    </div>
                    <div class="array">
                        <span>대표자명</span>
                        <input class="info_enter" type="text" id="input4" name="repname" />
                    </div>
                    <div class="array">
                    
                        <span>업종명</span>
                        
							<select class="info_enter" name="mc_number" id="mc_number">
								<option value="">산업군</option>
								<c:forEach items="${list}" var="CategoryVO">
									<option value="${CategoryVO.mc_number}">${CategoryVO.mc_name}</option>
								</c:forEach>
							</select> 
                    </div>
							
                    <div class="array">
							<select class="info_enter" name="businessname" id="ks_id">
								<option value=""></option>
							</select>
                    </div>
                    <div class="array">
                        <span>주요상품</span>
                        <input class="info_enter" type="text" id="input6" name="main_product" />
                    </div>
                    <div class="array">
                        <span>인증현황</span>
                        <input class="info_enter" type="text" id="input7" name="certificationStatus" />
                    </div>
                    <div class="array">
                        <span>종웝원 수</span>
                        <input class="info_enter" type="text" id="input8" name="employees" />
                    </div>
                    <div class="array">
                        <span>웹페이지</span>
                        <input class="info_enter" type="text" id="input9" name="homepage" />
                    </div>
                    <div class="array">
                        <span>사업장 현황</span>
                        <input class="info_enter" type="text" id="input13" name="businessStatus" placeholder="ex. 지사, 대리점 현황" />
                    </div>
                    <div class="array">
                        <div class="h_array">
                            <span>매출액</span>
                            <input class="h_infoBox" type="text" id="input14" name="take" />
                        </div>
                        <div class="h_array">
                            <span class="t_p">자본금</span>
                            <input class="h_infoBox" type="text" id="input15" name="capital" />
                        </div>
                    </div>
                    <div class="array">
                        <div class="h_array">
                            <span>당기순이익</span>
                            <input class="h_infoBox" type="text" id="input16" name="netincome" />
                        </div>
                        <div class="h_array">
                            <span class="t_p">설립일</span>
                            <input class="h_infoBox" type="text" id="input17" name="establish" />
                        </div>
                    </div>
                    <span class="array">회사소개글</span>
                    <textarea id="intro_company2" name="AboutUs" wrap="hard" onkeyup="resize(this)"> </textarea>
                
                    <button class="next_page" type="submit" >저장</button>
                </div>
            </form>
        </div>
    </section>
</body>
</html>