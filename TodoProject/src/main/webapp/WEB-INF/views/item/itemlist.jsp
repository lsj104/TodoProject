<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>카멜레온 집사</title>


<%@ include file="/WEB-INF/inc/asset.jsp"%>

</head>

<style>
#lists {
	display: flex;
	height: 600px;
}

.page-arrow {
	width: 122px;
	height: 122px;
	margin-top: auto;
	margin-bottom: auto;
	text-align: center;
	font-size: 5em;
	color: #999;
}

#item-list {
	width: 1200px;
	border: 0px solid black;
	margin: auto auto;
	display: flex;
	flex-wrap: wrap;
}

.item {
	width: 260px;
	height: 260px;
	margin: 20px;
}

.item-image {
	width: 260px;
	height: 184.844px;
	border-bottom: 1px solid rgba(0, 0, 0, 0.125);
}

.item-info {
	display: flex;
	margin-top: auto;
	margin-bottom: auto;
}

.item-name {
	width: 180px;
	text-align: center;
	margin: auto 0 auto 10px;
	font-size: 0.9rem;
}

.btn {
	border-radius: 5px;
	width: 60px;
	height: 35px;
	padding: 0px;
	margin-top: auto;
	margin-bottom: auto;
	margin-right: 10px;
}

#page {
	width: 1200px;
	margin-left: auto;
	margin-right: auto;
}

#page>#page-bar {
	border: 1px solid rgba(0, 0, 0, 0.125);
	margin-left: auto;
	margin-right: auto;
}

#page>#page-bar>tr {
	border: 1px solid rgba(0, 0, 0, 0.125);
}

#page>#page-bar td {
	border: 1px solid rgba(0, 0, 0, 0.125);
	width: 30px;
	height: 30px;
	text-align: center;
}

#item-list img {
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
	width: 100%;
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
					<h2>Store</h2>
					<div id="lists">

						<div class="page-arrow">
							<i class="fa-solid fa-chevron-left" onclick="move(${nowPage-1})"></i>
						</div>

						<div id="item-list">
							<c:forEach items="${list}" var="dto">
								<div class="card item">
									<div class="item-image">
										<img src="/todo/asset/images/item/${dto.image}">
									</div>
									<div class="item-info">
										<div class="item-name">
											<span>${dto.itemName}</span>
										</div>
										<c:if test="${dto.isown == 0 && (totalPoint >= dto.itemCost)}">
											<input type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#purchase" value="${dto.itemCost}" onclick="showInfo();" data-name="${dto.itemName}" data-seq="${dto.seq}" id="btn${dto.seq}">
										</c:if>
										<c:if test="${dto.isown == 0 && (totalPoint < dto.itemCost)}">
											<input type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#failed" value="${dto.itemCost}" onclick="showInfo();" data-name="${dto.itemName}" data-seq="${dto.seq}" id="btn${dto.seq}">
										</c:if>
										<c:if test="${dto.isown > 0}">
											<input type="button" class="btn btn-primary btn-lg" value="보유중">
										</c:if>


									</div>
								</div>
							</c:forEach>
						</div>


						<div class="modal fade" id="purchase" tabindex="-1" role="dialog" aria-labelledby="purchaseLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title purchaselLabel">아이템 구매</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
									</div>
									<div class="modal-body">
										<span> <span id="itemName"></span> 을/를 구매하시겠습니까?
										</span>
									</div>
									<div class="modal-footer">
										<input type="hidden" id="itemSeq">
										<button type="button" class="btn btn-primary" onclick="purchase();" data-toggle="modal" data-target="#purchase">확인</button>
										<button type="button" class="btn btn-default" data-dismiss="modal" >취소</button>
									</div>
								</div>
							</div>
						</div>

						<div class="modal fade" id="failed" tabindex="-1" role="dialog" aria-labelledby="failedLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title failedLabel">구매 실패</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
									</div>
									<div class="modal-body">
										<span>포인트가 부족합니다.</span>
									</div>
									<div class="modal-footer">
										<input type="hidden" id="itemSeq">
										<button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
									</div>
								</div>
							</div>
						</div>
						
						<div class="modal fade" id="message" tabindex="-1" role="dialog" aria-labelledby="messageLabel">
							<div class="modal-dialog" role="document">
								<div class="modal-content">
									<div class="modal-header">
										<h4 class="modal-title messageLabel">응원 메세지 변경</h4>
										<button type="button" class="close" data-dismiss="modal" aria-label="Close">
											<span aria-hidden="true">×</span>
										</button>
									</div>
									<div class="modal-body">
										<form>
									        <div class="form-group">
									            <label for="message-text" class="col-form-label">응원 메세지:</label>
									            <input type="text" class="form-control" id="messageChanger" required>
									        </div>
										</form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-primary" data-toggle="modal" onclick="messageChange();">확인</button>
									</div>
								</div>
							</div>
						</div> 

						<div class="page-arrow">
							<i class="fa-solid fa-chevron-right" onclick="move(${nowPage+1})"></i>
						</div>

					</div>
					<!-- 페이지 바 -->
					<div id="page">${pagebar}</div>

				</div>
				<!-- content-wrapper ends -->
				<%@include file="/WEB-INF/inc/footer.jsp"%>
				<!-- partial -->
			</div>
			<!-- <!— main-panel ends —> -->
		</div>

	</div>

	<script>
		
		function move(page) {
			if (page < 1) {
				location.href = '/todo/item/itemlist.do?page=' + ${totalPage};
			} else if (page > ${totalPage}) {
				location.href = '/todo/item/itemlist.do?page=' + 1;
			} else {
				location.href = '/todo/item/itemlist.do?page=' + page;
			}
		}
		
		function purchase(itemSeq) {
			
			var itemSeq = $('#itemSeq').val();
			
			$.ajax({
				type: 'GET',
				url: '/todo/item/purchase.do',
				dataType:'JSON',
				data: 'itemSeq=' + itemSeq,
				async: false,
				success: function(result){
					
					if (result > 0) {
						
						if (itemSeq = 9) {
							$('#message').modal('show');
						} else {
							$('#btn' + $('#itemSeq').val()).prop('disabled', true);
							$('#btn' + $('#itemSeq').val()).val('보유중');
							location.reload();
						}	
						
					} 
					
				},
				error: function(a,b,c) {
					console.log(a,b,c)
				}
			
			});
					
		}
		
		function showInfo() {
			$('#itemName').text($(event.target).data('name'));
			$('#itemSeq').val($(event.target).data('seq'));
		}
		
		
		function messageChange() {
			
			if ($('#messageChanger').val() != null) {
			
				$.ajax({
					type: 'GET',
					url: '/todo/member/messagechange.do',
					dataType:'JSON',
					data: 'messageChange=' + $('#messageChanger').val(),
					async: false,
					success: function(result){
						
						if (result = 0) {
							alert('응원메세지 변경을 실패했습니다.');
						} else {
							$('#message').modal('hide');
							$('#btn' + $('#itemSeq').val()).prop('disabled', true);
							$('#btn' + $('#itemSeq').val()).val('보유중');
							location.reload();
						}
						
					},
					error: function(a,b,c) {
						console.log(a,b,c)
					}
				});
			
			} else {
				$('#messageChanger').focus();
			}
			
		}
 	
	</script>

</body>

</html>



