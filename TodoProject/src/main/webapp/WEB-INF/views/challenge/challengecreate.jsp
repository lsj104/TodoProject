<%@ page import = "java.util.*" %>
<%@ page import = "com.test.todo.challenge.CategoryDTO" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title> Make your Challenge!</title>
    <!-- plugins:css -->
    <%@include file="/WEB-INF/inc/asset.jsp" %>
</head>
<style>
	/*body {*/
	/*	background-color: #f1edf3;*/
	/*	display: flex;*/
	/*}*/

	.form-popup {
		background-color: white;
		box-sizing: border-box;
		color: black;
		display: inline-block;
		height: 500px;
		font-size: 14px;
		position: absolute;
		text-align: center;
		top: 5%;
		width: 800px;
		position: relative;
		margin-top: 100px;
		margin-left: 400px;
		border-radius: 5px;
	}

	.form-popup-header {
		font-size: 28px;
		padding: 25px 5px 5px 5px;
	}


	input[type="text"], input[type="submit"], input[type="number"] {
		background-color: white;
		border: 1px solid #f1edf3;
		/*border: 1px solid #ac70f7;*/
		border-radius: 3px;
		color: grey;
		display: block;
		margin: auto;
		margin-bottom: 5px;
		margin-top: 5px;
		padding: 5px;
		width: 30%;
	}

	label {
		font-size: 16px;
	}

	.btn-submit {
		background-color: #f1edf3;
		color: black;
		border: 0px;
		border-radius: 3px;
		font-weight: 900;
		margin: 20px;
		padding: 10px;
		width: 30%;
	}

	.btn-submit:hover {
		background-color: #f1edf3;
		color: gray;
	}

	input:focus {
		outline: none;
	}

	select:focus {
		outline: none;
		box-shadow: white;
	}

	h1 {
		font-size: 13px;
		margin-top: 10px;
	}

</style>

<script>
	$(function () {
		$('#save').on('click', function () {
			if ($("#title").val() == "") {
				alert("챌린지 이름을 입력해 주세요.");
				$("#title").focus();
				return false;
			}
		})
	});

	$(function () {
		$('#save').on('click', function () {
			if ($("#mission").val() == "") {
				alert("공동 미션을 입력해 주세요.");
				$("#mission").focus();
				return false;
			}
		})
	});

	$(function () {
		$('#save').on('click', function () {
			if ($("#category").val() == "") {
				alert("카테고리를 입력해 주세요.");
				$("#category").focus();
				return false;
			}
		})
	});

</script>
<body>
<%
	List<CategoryDTO> categorys = (List<CategoryDTO>) request.getAttribute("categorys");

%>

<div class="container-scroller">
   <%@include file="/WEB-INF/inc/navbar.jsp" %>
    
    <div class="container-fluid page-body-wrapper">
        <!-- sidebar -->
        <%@include file="/WEB-INF/inc/sidebar.jsp" %>
        <!-- partial -->
        <div class="main-panel">
        <!--메인  -->
            <div class="content-wrapper">
				<div class="form-popup-container" id="login-form-popup-container">
					<form id="frm" class="form-popup" action="challengecreateok.do" method="post">
						<p class="form-popup-header" id="login-form-popup-header">
							Create Challenge!
						</p>

						<h1>챌린지 이름</h1>
						<input type="text" id="title" name="title" style="margin-top: 10px"/>

						<h1>기간</h1>
						<input type="number" id="period" value="1" name="period" style="margin-top: 10px"/>

						<h1>인원</h1>
						<input type="number" id="membercnt" value="2" name="membercnt" style="margin-top: 10px"/>

						<h1>카테고리</h1>
						<select id="category" name="category" class="category" style="display: inline; width: 30%; height:30px; font-size:13px; border-color: #f1edf3; text-align: center">
							<option value="">선택해주세요.</option>
							<c:forEach var="category" items="${categorys}">
								<option value="${category.seq}">${category.name}</option>
							</c:forEach>
						</select>

						<h1 style="margin-top: 10px">공동 미션</h1>
						<input type="text" id="mission" name="mission" style="margin-top: 10px"/>

						<div>
							<button type="submit" class="btn-submit" id="save" style="margin-top: 10px; ">
								챌린지 만들기
							</button>
						</div>

					</form>

				</div>
            </div>
           
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <%@include file="/WEB-INF/inc/footer.jsp" %>
             
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
       
    </div>
</div>


<script src="/vendors/js/vendor.bundle.base.js"></script>
<script src="/vendors/js/vendor.bundle.addons.js"></script>

<script src="/js/off-canvas.js"></script>
<script src="/js/misc.js"></script>

</body>

</html>
