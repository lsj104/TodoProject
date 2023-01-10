<%@ page import = "java.util.*" %>
<%@ page import = "com.test.todo.challenge.ChallengeDTO" %>

<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Enjoy your Challenge!</title>
    <!-- plugins:css -->
    <%@include file="/WEB-INF/inc/asset.jsp" %>
</head>
<style>
	.input-group {
		margin-left 200px;
	}

</style>
<body>
<%
	List<ChallengeDTO> challengeList = (List<ChallengeDTO>) request.getAttribute("list");
	List<ChallengeDTO> challengeAllList = (List<ChallengeDTO>) request.getAttribute("allList");
	List<ChallengeDTO> searchList = (List<ChallengeDTO>) request.getAttribute("searchList");
%>
<div class="container-scroller">
   <%@include file="/WEB-INF/inc/navbar.jsp" %>
    
    <div class="container-fluid page-body-wrapper">
        <!-- sidebar -->
        <%@include file="/WEB-INF/inc/sidebar.jsp" %>
        <!-- partial -->
        <div class="main-panel">
        <!--메인  -->
            <div class="content-wrapper">
            
            <a style="font-size:1.5rem; padding : 4rem;">Challenge</a>

            <form method="get" class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="challengemain.do">
                <div class="input-group">
                    <input class="form-control" style=""type="text" placeholder="Search for..." aria-label="Search for..." aria-describedby="btnNavbarSearch" id="search" name="search" />
                    <button class="btn btn-primary" id="btnNavbarSearch" style="margin-left:2rem; margin-top : -0.5rem;"><i class="fas fa-search"></i></button>
                 
                </div>          
            </form>
            
            <form method="get" class="d-none d-md-inline-block form-inline ms-auto me-0 me-md-3 my-2 my-md-0" action="challengecreate.do">
            <button class="btn btn-primary"style="margin-left:10rem;"> 챌린지 만들기 </button>
            </form>

            <p style="margin:6rem;"></p>
            
            <%
            	if(searchList != null) {
            		
            		%>
            		<a style="padding: 1.2rem;">Search Challenge </a>
            		<div class="row" style="margin-top:1.5rem;">
            		<% 
            			for(ChallengeDTO challenge: searchList){
            		
            %>
           
				              
		            
		            		<div class="col-xl-3 col-md-6" style="padding-left:3rem;">
                              <div class="card bg-primary text-white mb-4">
                                  <div class="card-body"><%= challenge.getName() %></div>
                                  <div class="card-footer d-flex align-items-center justify-content-between">
                                      <a class="small text-white stretched-link" href="#"><%= challenge.getCreateDate() %></a>
                                      <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                  </div>
                              </div>
                          </div>
            <% 
          			}
            	}
            	else {
            %>
            <a style="padding: 1.2rem;">My Challenge</a>
            <div class="row" style="margin-top:1.5rem;">
            	<%
					if(challengeList != null) {
						%>
						
						<% 
						for(ChallengeDTO challenge: challengeList){
				%>
				 
				<div class="col-xl-3 col-md-6" style="padding-left:3rem;">
                                <div class="card bg-primary text-white mb-4">
                                    <div class="card-body"><%= challenge.getName() %></div>
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link" href="#"><%= challenge.getCreateDate() %></a>
                                        <div class="small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
				
				<%
						}
					} 
				%>
				</div>
         
            <p style="margin:6rem;"></p>
            <a style="padding: 1.2rem;">More Challenge</a>
            <div class="row" style="margin-top:1.5rem;">
            	<%
					if(challengeAllList != null) {
						for(ChallengeDTO challenge: challengeAllList){
				%>
				<div class="col-xl-3 col-md-6" style="padding-left:3rem;">
                                <div class="card bg-primary text-white mb-4" onclick="joinchallenge(<%=challenge.getSeq() %>)">
                                    <div class="card-body" ><%= challenge.getName() %></div>
              			
                                    <div class="card-footer d-flex align-items-center justify-content-between">
                                        <a class="small text-white stretched-link"><%= challenge.getCreateDate() %></a>
                    
                                        <div class="₩   small text-white"><i class="fas fa-angle-right"></i></div>
                                    </div>
                                </div>
                            </div>
				
				<%
						}
					}
				%>
				</div>
				 <% } %>
            </div>
           
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <%@include file="/WEB-INF/inc/footer.jsp" %>
             
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
       
    </div>
</div>


<script src="/vendors/js/vendor.bundle.base.js"></script>
<script src="/vendors/js/vendor.bundle.addons.js"></script>

<script src="/js/off-canvas.js"></script>
<script src="/js/misc.js"></script>


<script>
	function joinchallenge(seq) {
		console.log("seq : ", seq);
		var result = confirm('가입하시겠습니까?');
		const element = document.getElementsByClassName('challengeseq'); // [object HTMLDivElement]
		const element2 = document.getElementById('challengeseq'); // [object HTMLDivElement]
		if(result === true) {
			location.href="challengejoin.do?seq="+seq;
		} else {
			// 미가입하기
		}
	}
	
	
</script>
</body>

</html>
