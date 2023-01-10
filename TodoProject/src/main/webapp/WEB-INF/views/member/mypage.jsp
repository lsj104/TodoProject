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

<style>

	.content-wrapper img {
		border-radius: 100px;
		width: 150px;
		height: 150px;
	}
	
	.content-wrapper #profile-image {
		text-align: center;
		margin-top: 50px;
	}
	
	.content-wrapper #info {
		text-align: center;
		margin: 30px;
		margin-top: 10px;
		margin-bottom: 120px;
	}
	
	a {
		text-decoration: none;
		color: black;
	}
	
	.col-lg-6 {
		margin: 0 auto;
	}
	
	.nav-link {
		margin: 2px 0;;
	}
	
	.mdi {
		font-size: 15px;
		margin-right: 10px;
	}
	
	.modal {
		text-align: center;
	}
	
	.modal-dialog {
		display: inline-block;
		text-align: left;
		vertical-align: middle;
	}
	
	.modal-body {
		padding: 10px;
	}
	
	#my-category, .change-category {
		border: 1px solid white;
		border-radius: 5px;
		padding: 4px;
		/*background-color: #EEE;*/
		background-color: #f1edf3;
		font-size: small;
		color: #424242;
		font-weight: bold;
	}
	
	#my-category:before {
		content: "#";
	}
	
	.modal {
        text-align: center;
	}
 
	@media screen and (min-width: 768px) { 
   		.modal:before {
           display: inline-block;
           vertical-align: middle;
           content: " ";
           height: 100%;
   		}
	}
 
	.modal-dialog {
	        display: inline-block;
	        text-align: left;
	        vertical-align: middle;
	}
	
	.modal-body {
	   padding: 10px;
	}
	
	#unregister:hover, #my-category:hover {
		cursor: pointer;
	}
	
	
	
	
</style>
</head>

<body>
	<div class="container-scroller">
		<!-- navbar -->
		<%@include file="/WEB-INF/inc/navbar.jsp"%>
		<div class="container-fluid page-body-wrapper">
			<!-- sidebar -->
			<%@include file="/WEB-INF/inc/sidebar.jsp"%>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="col-lg-6 grid-margin stretch-card">
						<div class="card" style="width: 100px;">
							<div class="card-body">
								<h4 class="card-title">MyPage</h4>
								<div id="profile-image">
									<img src="/todo/asset/images/profile/${image}" alt="profile">
								</div>
								<div id="info">
									<span class="font-weight-bold">${nickname}</span>님
								</div>
								<table class="table table-striped">
									<tbody>
										<tr>
											<td class="py-1" colspan="5">
												<div class="nav-link">
													<i class="mdi mdi-format-list-bulleted-type"></i> 
													카테고리: 
													<span id="my-category" data-toggle="modal" data-target="#popup_box">
														${category}
													</span>
												</div>
											</td>
										</tr>
										<tr>
											<td class="py-1" colspan="5">
												<a href="/todo/member/mypageedit.do" class="nav-link">
													<i class="mdi mdi-account-convert"></i>
														개인 정보 수정
												</a>
											</td>
										</tr>
										<tr>
											<td class="py-1" colspan="5">
												<a href="/todo/member/mypoint.do" class="nav-link">
													<i class="mdi mdi-coin"></i>
														포인트 내역
												</a>
											</td>
										</tr>
										<tr>
											<td class="py-1" colspan="5">
												<span class="nav-link" id="unregister" data-toggle="modal" data-target="#unregister_box">
													<i class="mdi mdi-account-remove"></i>
														회원 탈퇴
												</span>
											</td>
										</tr>

									</tbody>
								</table>
							</div>
						</div>
					</div>

					<!-- modal 부분 -->
					<div class="modal fade" tabindex="-1" role="dialog" id="popup_box">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title font-weight-bold">태그 변경하기</h4>
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
								</div>
								<div class="modal-body">
									<c:forEach items="${list}" var="dto">
										<input type="button" class="change-category" value="${dto.category}" />
									</c:forEach>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>
					
					<!-- 회원 탈퇴 modal -->
					<div class="modal fade" tabindex="-1" role="dialog" id="unregister_box">
						<div class="modal-dialog" role="document">
							<div class="modal-content">
								<div class="modal-header">
									<h4 class="modal-title font-weight-bold">회원 탈퇴</h4>									
									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          								<span aria-hidden="true">&times;</span>
        							</button>
								</div>
								<div class="modal-body" style="text-align: center;">
        							<span>회원 탈퇴를 진행하시겠습니까?</span>
      							</div>
								<div class="modal-footer">
        							<button type="button" class="btn" id="unregister_ok">진행하기</button>
        							<button type="button" class="btn btn-secondary" data-dismiss="modal">닫기</button>
								</div>
							</div>
						</div>
					</div>

				</div>

				<%@include file="/WEB-INF/inc/footer.jsp"%>
			</div>

		</div>
	</div>


	<script>
    
    $('.change-category').click(()=> {
    	
    	const category = event.target.value;
    	
    	if(confirm('변경하시겠습니까?')) {
    		document.getElementById('my-category').textContent = category;
    		
    		$('#popup_box').modal('hide');
    		
    		location.href='/todo/member/editok.do?value=' + category;
    	}
    	
    });
    
    $('#unregister_ok').click(()=> {
    	
    	location.href='/todo/member/unregisterok.do';
    	
    });
    
    
</script>

</body>

</html>











