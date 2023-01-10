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

<body>
	<c:if test="${auth eq null}">
		<script type="text/javascript">
			alert("로그인 후 이용해 주세요.");
			location.href = "/todo/member/login.do"
		</script>
	</c:if>
	<div class="container-scroller">
		<!-- navbar -->
		<%@include file="/WEB-INF/inc/navbar.jsp"%>

		<div class="container-fluid page-body-wrapper">
			<!-- sidebar -->
			<%@include file="/WEB-INF/inc/sidebar.jsp"%>

			<div class="main-panel">
				<div class="content-wrapper"></div>
				<!-- content-wrapper ends -->
				<%@include file="/WEB-INF/inc/footer.jsp"%>

			</div>
			<!-- main-panel ends -->
		</div>

	</div>
	
</body>

</html>








































