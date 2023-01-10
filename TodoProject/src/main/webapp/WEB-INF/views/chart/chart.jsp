<%@page import="java.util.Random"%>
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
<script src="https://kit.fontawesome.com/74223ef5b2.js"
	crossorigin="anonymous"></script>
<title>카멜레온 집사</title>
<%@ include file="/WEB-INF/inc/asset.jsp"%>
</head>
<style>
.table thead th {
	padding-left: 30px;
}

h1 {
	text-align: center;
}

.medal {
	text-align: center;
	margin-left: 13px;
}

.col-lg-6 {
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

			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="page-header">

						<div class="col-lg-6 grid-margin stretch-card">
							<div class="card">
								<div class="card-body">
									<h1 class="card-title">
										<i class="mdi mdi-crown" style="color: gold; font-size: 25px;"></i>
										Point Ranking <i class="mdi mdi-crown"
											style="color: gold; font-size: 25px;"></i>
									</h1>
									<p class="card-description">
										<%-- Add class <code>.table-hover</code> --%>
									</p>
									<table class="table table-hover">
										<thead>
											<tr>
												<th></th>
												<th>NickName</th>
												<th>Point</th>
												<th>Status</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${mlist}" var="dto" varStatus="status">
												<tr>
													<td style="padding-left: 30px; padding-right: 0;">
														${status.count}</td>
													<td><img src="/todo/asset/images/profile/${dto.image}">
														${dto.nickname}</td>
													<td><span style="color: gold; font-size: 20px;"><i
															class="mdi mdi-coin"></i></span> ${dto.point}</td>
													<td style="padding-left: 35px;"><c:if
															test="${status.count == 1}">
															<img src="/todo/asset/images/gold.png" style="width: 30px;">
														</c:if> <c:if test="${status.count == 2}">
															<img src="/todo/asset/images/silver.png" style="width: 30px;">
														</c:if> <c:if test="${status.count == 3}">
															<img src="/todo/asset/images/bronze.png" style="width: 30px;">
														</c:if></td>
												</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
							</div>
						</div>

					</div>

				</div>
					<%@include file="/WEB-INF/inc/footer.jsp"%>
			</div>
		</div>
	</div>

</body>

</html>

