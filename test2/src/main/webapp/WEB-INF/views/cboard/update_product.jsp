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
    
    <title>Klaiver 회사정보 입력페이지(product)</title>
    <link href="../resources/images/favicon.png" rel="shortcut icon" type="image/png" />
    <link rel='stylesheet' type='text/css' href='http://fonts.googleapis.com/css?family=Noto+Sans' />
    <link rel='stylesheet' type='text/css' href='../resources/css/klaiver.css' />

    <script src="../resources/js/jquery-1.11.2.js"></script>
          <script>
         function resize(obj) {
             obj.style.height = "1px";
             obj.style.height = (20 + obj.scrollHeight) + "px";
             $("body").scrollTop($(document).height());
         }
    </script>
 <script>
         $(document).ready(function () {
        	 $("#add").click(function() {
        		var userid=$("#userid").val();
     			var form = $("#form1")[0];
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
     				url:'./update_product', 
     				// 앞에서 지정한 formData
     				data: formData, 
     				processData: false, 
     				contentType: false, 
     				type: 'POST',
     				success: function(data){ 
     					opener.parent.location.reload();
    					self.close(); 
     					} 
     				});
     		});
         });
</script>
</head>
<body>

    <!------------==============================  콘텐츠 영역  ==============================------------>
    <section id="container"> 
		<div id="contents_wrap1">
      	 <form id="form1" method="post" encType="multipart/form-data" role="form" name="joinForm" >
            	<div>
					<div>
						<div class='info_enter1'>
							<img src="../resources/img/${k_product.image}" onclick="doImgPop('../resources/img/${k_product.image}')"
							style="width: 300px;"/>
							<input type="hidden" name="fakeimg" value="${k_product.image}">
							<input type="hidden" id="userid" name="userid" value="${k_product.userid}">
							<input type="hidden" id="pid" name="pid" value="${k_product.pid}">
						</div>
						<div class='info_enter1'>
							<input type="file" id="imageFile">
						</div>
						<div class='info_enter1'>
							<input type="text" class='info_enter' placeholder='제품명'name="product" id="product" value="${k_product.product}">
						</div>
						<div class='info_enter1'>
							<input type="text" class='info_enter' placeholder='제품영문명'name="product_en" id="product_en" value="${k_product.product_en}">
						</div>
						<div class='info_enter1'>
							<input class='info_enter' placeholder='용도' type="text" class='divide_b' name="usage"id="usage"value="${k_product.usage}">
						</div>
						<div class='info_enter1'>
							<input class='info_enter' placeholder='제품코드' type="text" id="pcod" name="pcod"value="${k_product.pcod}">
						</div>
						<div class='info_enter1'>설명</div>
						<div class='info_enter1'>
							<textarea id='intro_company' wrap="hard" onkeyup='resize(this)' name="pinfo">${k_product.pinfo}</textarea>
						</div>
					</div>
					<input type="button"value="등록" id="add">
				</div>
			</form>
		</div>
    </section>
</body>
</html>