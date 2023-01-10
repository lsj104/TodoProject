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
	.content-wrapper {
		margin: 0 auto;
	}
	
	.card {
		margin: 50px auto;
		width:700px;

	}
	.form-control {
		margin-rigth: 10px;
		height: 3rem;
	}
	
	.btn {
		margin-left: 10px;
		margin-bottom: 50px;
	}
	
	ul {
		list-style-type: none;
	}
	
	li {
		position: relative;
		left: 0;
		top: 0;
	
	}
	
	.remove {
		position: absolute;
		right: 10px;
		bottom: 1px;
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
					<h1>${nickname}의 Todo List</h1>
					<div class="card">
						<div class="card-body">
							<div class="add-items d-flex">
								<input type="text" class="form-control todo-list-input" placeholder="What do you need to do today?">
								<button class="add btn btn-gradient-primary font-weight-bold todo-list-add-btn" id="add-task">Add</button>
							</div>
							<div class="list-wrapper">
								<ul class="d-flex flex-column-reverse todo-list todo-list-custom">
									<c:forEach items="${todolist}" var="dto">
										<li>
											<div class="form-check">
												<label class="form-check-label"> 
												<input class="checkbox" type="checkbox"> ${dto.content} <i class="input-helper"></i><i class="remove mdi mdi-close-circle-outline" onclick="del('${dto.seq}')"></i></label>
											</div> 
										</li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="modal fade" id="add-failed" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
						  <div class="modal-dialog" role="document">
						    <div class="modal-content">
						      <div class="modal-header">
						        <h4 class="modal-title" id="myModalLabel">TodoList 추가 실패</h4>
						        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
						      </div>
						      <div class="modal-body">
						        <span>TodoList 추가에 실패하였습니다.</span>
						      </div>
						      <div class="modal-footer">
						        <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
						      </div>
						    </div>
						  </div>
						</div>
				</div>
				<!-- content-wrapper ends -->
				<%@include file="/WEB-INF/inc/footer.jsp"%>
			</div>
			<!-- main-panel ends -->
		</div>
		</div>
</body>


<script src="/todo/asset/js/todolist.js"></script>

<script type="text/javascript">


	$('.todo-list-add-btn').on("click", function() {
	
		$.ajax({
	     	type: 'GET',
	     	url: '/todo/todolist/todolistadd.do',
	     	dataType: 'JSON',
	     	data: 'content=' + $('.todo-list-input').val(),
	     	success: function(result) {
	     		
	     		if (result == 1) {   			
	     			$('.todo-list').append("<li><div class='form-check'><label class='form-check-label'><input class='checkbox' type='checkbox'/>" + $('.todo-list-input').val() + "<i class='input-helper'></i><i class='remove mdi mdi-close-circle-outline' onclick='del('${dto.seq}')'></i></label></div></li>");
	     			$('.todo-list-input').val("");
	            	
	     		} else {
	     			$("#add-failed").modal('show');
	     		}
	     	},
	     	error: function(a,b,c) {
	     		console.log(a,b,c)
	     	}
	     	
	     });
		
	});

	$('.todo-list').on("change", function (){
			
		if(this.checked){
			$(this).prop("disabled",true);
			$(this).prop("checked",true);
		}
		else{
			$(this).prop("disabled",false);
		}
	      $(this).closest("li").toggleClass('completed');
	});
	
	function del(seq) {
		
		$.ajax ({
			
			type: 'GET',
	     	url: '/todo/todolist/todolistdel.do',
	     	dataType: 'JSON',
	     	data: 'seq=' + seq,
	     	success: function(result) {
	     		if (result == 1) { 
	     			$(this).parent().remove();
	     		}
	     	},
	     	error: function(a,b,c) {
	     		console.log(a,b,c)
	     	}	
			
		});
    	

    }
	
	
	
 



</script>
</html>
