<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
   content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>글 보기</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
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

#title {
	width: 500px;
}

.col-sm-9 {
	flex: 0 0 80%;
	max-width: 80%;
}

.btn-inverse-danger:not(.btn-inverse-light) {
	background-color: #f2edf3;
	color: #6c757d;
}

.btn-inverse-danger:hover {
	border-color: #e7e1e8;
}

#content {
	border-bottom: 1px solid #ebedf2;
	border-top: 1px solid #ebedf2;
	padding: 40px;
	margin-bottom: 30px;
}

.line-h-24 {
	font-size: 13px;
	color: #969696;
	margin-bottom: 30px;
}

#btn2 {
	text-align: right;
	margin: 0;
}

#btn2 button, #back {
	width: 70px;
	height: 30px;
	padding: 0;
	margin-bottom: 40px;
}

p {
	margin: 0;
}

.table {
	border-bottom: 1px solid #ebedf2;
	width: 1000px;
    margin: 0 auto;
    margin-top: 50px;
}

#back {
	margin: 0;
}

#date {
	font-size: 11px;
	color: #969696;
}

.row {
	margin-bottom: 30px;
}


td:nth-child(1) {
	width: 120px;
}

#content2 {
    width: 700px;
    height: 100px;
    float: left;
}

#content3 {
	margin-left: 300px;
}
.write {
	margin: 0;
	height: 100px;
	margin-left: 5px;
}
.modal-body {
	text-align: center;
}
</style>

<body>
   <div class="container-scroller">
      <!-- partial:../../partials/_navbar.html -->
      <%@include file="/WEB-INF/inc/navbar.jsp"%>
      <!-- partial -->
      <div class="container-fluid page-body-wrapper">
         <!-- partial:../../partials/_sidebar.html -->
         <%@include file="/WEB-INF/inc/sidebar.jsp"%>
         <!-- partial -->
         <div class="main-panel">
            <div class="content-wrapper">
               <div class="page-header">
                  <nav aria-label="breadcrumb"></nav>
               </div>
               <div class="row">
                  <div class="col-md-12">
                     <div class="card">
                        <div class="card-body">
                           <button type="button" id="back" class="btn btn-light"
                              onclick="location.href='/todo/board/boardmain.do';">돌아가기</button>

                           <c:if test="${nickname == dto.nickname}">
                              <div id="btn2">
                                 <button type="button" class="btn btn-inverse-danger edit"
                                    onclick="location.href='/todo/board/edit.do?seq=${dto.seq}';">수정하기</button>
                                 <button type="button" class="btn btn-inverse-danger"
                                    id="openModalBtn">삭제하기</button>
                                 <form method="GET" action="/todo/board/delok.do">
                                    <div id="modalBox" class="modal fade" id="myModal"
                                       tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                                       <div class="modal-dialog" role="document">
                                          <div class="modal-content">
                                             <div class="modal-header">
                                                <h4 class="modal-title" id="myModalLabel">게시글 삭제</h4>
                                             </div>
                                             <div class="modal-body">해당 게시글을 삭제하시겠습니까?</div>
                                             <div class="modal-footer">
                                                <button type="submit" class="btn btn-primary">확인</button>
                                                <button type="button" class="btn btn-default"
                                                   id="closeModalBtn">취소</button>
                                             </div>
                                          </div>
                                       </div>
                                    </div>
                                    <input type="hidden" name="seq" value="${dto.seq}">
                                 </form>
                              </div>
                           </c:if>
                           <div class="clearfix">
                              <div class="float-left">
                                 <h3>
                                    <b>${dto.title}</b>
                                 </h3>
                              </div>
                              <div class="float-right">
                                 <p class="m-0 d-print-none">조회수 ${dto.readcount}</p>
                              </div>
                           </div>


                           <div class="row mt-4">
                              <div class="col-6">
                                 <h6 class="font-weight-bold" style="margin-bottom: 0;">
                                    <img src="/todo/asset/images/profile/${dto.image}"
                                       style="width: 30px; height: 30px; margin-right: 5px;">${dto.nickname}</h6>

                                 <div class="line-h-24">${dto.qdate}</div>
                              </div>
                              <!-- end col -->
                           </div>
                           <div id="content">
                              <div>${dto.content}</div>
                           </div>
                           <!-- end col -->
                           <!-- end row -->

                           <div class="row">
                              <div class="col-md-12">
                                 <div class="table-responsive">
                                    <table class="table">
                                       <thead>
                                          <tr>
                                             <th style="margin-bottom: 1px; padding: 2px;"><b>댓글</b></th>
                                          </tr>
                                       </thead>
                                       <tbody>
                                          <c:forEach items="${alist}" var="adto">
                                             <tr>
                                                <td><img src="/todo/asset/images/profile/${adto.image}"
                                                   style="width: 25px; height: 25px; margin-right: 5px;">${adto.nickname}</td>
                                                <td>
                                                   <p>${adto.content}</p>
                                                   <p id="date">${adto.adate}</p>
                                                </td>
                                             </tr>
                                          </c:forEach>
                                       </tbody>
                                    </table>
                                 </div>
                              </div>
                           </div>
                           <c:if test="${not empty auth}">
                           	<div id="content3">
                              <form method="GET" action="/todo/board/answeraddok.do">
                                 <div>
                                    <textarea name="content" id="content2" class="form-control"
                                       required placeholder="댓글"></textarea>
                                 </div>
                                 <div>
                                    <button type="submit" class="btn btn-inverse-danger write"
                                       style="margin-left: 30px;">댓글 쓰기</button>
                                 </div>
                                 <!-- 현재 보고있는 글번호 == 작성하는 댓글의 부모 글번호 -->
                                 <input type="hidden" name="qseq" value="${dto.seq}">
                              </form>
                              </div>
                           </c:if>
                        </div>
                     </div>
                     <!-- end card-->
                  </div>
                  <!-- end col -->
               </div>
            </div>
         </div>
         <!-- <!? main-panel ends ?> -->
      </div>

   </div>

   <script>
      // 모달 버튼에 이벤트를 건다.
      $('#openModalBtn').on('click', function() {
         $('#modalBox').modal('show');
      });
      // 모달 안의 취소 버튼에 이벤트를 건다.
      $('#closeModalBtn').on('click', function() {
         $('#modalBox').modal('hide');
      });
   </script>

</body>

</html>
















