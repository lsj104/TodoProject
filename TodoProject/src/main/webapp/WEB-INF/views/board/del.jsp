<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>삭제 페이지</title>
    <%@ include file="/WEB-INF/inc/asset.jsp" %>
</head>
<style>

	.col-lg-6 {
		margin: 0 auto;
	}
	
	.card .card-title {
		text-align: center;
		margin-bottom: 70px;
	}
	
	button[type="submit"] {
		margin-left: 120px;
	}

	#btn2 {
		margin-top: 50px
	}
	
	#content {
		height: 400px;
    	width: 500px;
	}
	
	#title {
		width: 500px;
	}
	
	.col-sm-9 {
    flex: 0 0 80%;
    max-width: 80%;
	}
	
	.btn-inverse-danger:not(.btn-inverse-light) {
		background-color: #f2edf3;
		color: black;
	}
	
	.btn-inverse-danger:hover {
		border-color: #e7e1e8; 
		background-color: #e7e1e8;
	}
	
</style>

<body>
		<div class="container-scroller">
		    <!-- partial:../../partials/_navbar.html -->
		    <%@include file="/WEB-INF/inc/navbar.jsp" %>
		    <!-- partial -->
		    <div class="container-fluid page-body-wrapper">
		        <!-- partial:../../partials/_sidebar.html -->
		        <%@include file="/WEB-INF/inc/sidebar.jsp" %>
		        <!-- partial -->
		        <div class="main-panel">
		        <div class="content-wrapper">
		          <div class="page-header">
		            <h3 class="page-title">
		              게시판 목록
		            </h3>
		            <nav aria-label="breadcrumb">
		              <ol class="breadcrumb">
		                <li class="breadcrumb-item"><a href="#">기능 미구현</a></li>
		              </ol>
		            </nav>
		          </div>
		          <div class="row">
		            
		            <div class="col-lg-12 grid-margin stretch-card">
		              <div class="card">
		                <div class="card-body">
		            
		            <section>
			
			<h2>Board</h2>
			
			<form method="GET" action="/todo/board/delok.do">
						
			<div class="btns">
				<input type="button" value="돌아가기" onclick="location.href='/todo/board/boardmain.do';">
				<button type="submit">
					<i class="fa-solid fa-trash-can"></i>
					삭제하기
				</button>
			</div>
			
			<input type="hidden" name="seq" value="${seq}">
			</form>
			
			
		</section>
		
		</div>
		</div>
		</div>
		            
		          </div>
		        </div>
        <!-- <!— main-panel ends —> -->
    </div>

</div>
	
	<script>
		
		
	
	</script>

</body>

</html>
















