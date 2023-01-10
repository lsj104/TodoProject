<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>게시판 메인</title>
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
		margin-top: 50px;
		margin-left: 80px;
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
    	margin: 0 auto;
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
		          <div class="row">
			<div class="col-lg-6 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
                  <h1 class="card-title"><i class="mdi mdi-lead-pencil"></i> Edit <i class="mdi mdi-lead-pencil"></i></h1>
                  <form class="forms-sample" method="GET" action="/todo/board/boardmain.do">
                    <div class="form-group row">
                      <label for="exampleInputUsername2" class="col-sm-3 col-form-label">제목</label>
                      <div class="col-sm-9">
                        <input type="text" class="form-control" name="title" id="title" placeholder="Title" value="${dto.title}" required>
                      </div>
                    </div>
                    <div class="form-group row">
                      <label for="exampleInputEmail2" class="col-sm-3 col-form-label">내용</label>
                      <div class="col-sm-9">
                      	<textarea name="content" id="content" class="form-control" rows="10" required placeholder="Content">${dto.content}</textarea>
                      </div>
                    </div>
                    
                    <div id="btn2">
	                    <button type="submit" class="btn btn-inverse-danger mr-2">수정하기</button>
	                    <button type="button" class="btn btn-light" onclick="location.href='/todo/board/view.do?seq=${dto.seq}';">돌아가기</button>
                    </div>
                    <input type="hidden" name="seq" value="${dto.seq}">
                  </form>
                </div>
              </div>
            </div>
		</div>
		</div>
		           <%@ include file="/WEB-INF/inc/footer.jsp" %>
		</div>
		            
		          </div>
		        </div>
        <!-- <!? main-panel ends ?> -->

	<script>
	
	</script>

</body>

</html>

















