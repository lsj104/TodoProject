<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/inc/asset.jsp"%>

<style>
.divbtn {
	float: left;
}

#btnemail, #btncode, #btnnick {
	height: 40px;
	width: 119px;
	padding: 5px;
	margin-left: 5px;
}

.auth .auth-form-light {
	width: 530px;
}

.form-control {
	width: 310px;
}

.filebox .upload-name {
	display: inline-block;
	height: 40px;
	padding: 0 10px;
	vertical-align: middle;
	border: 1px solid #ebedf2;
	width: 310px;
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
	/*  display: inline-block;
    padding: 10px 20px;
    color: #fff;
    vertical-align: middle;
    background-color: #999999;
    cursor: pointer;
    height: 40px;
    margin-left: 10px; */
	background: linear-gradient(to right, #da8cff, #9a55ff);
	height: 40px;
	width: 119px;
	padding: 10px 20px;
	margin-left: 5px;
	color: #ffffff;
	display: inline-block;
	border-radius: 3px;
	text-align: center;
	padding-top: 13px;
	border: 0;
	margin-left: 0;
	cursor: pointer;
}
</style>
<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth">
				<div class="row w-100">
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left p-5">
							<div style="margin: 0 auto;">
								<img src="/todo/asset/images/logo.png"
									style="width: 350px; margin-left: 40px; padding: 0;">
							</div>
							<h4>Register</h4>


							<form method="POST" action="/todo/member/registerok.do"
								enctype="multipart/form-data" class="pt-3">

								<div class="form-group">
									<div class="divbtn">
										<input type="email" class="form-control form-control-lg"
											name="email" id="email" placeholder="Email" required>
									</div>
									<button type="button"
										class="btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
										id="btnemail" onclick="createCode()">인증번호 발송</button>
								</div>
								<div id="result"></div>
								<div class="form-group">
									<div class="divbtn">
										<input type="text" class="form-control form-control-lg"
											name="code" id="code" placeholder="Certification Number"
											disabled required>
									</div>
									<div>
										<button type="button"
											class="btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
											id="btncode">인증하기</button>
										<input type="hidden" name="checked_code" value="">
									</div>
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-lg"
										name="pw" id="pw" placeholder="Password" required>
								</div>
								<div class="form-group">
									<div class="divbtn">
										<input type="text" class="form-control form-control-lg"
											name="nickname" id="nickname" placeholder="NickName">
									</div>
									<div>
										<button type="button"
											class="btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
											id="btnnick">중복확인</button>
										<input type="hidden" name="checked_nick" value="">
									</div>
									<div id="resultnick"></div>
								</div>
								<div class="form-group">
									<select class="form-control form-control-lg" name="category"
										id="category">
										<option selected disabled>Category</option>
										<option value="초등학교">초등학교</option>
										<option value="중학교 1학년">중학교 1학년</option>
										<option value="중학교 2학년">중학교 2학년</option>
										<option value="중학교 3학년">중학교 3학년</option>
										<option value="고등학교 1학년">고등학교 1학년</option>
										<option value="고등학교 2학년">고등학교 2학년</option>
										<option value="고등학교 3학년">고등학교 3학년</option>
										<option value="N수생">N수생</option>
										<option value="대학생">대학생</option>
										<option value="일반/학사 편입">일반/학사 편입</option>
										<option value="PEET">PEET</option>
										<option value="MDEET">MDEET</option>
										<option value="LEET">LEET</option>
										<option value="로스쿨생">로스쿨생</option>
										<option value="간호대">간호대</option>
										<option value="대학원생">대학원생</option>
										<option value="의대/의전원">의대/의전원</option>
										<option value="약학대학">약학대학</option>
										<option value="공무원">공무원</option>
										<option value="경찰">경찰</option>
										<option value="소방">소방</option>
										<option value="임용 유아">임용 유아</option>
										<option value="임용 초등">임용 초등</option>
										<option value="임용 중등">임용 중등</option>
										<option value="임용 특수">임용 특수</option>
										<option value="공인중개사">공인중개사</option>
										<option value="회계사">회계사</option>
										<option value="세무사">세무사</option>
										<option value="관세사">관세사</option>
										<option value="법무사">법무사</option>
										<option value="노무사">노무사</option>
										<option value="감정평가사">감정평가사</option>
										<option value="변리사">변리사</option>
										<option value="고시">고시</option>
										<option value="취업">취업</option>
										<option value="이직">이직</option>
										<option value="어학">어학</option>
										<option value="자격증">자격증</option>
										<option value="기타">기타</option>
									</select>
								</div>
								<!-- <div class="form-group" style="padding: 3px;">
									profile
									<input type="file" class="upload-name form-control" name="profile" placeholder="Profile">
									<label for="file">파일찾기</label>
									<input type="file" id="file">
								</div> -->
								<div class="form-group filebox">
									<input class="upload-name" value="Profile" placeholder="첨부파일"
										disabled> <label for="file">파일찾기</label> <input
										type="file" id="file" name="profile">
								</div>

								<div class="form-group">
									<input type="text" class="form-control" name="ddayname"
										placeholder="Dday Name">
								</div>

								<div class="form-group">
									<label for="">Ddate</label> <input type="date" max="2077-06-20"
										min="" value="" class="form-control" name="ddate" id="ddate"
										data-placeholder="Dday">
								</div>


								<div class="mt-3">
									<button type="submit"
										class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn"
										id="signup">SIGN UP</button>
								</div>
								<div class="text-center mt-4 font-weight-light">
									Already have an account? <a href="/todo/member/login.do"
										class="text-primary">Login</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- content-wrapper ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->

	<script>

	function createCode() {
		<%Random rnd = new Random();

int num = 0;
String temp = "";
String Ccode = "";

for (int i = 0; i < 6; i++) {

	num = rnd.nextInt(9);
	temp = Integer.toString(num);
	Ccode += temp;
}
System.out.println(Ccode);%>
	}
	
	$("#file").on('change',function(){
		  var fileName = $("#file").val();
		  $(".upload-name").val(fileName);
	});
	
  
  
 	  $('#btnemail').click(()=>{
			
		  var email = $('#email').val();
		  var code = <%=Ccode%>
		  
		  
		  if(email == '') {
			  alert("이메일을 입력해주세요.");
			  return false;
		  }
		  
		  if (email)
		  
			$.ajax({
				type: 'POST',
				url: '/todo/member/emailok.do',
				data: 'email=' + email,
				dataType: 'JSON',
				async: false,
				success: function(resultemail) {
					if (resultemail == 1) {
						alert("이미 가입된 이메일입니다. 다시 입력해주세요.");			
						$('#email').val("");
						$('#email').focus();
					} else {
						alert("인증번호 발송이 완료되었습니다.\n입력한 이메일에서 인증번호를 확인해주세요.");
						$('#code').attr("disabled", false);
					
						
						$.ajax({
							type: 'POST',
							url: '/todo/member/sendcode.do',
							data: 'email=' + email + '&code=' + code,
							dataType: 'JSON',
							async: false,
							success: function() {
							},
							error: function(a,b,c) {
								console.log(a,b,c);
							}
						});
					}
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			}); 
			
		});  
 	  
 	  
 	  $('#btncode').click(()=>{
 		  
 		 $("input[name=checked_code]").val('y');
 		  
 		  var code = $('#code').val();
 		  var Ccode = <%=Ccode%>
 		  
 		  if (code == '') {
 			  alert('인증번호를 입력해주세요.');
 			  $('#code').focus();
 		  } else if (code == Ccode) {
 			  alert('인증이 완료되었습니다.');
 			  $('#code').attr("disabled", true);
 		  } else if (code != Ccode) {
 			  alert('잘못된 인증번호입니다. 다시 입력해주세요.');
 			  $('#code').val("");
 			  $('#code').focus();
 		  }
 	  });
	  
 	   $('#btnnick').click(()=>{
 		   
 		  var nickname = $('#nickname').val();
 		   
 		  $("input[name=checked_nick]").val('y');
 		   
 		  if(($('#nickname').val().length) > 12) {
	          alert('닉네임은 최대 12자로 입력하세요.');
	           $('#nickname').focus();
	           return;
	      }
			
			$.ajax({
				type: 'POST',
				url: '/todo/member/nicknameok.do',
				data: 'nickname=' + nickname,
				dataType: 'JSON',
				async: false,
				success: function(resultnick) {
					if (resultnick == 1) {
						alert("이미 사용중인 닉네임입니다. 다시 입력해주세요.");
						$('#nickname').val("");
						$('#nickname').focus();
					} else {
						alert('사용 가능한 닉네임입니다.');
					}
				},
				error: function(a,b,c) {
					console.log(a,b,c);
				}
			});
			
		});
 	   
 	   
 	   $('#signup').click(()=>{
 		   
 		  if($('#email').val() == '' || $('#pw').val() == '' || $('#nickname').val() == '' || $('#category').val() == ''|| $('#code').val() == '') {
			  alert("모든 항목이 입력되지 않았습니다. 모두 입력해주세요.");
			  event.preventDefault();
			  return;
		  }
 		   
 		  if(($('#pw').val().length) < 4 || ($('#pw').val().length) > 12) {
 	          alert('비밀번호를 4 ~ 12자 사이로 입력하세요.');
 	          event.preventDefault();
 	           $('#pw').focus();
 	           return;
 	      }
 		  
 		 for (var i=0; i<$('#pw').val().length; i++) {
             let c = $('#pw').val().charAt(i);
             if ((c < 'A' || c > 'Z') && (c < 'a' || c > 'z') && (c < '0' || c > '9')) {
                   alert('비밀번호는 영어/숫자만 입력하세요.');
                   event.preventDefault();
                   $('#pw').focus();
                   return;
                }
           }
 		 
 		 
 		if($("input[name='checked_nick']").val()==''){
 	        alert('닉네임 중복 확인을 해주세요.');
 	        $("input[name='checked_nick']").eq(0).focus();
 	       event.preventDefault();
 	        return false;
 	    	}
 		
 		if($("input[name='checked_code']").val()==''){
 	        alert('이메일 인증을 해주세요.');
 	        $("input[name='checked_code']").eq(0).focus();
 	       event.preventDefault();
 	        return false;
 	    	}

 		 

 	   });
 	   
 	   
  
  </script>


</body>
</html>