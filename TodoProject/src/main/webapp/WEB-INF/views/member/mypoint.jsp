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
<title>카멜레온 집사</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
</head>
<style>

.content-wrapper img {
	border-radius: 100px;
	width: 150px;
	height: 150px;;
}

.card-body {
	height: 100vh;
}

.content-wrapper #profile-image {
	text-align: center;
	margin-top: 100px;
}

.content-wrapper #info {
	text-align: center;
	margin: 20px;
}

.content-wrapper span {
	font-size: 1rem;
}

.desc {
	font-size: 13px;
	color: #4E4E4E;
	margin: 10px;
}

#buttons {
	margin-top: 40px;
	text-align: right;
}
.card {
	position: relative;
	top: 0;
	left: 0;
}

#btns {	
	position: absolute;
	top: 60px;
	left: 250px;
}

#plus.btn.btn-fw, #minus.btn.btn-fw { 
    min-width: 100px;
    margin-right: 3px;
	padding: 10px 20px;
}

#inquiry {
	width: 1000px;
	height: 60px;
	background-color: #f1edf3;
	display: flex;
	align-items: center;
	margin: 20px auto;
	margin-top: 70px;
	border-radius: 3px;
}

#inquiry>ul>li>input {
	background-color: #FFF;
	border: 1px solid #4c4c4c;
	padding: 5px 15px;
	color: #4c4c4c;
}

#inquiry>ul>li>input:hover {
	background-color: #222;
	color: white;
}

#inquiry>span {
	font-size: 1.2rem;
	margin-right: 220px;
	margin-left: 120px;
}

.date-picker {
	color: #777;
	background-color: #FFF;
	border: 1px solid #DDD;
	padding: 5px;
}


#inquiry>span:nth-child(4) {
	margin: auto 10px;
	color: #777;
}

.date-picker:hover {
	text-decoration: none;
	color: #777;
}

#inquiry>input[type=submit] {
	margin-left: 30px;
	padding: 10px;
}


table th, table td {
	text-align: center;
} 


#date {
	width: 350px;
	border: 1px solid #EEE;
	border-radius: 10px;
	text-align: center;
	padding: 5px;
	font-weight: bold;
	color: #202020;
}

table td:nth-child(1) { width: 150px; }
table td:nth-child(2) { width: 300px; }
table td:nth-child(3) { width: 100px; }


#buttons {
	text-align: right;
}

.table {
	width: 1000px;
	margin: 0 auto;
}
</style>
<body>
	<div class="container-scroller">
		<!-- navbar -->
		<%@include file="/WEB-INF/inc/navbar.jsp"%>

		<div class="container-fluid page-body-wrapper">
			<!-- sidebar -->
			<%@include file="/WEB-INF/inc/sidebar.jsp"%>

			<div class="main-panel">
				<div class="content-wrapper">
					<div class="h1 font-weight-bold">
						Point
					</div>
					<div class="grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<div id="btns">
									<input type="button" class="btn btn-rounded btn-inverse-primary btn-fw" value="적립" id="plus" onclick="plus()"> 
									<input type="button" class="btn btn-rounded btn-inverse-primary btn-fw" value="사용" id="minus" onclick="minus()">
								</div>
								<form method="GET" action="/todo/member/mypoint.do">
									<div id="inquiry">
										<span class="font-weight-bold">기간 조회</span>
										<i class="mdi mdi-calendar-check" style="font-size: 20px; margin-right: 10px;"></i>
										<input type="text" id="date" name="date" data-range="true" data-multiple-dates-separator=" ~ " data-language="ko" class="datepicker-here" placeholder="여기를 클릭해주세요!" />
										<input type="submit" class="btn btn-gradient-primary mr-2" id="submit-button" value="조회">
									</div>
								</form>
								<div id="minustbl">
								<table id="minus-table" class="table point">
									<tr>
										<th>날짜</th>
										<th>내역</th>
										<th>차감 포인트</th>
									</tr>
									<c:if test="${pointList.size() == 0 }">
									<tr>
										<td colspan="3" style="color: #424242;">포인트 내역이 없습니다.</td>
										
									</tr>
									</c:if>
									<c:forEach items="${pointList}" var="dto">
										<tr>
											<td>${dto.regdate}</td>
											<td>${dto.itemname}</td>
											<td style="color: red; text-align:right; padding-right: 100px;">- ${dto.itemcost}P</td>
										</tr>
									</c:forEach>
									
								</table>
								</div>
								<div id="plustbl">
								<table id="plus-table" class="table point">
									<tr>
										<th>날짜</th>
										<th>내역</th>
										<th>적립 포인트</th>
									</tr>
									<c:if test="${plusPointList.size() == 0 }">
									<tr>
										<td colspan="3" style="color: #424242;">포인트 내역이 없습니다.</td>
										
									</tr>
									</c:if>
									<c:forEach items="${plusPointList}" var="pdto">
										<tr>
											<td>${pdto.regdate}</td>
											<td>${pdto.plusName}</td>
											<td style="color: red; text-align:right; padding-right: 100px;">+ ${pdto.plusPoint}P</td>
										</tr>
									</c:forEach>
									
								</table>
								</div>
								
								<div id="buttons">
							
									<button type="button" class="btn btn-light" onclick="location.href='/todo/member/mypage.do';">돌아가기</button>
								
								</div>

							</div>

						</div>
					</div>
				</div>
				<%@include file="/WEB-INF/inc/footer.jsp"%>

			</div>

			<!-- main-panel ends -->
		</div>

	</div>

	<script type="text/javascript">
	$("#datepicker").datepicker({
    	language: 'ko'
    });
	
	$('#submit-button').click(()=>{
		
		$('#date').val().replace(' ','').split('~');
		
	});
	
	
	$("#minustbl").hide();
	
	function plus() {
		$("#plustbl").show();
		$("#minustbl").hide();
	}
	
	function minus() {
		$("#plustbl").hide();
		$("#minustbl").show();
	}
	
	</script>

</body>

</html>
