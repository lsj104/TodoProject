<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>게시판 메인</title>
    <%@ include file="/WEB-INF/inc/asset.jsp"%>
</head>

<style>
.table{
	width: 1300px;
	margin: 0 auto;
}

th, td {
	text-align: center;
}

th {
	background-color: #F9F9F9;
}

th:nth-child(1){ width: 200px;}
th:nth-child(2){ width: 980px;}
th:nth-child(3){ width: 150px;}
th:nth-child(4){ width: 80px;}

td:nth-child(1){ width: 200px;}
td:nth-child(2){ width: 980px;}
td:nth-child(3){ width: 150px;}
td:nth-child(4){ width: 80px;}

#title {
	text-align: left;
}

#titleBtn {
	margin-bottom: 70px;
}

.display-4 {
	text-align: center;
	mragin-bottom: 30px;
	text-decoration: underline;
}

.btn .btn-inverse-danger{
	float: right;
	width: 70px;
	height: 30px;
	padding: 7px;
}

.btn-inverse-danger:not(.btn-inverse-light) {
	background-color: #f2edf3;
	color: #6c757d;
}

.btn-inverse-danger:hover {
	border-color: #e7e1e8;
}

.col-lg-11 {
	margin: 0 auto;
}

.card {
	padding-bottom: 50px;
}


a {
	text-decoration: none;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: underline;
}

.input-group {
	width: 300px;
	float: right;
}

#write {
	margin-left: 1300px;
	width: 80px;
	height: 30px;
	padding: 0;
}

#column {
	width: 80px;
    display: inline-block;
    margin-left: 630px;
}

form {
	display: flex;
	margin-left: 480px;

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
            <div class="col-lg-11 grid-margin stretch-card">
              <div class="card">
                <div class="card-body">
               	<div id="titleBtn">
                  <h1 class="display-4">QnA</h1>
                  <div class="form-group">
                  <form method="GET" action="/todo/board/boardmain.do">
                  	<!-- 검색 -->
                      	<select class="form-control" name="column" id="column">
    						<option value="title">제목</option>
    						<option value="content">내용</option>
						</select>
                    <div class="input-group">
                      <input type="text" class="form-control" placeholder="검색할 내용을 입력해주세요!" aria-label="Recipient's username" aria-describedby="basic-addon2" name="word" id="word">
                      <div class="input-group-append">
                      <button type="submit" class="btn btn-sm btn-inverse-danger" type="button">Search</button>
                      </div>
                    </div>
                 </form>
                  </div>
					</div>
                  <table class="table table-hover">
                    <thead>
                      <tr>
                        <th style="width: 200px;">
                          NickName
                        </th>
                        <th>
                          Title
                        </th>
                        <th style="width: 150px;">
                          Date
                        </th>
                        <th style="width: 80px;">
                          Read
                        </th>
                      </tr>
                    </thead>
                    <tbody>
                    
                    <c:if test="${map.isSearch == 'y'}">
			      	<div style="text-align: center; margin-bottom: 10px;">
			      		'${map.word}'(으)로 검색한 결과 총 ${list.size()}개의 게시물이 발견되었습니다. 
			      	</div>
			      	</c:if>
			      	
			      	<c:if test="${list.size() == 0}">
		      		<tr>
		      			<td colspan="5">게시물이 없습니다.</td>
		      		</tr>
		      		</c:if>
    		
    		
                    <c:forEach items="${list}" var="dto">
                            <tr>
                              <td style="width: 200px;">
                                <img src="/todo/asset/images/profile/${dto.image}">
                                ${dto.nickname}
                              </td>
                              <td id="title">
                                <a href="/todo/board/view.do?seq=${dto.seq}">${dto.title}</a>
                              </td>
                              <td style="width: 150px;">
                                ${dto.qdate}
                              </td>
                              <td style="width: 80px;">
                                ${dto.readcount}
                              </td>
                            </tr>
                   </c:forEach>
                    </tbody>
                  </table>
                </div>
                    <button type="button" onclick="location.href='boardadd.do'" class="btn btn-inverse-danger mr-2" id="write">글쓰기</button>
              </div>
              
            </div>

          </div>
        </div>
        <!-- <!? main-panel ends ?> -->
    </div>

</div>

	<script>
	
		<c:if test="${map.isSearch == 'y'}">
		$('#column').val('${map.column}');
		$('#word').val('${map.word}');
		</c:if>
		
		
		function move() {
			
			location.href = '/todo/board/boardmain.do?page=' + $(event.target).val();
			
		}
		
		$('#selpage').val(${nowPage});
	
	</script>

</body>

</html>

