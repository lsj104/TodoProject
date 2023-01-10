<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>카멜레온 집사</title>
<!-- plugins:css -->
<%@ include file="/WEB-INF/inc/asset.jsp"%>
</head>
<style>
.content-wrapper img {
	border: solid 1px #EEE;
	box-shadow: 5px 5px 5px #BBB;
	border-radius: 100px;
	width: 200px;
	height: 200px;
	padding: 20px;
}

.content-wrapper #profile-image {
	text-align: center;
	margin-top: 100px;
	margin-bottom: 20px;
}

.content-wrapper #info {
	text-align: center;
	margin: 20px;
	margin-bottom: 70px;
}

.content-wrapper span {
	font-size: 1.2rem;
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

#inputConfirmPassword2 {
	margin-top: 5px;
}

.h1 {
	margin-bottom: 30px;
}

.filebox .upload-name {
	display: inline-block;
	height: 40px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #ebedf2;
	width: 85%;
	color: #c9c8c8;;
	padding: 15px;
	margin-right: 0;
}

.filebox input[type="file"] {
	position: absolute;
	width: 0;
	height: 0;
	padding: 0;
	overflow: hidden;
	border: 0;
}

.filebox label {
	display: inline-block;
	padding: 10px 20px;
	color: #fff;
	vertical-align: middle;
	cursor: pointer;
	height: 40px;
	margin-left: 5px;
	background: linear-gradient(to right, #da8cff, #9a55ff);
	height: 40px;
	width: 12%;
	margin-left: 5px;
	color: #ffffff;
	border-radius: 3px;
	text-align: center;
	padding-top: 13px;
}

.lab {
	font-weight: bold;
}

.form-group {
	margin-bottom: 50px;
}

.btn {
	margin: 0 auto;
	margin-top: 30px;
}

#buttons {
	text-align: right;
}
</style>
<body>
	<div class="container-scroller">
		<!-- navbar -->
		<%@include file="/WEB-INF/inc/navbar.jsp"%>

		<div class="container-fluid page-body-wrapper">
			<!-- sidebar -->
			<%@include file="/WEB-INF/inc/sidebar.jsp"%>

			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">

					<div class="h1 font-weight-bold">Edit Profile</div>

					<div class="grid-margin stretch-card">
						<div class="card">
							<div class="card-body">
								<!-- <h4 class="card-title">회원 정보 변경</h4>    -->
								<div id="profile-image">
									<img src="/todo/asset/images/profile/${image}" alt="profile">
								</div>
								<div id="info">
									<span class="font-weight-bold">${nickname}</span>님
								</div>

								<form method="POST" class="forms-sample" enctype="multipart/form-data" action="/todo/member/mypageeditok.do">
									<div class="form-group">
										<label for="exampleInputName1" class="lab">닉네임</label> 
										<input type="text" class="form-control" id="inputUsername" name="inputUsername" placeholder="닉네임을 입력해주세요!" value="${nickname}">
										<div class="desc">* 닉네임의 최대 길이는 한글 8자, 영어 16자입니다.</div>
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1" class="lab">현재 비밀번호</label>
										<input type="password" class="form-control" id="inputPassword"
											name="inputPassword" placeholder="현재 비밀번호를 입력해주세요!">
									</div>
									<div class="form-group">
										<label for="exampleInputConfirmPassword1" class="lab">새로운 비밀번호</label> 
										<input type="password" class="form-control" id="inputConfirmPassword1" name="inputConfirmPassword1" placeholder="새로운 비밀번호를 입력해주세요!"> 
										<input type="password" class="form-control" id="inputConfirmPassword2" name="inputConfirmPassword2" placeholder="새로운 비밀번호를 한번 더 입력해주세요!">
										<div class="desc">* 비밀번호 변경을 원하실 경우 위의 입력칸에 새로운 비밀번호를
											입력해주세요.</div>
										<div class="desc">* 카멜레온 집사는 회원님의 비밀번호 유출을 방지하기 위해 업계 표준
											암호화 기술을 사용합니다.</div>
									</div>
									<div class="form-group filebox">
										<input class="upload-name" value="Profile" placeholder="첨부파일" disabled> 
										<label for="file">파일찾기</label> 
										<input type="file" id="file" name="profile">
									</div>
									<div id="buttons">
										<button type="submit" id="submit-button"
											class="btn btn-gradient-primary mr-2">수정하기</button>
										<button type="button" class="btn btn-light"
											onclick="location.href='/todo/member/mypage.do';">돌아가기</button>
									</div>
								</form>
							</div>
						</div>

					</div>
				</div>
				<!-- content-wrapper ends -->
				<%@include file="/WEB-INF/inc/footer.jsp"%>
				<!-- partial -->

			</div>

			<!-- main-panel ends -->
		</div>

	</div>

	<script>
	
		$("#file").on('change',function(){
		     var fileName = $("#file").val();
		     $(".upload-name").val(fileName);
		}); 
	
		$('#submit-button').click(()=> {
			
			const orginPw = $('#inputPassword').val();
			const newPw1 = $('#inputConfirmPassword1').val();
			const newPw2 = $('#inputConfirmPassword2').val();
			
			if (orginPw != ${pw}) {
				
				$('#inputPassword').focus();
				
				window.alert("현재 비밀번호를 확인해주세요.")
				
				return false; // 비밀번호 틀릴 시 submit 취소
				
			}
			
			if (newPw1 != newPw2) {
				
				$('#inputConfirmPassword1').focus();
				
				window.alert("새 비밀번호를 확인해주세요.");
				
				return false; // 비밀번호 틀릴 시 submit 취소
			} 
			
			if (newPw1 == '' && newPw2 == '') {
				
				$('#inputConfirmPassword1').val(${pw});
				
				return true;
				
			} // 기존 비밀번호 -> 새로운 비밀번호
			
			
		});
		
		
		
	
		
	</script>

</body>

</html>
