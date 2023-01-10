<%@ page import = "java.util.*" %>
<%@ page import = "com.test.todo.todolist.CalDTO" %>


<%
	List<CalDTO> calList = (List<CalDTO>) request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
    <!-- Required meta tags -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
     <!-- jquery CDN -->
  <!-- fullcalendar CDN -->
  
    <%@include file="/WEB-INF/inc/asset.jsp" %>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  <link href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css' rel='stylesheet' />
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script> 
  <!-- fullcalendar 언어 CDN -->
  <script src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script> 
 
    <title>Calendar</title>
    <!-- plugins:css -->
     
		
</head>

<body>

<div class="container-scroller">
   <%@include file="/WEB-INF/inc/navbar.jsp" %>
    
    <div class="container-fluid page-body-wrapper">
        <!-- sidebar -->
        <%@include file="/WEB-INF/inc/sidebar.jsp" %>
        <!-- partial -->
        <div class="main-panel">
        <!--메인  -->
            <div class="content-wrapper">
					            
					
					
					<div id="calendarBox">
			        <div id="calendar"></div>
			    </div> 
			
			    <!-- modal 추가 -->
			    <div class="modal fade" id="calendarModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			        aria-hidden="true">
			        <div class="modal-dialog" role="document">
			            <div class="modal-content">
			                <div class="modal-header">
			                    <h5 class="modal-title" id="exampleModalLabel">일정을 입력하세요.</h5>
			                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
			                        <span aria-hidden="true">X</span>
			                    </button>
			                </div>
			                <div class="modal-body">
			                    <div class="form-group">
			                        <label for="taskId" class="col-form-label">일정 내용</label>
			                        <input type="text" class="form-control" id="calendar_content" name="calendar_content">
			                        <label for="taskId" class="col-form-label">시작 날짜</label>
			                        <input type="date" class="form-control" id="calendar_start_date" name="calendar_start_date">
			                        <label for="taskId" class="col-form-label">종료 날짜</label>
			                        <input type="date" class="form-control" id="calendar_end_date" name="calendar_end_date">
			                    </div>
			                </div>
			                <div class="modal-footer ">
			                    <button type="button" class="btn btn-warning" id="addCalendar" >추가</button>
			                    <button type="button" class="btn btn-secondary" data-dismiss="modal"
			                        id="sprintSettingModalClose">취소</button>
			                </div>
			    
			            </div>
			        </div>
			    </div>
			    
			    
			    <!-- modal 추가 -->
			    <div class="modal fade" id="calendarDelete" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
			        aria-hidden="true">
			        <div class="modal-dialog" role="document">
			            <div class="modal-content">
			               
			                <div class="modal-body">
			                    <div class="form-group">
			                    	<h5 class="modal-title" id="exampleModalLabel" style="text-align:center; margin-top:1.2rem;">삭제하시겠습니까?</h5>
			                        <button type="button" class="btn btn-warning" id="deletecal" style="margin-left : 8rem; margin-top : 2rem;">삭제</button>
			                         <button type="button" class="btn btn-secondary" data-dismiss="modal"
			                        id="sprintSettingModalClose" style="margin-top : 2rem;">취소</button>
			                    </div>
			                </div>
			       
			            </div>
			        </div>
			    </div>
			    


			    
			   			
					
            
            </div>
           
            <!-- content-wrapper ends -->
            <!-- partial:../../partials/_footer.html -->
            <%@include file="/WEB-INF/inc/footer.jsp" %>
             
            <!-- partial -->
        </div>
        <!-- main-panel ends -->
       
    </div>
</div>

<script>

	let seq = -1;

	$("#deletecal").on("click",function(){  // modal의 추가 버튼 클릭 시
		
    	console.log("seq : ", seq);
    	location.href='removeCal.do?seq='+seq;
       
    });

document.addEventListener('DOMContentLoaded', function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        timeZone: 'UTC',
        initialView: 'dayGridMonth', // 홈페이지에서 다른 형태의 view를 확인할  수 있다.
        events:[ // 일정 데이터 추가 , DB의 event를 가져오려면 JSON 형식으로 변환해 events에 넣어주면된다.
             
            	<%
            	if(calList != null) {
            		for(CalDTO cal: calList){
            			
            	%>
            	{
           
                title: '<%= cal.getContent()%>',
                start: '<%= cal.getStartdate()%>',
                end:'<%= cal.getEndd()%>' ,
                seq: '<%= cal.getSeq() %>',
                // color 값을 추가해 색상도 변경 가능 자세한 내용은 하단의 사이트 참조
                color : '#' + Math.round(Math.random() * 0xffffff).toString(16) 
            	},
            	
            		<%
            	} }
            		%>
        
        ], 
        
        
        // 클릭 이벤트 > 수정 or 삭제
        eventClick: function (info) {
        	
        	seq = info.event._def.extendedProps.seq
        	$("#calendarDelete").modal("show"); 
        	
    	},
    	
    	
    	
    	
        headerToolbar: {
            center: 'addEventButton' // headerToolbar에 버튼을 추가
        }, customButtons: {
            addEventButton: { // 추가한 버튼 설정
                text : "일정 추가",  // 버튼 내용
                click : function(){ // 버튼 클릭 시 이벤트 추가
                    $("#calendarModal").modal("show"); // modal 나타내기

                    $("#addCalendar").on("click",function(){  // modal의 추가 버튼 클릭 시
                        var content = $("#calendar_content").val(); 
                        var start_date = $("#calendar_start_date").val();
                        var end_date = $("#calendar_end_date").val();
                        
                        //내용 입력 여부 확인
                        if(content == null || content == ""){
                            alert("내용을 입력하세요.");
                        }else if(start_date == "" || end_date ==""){
                            alert("날짜를 입력하세요.");
                        }else if(new Date(end_date)- new Date(start_date) < 0){ // date 타입으로 변경 후 확인
                            alert("종료일이 시작일보다 먼저입니다.");
                        }else{ // 정상적인 입력 시
                        	
                            console.log(content);
                        	console.log(start_date);
                        	console.log(end_date);
                            // console.log(obj); //서버로 해당 객체를 전달해서 DB 연동 가능
                            
                            location.href='/todo/insertCal.do?content='+content+'&start_date='+start_date+'&end_date='+end_date;
                        }
                    });
                }
            }
        },
        editable: true, // false로 변경 시 draggable 작동 x 
        displayEventTime: false, // 시간 표시 x
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
    });
    calendar.render()
function getYmd10(date) {
    //yyyy-mm-dd 포맷 날짜 생성
    var d = new Date(date)
    return d.getFullYear() + "-" + ((d.getMonth() + 1) > 9 ? (d.getMonth() + 1).toString() : "0" + (d.getMonth() + 1)) + "-" + (d.getDate() > 9 ? d.getDate().toString() : "0" + d.getDate().toString());
}
});

		</script>
		<script src="https://code.jquery.com/ui/1.13.0/jquery-ui.js"></script>

</body>

</html>
