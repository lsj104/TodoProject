<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>로그인 화면</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
<style>
.brand-logo {
	margin-left: 150px;
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
							<h4>Hello! let's get started</h4>
							<h6 class="font-weight-light">Sign in to continue.</h6>
							
							<form class="pt-3" method="POST" action="/todo/member/loginok.do">
							
								<div class="form-group">
									<input type="email" name="email" class="form-control form-control-lg" id="email" placeholder="Email" required>
								</div>
								<div class="form-group">
									<input type="password" name="pw" class="form-control form-control-lg" id="pw" placeholder="Password" required>
								</div>
								<input class="btn btn-block btn-gradient-primary btn-lg font-weight-medium auth-form-btn" type="submit" value="SIGN IN">
								<div class="my-2 d-flex justify-content-between align-items-center">
									<a href="/todo/member/findpw.do" class="auth-link text-black">Forgot password?</a>
								</div>
								<div class="text-center mt-4 font-weight-light">
									Don't have an account? 
									<a href="/todo/member/register.do" class="text-primary">Create</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>



















