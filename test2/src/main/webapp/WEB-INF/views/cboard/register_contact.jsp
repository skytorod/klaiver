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
    
    <title>Klaiver 회사정보 입력페이지(contact)</title>
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
        <form id="Integrated_search" name="Integrated_search" method="post">
            <input type="text" id="search" name="qa_text" placeholder="ex)기업명, 대표자명, 이메일, 산업군" />
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
            <!----기업등록 공통---->
            <div id="top_bg">
                <div id="color_box"></div>    
            </div>

            <div id="company_profile">
                <div id="company_img"></div> <!--기업프로필이미지-->
                <div id="company_info"> <!--기업정보-->
                    <div id="company_name">회사등록</div>
                    <div id="company_type">4.Contact</div>

                </div>
            </div>
        </div>   
            <!----기업 콘텐츠---->
           
        <div id="join_wrap">
            <form id="form4" name="joinForm" method="post" >
                <div class="data_box">
                    <div class="array">
                        <span>전화번호</span>
                        <input type="hidden" name="userid" value="${sessionScope.login}" />
                        <input class="info_enter" type="text" id="input21" name="phonenumber" />
                    </div> 
                    <div class="array">
                        <span>Fax</span>
                        <input class="info_enter" type="text" id="input22" name="fax" />
                    </div>
                    <div class="array">
                        <span>Email</span>
                        <input class="info_enter" type="text" id="input23" name="cemail" />
                    </div>
                    <div class="array">
                        <span>주소</span>
                        <input class="info_enter" type="text" id="input24" name="address" />
                    </div>
                    <div class="array">
                        <span>조직도</span>
                        <input class="info_enter" type="text" id="input25" name="organizationChart" />
                    </div>                  
                
                    <button class="next_page" type="submit" >저장</button>
                </div>
            </form>
        </div>
    </section>
</body>
</html>