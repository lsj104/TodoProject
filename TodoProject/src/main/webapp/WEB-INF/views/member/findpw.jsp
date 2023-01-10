<%@page import="java.util.Random"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<title>카멜레온 집사</title>
<head>

    <%@ include file="/WEB-INF/inc/asset.jsp"%>
    
    <style>
        .auth .brand-logo img {
            width: 220px;
        }
        
        .brand-logo {
            margin-left: 70px;
        }
    </style>
</head>

<body>
<div class="container-scroller">
    <div class="container-fluid page-body-wrapper full-page-wrapper">
        <div class="content-wrapper d-flex align-items-center auth">
            <div class="row w-100">
                <div class="col-lg-4 mx-auto">
                    <div class="auth-form-light text-left p-5">
                        <div class="brand-logo">
                            <img src="/todo/asset/images/logo.png">
                        </div>
                        <h4>Forgot Password?</h4>
                        <h6 class="font-weight-light">You can reset your password here.</h6>
                        <!-- <form class="pt-3" method="post" action="findpwok.do"> -->
                            <div class="form-group">
                                <input type="email" name="email" class="form-control form-control-lg" id="email" placeholder="Email" required>
                            </div>
                            <input class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" type="button" value="Reset Password" id="find" onclick="createPw(); location.href='/todo/member/login.do'">

                            <div class="text-center mt-4 font-weight-light">
                                Did you remember your password? <a href="todo/member/login.do" class="text-primary">Login</a>
                            </div>
                        <!-- </form> -->
                    </div>
                </div>
            </div>
        </div>
        <!-- <!— content-wrapper ends —> -->
    </div>
    <!-- <!— page-body-wrapper ends —> -->
</div>
<!--  <!— container-scroller —> -->

	
<script>

	var temp_pw = "";
function createPw() {

	
	let ranValue1 = ['1','2','3','4','5','6','7','8','9','0'];
	let ranValue2 = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'];
	let ranValue3 = ['a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'];
	
	
	for(i=0 ; i<2; i++) {
		let ranPick1 = Math.floor(Math.random() * ranValue1.length);
		let ranPick2 = Math.floor(Math.random() * ranValue2.length);
		let ranPick3 = Math.floor(Math.random() * ranValue3.length);
		temp_pw = temp_pw + ranValue1[ranPick1] + ranValue2[ranPick2] + ranValue3[ranPick3];
	}

}


$('#find').click(()=>{
	
	  var email = $('#email').val();
	  var code = temp_pw;
	  
	  if(email == '') {
		  alert("이메일을 입력해주세요.");
		  return false;
	  }
	  
	  $('#pw').attr("value", code);
	  
		$.ajax({
			type: 'POST',
			url: '/todo/member/emailok.do',
			data: 'email=' + email,
			dataType: 'JSON',
			async: false,
			success: function(resultemail) {
				if (resultemail == 1) {
					alert("임시 비밀번호 발송이 완료되었습니다.");			

					$.ajax({
						type: 'POST',
						url: '/todo/member/sendpw.do',
						data: 'email=' + email + '&code=' + code,
						dataType: 'JSON',
						async: false,
						success: function() {
						},
						error: function(a,b,c) {
							console.log(a,b,c);
						}
					});
					
					$.ajax({
						type: 'POST',
						url: '/todo/member/findpwok.do',
						data: 'email=' + email + '&code=' + code,
						dataType: 'JSON',
						async: false,
						success: function() {
						},
						error: function(a,b,c) {
							console.log(a,b,c);
						}
					});
					
				} else {
					alert("존재하지 않은 이메일입니다.");
					
				}
			},
			error: function(a,b,c) {
				console.log(a,b,c);
			}
		}); 
		
	});  

</script>

</body></html>



















