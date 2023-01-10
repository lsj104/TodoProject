<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  

<nav class="sidebar sidebar-offcanvas" id="sidebar">
            <ul class="nav">
                <li class="nav-item nav-profile">
                    <a href="#" class="nav-link">
                        <div class="nav-profile-image">
                            <img src="/todo/asset/images/profile/${image}" alt="profile">
                            <span class="login-status online"></span> <!--change to offline or busy as needed-->
                        </div>
                        <div class="nav-profile-text d-flex flex-column">
                            <span class="font-weight-bold mb-2">${nickname}</span>
                            <span class="text-secondary text-small">${message}</span>
                        </div>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
                        <span class="menu-title">Todo List</span>
                        <i class="menu-arrow"></i>
                        <i class="mdi mdi-format-list-bulleted menu-icon"></i>
                    </a>
                    <div class="collapse" id="ui-basic">
              			<ul class="nav flex-column sub-menu">
                			<li class="nav-item"> <a class="nav-link" href="/todo/todolist/todolistmain.do">ToDoList</a></li>
							<c:if test="${not empty auth and timeTable == 'y'}">			
                			<li class="nav-item"> <a class="nav-link" href="/todo/todolist/timetable.do">TimeTable</a></li>
                			</c:if>
                			<c:if test="${not empty auth and timeCal == 'y' }">
                			<li class="nav-item"> <a class="nav-link" href="/todo/calmain.do">Calendar</a></li>
                			</c:if>
                			<c:if test="${not empty auth and timeCir == 'y'}">	
                			<li class="nav-item"> <a class="nav-link" href="/todo/todolist/cirmain.do">Circle</a></li>
              				</c:if>
              			</ul>
            		</div>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/todo/challenge/challengemain.do">
                        <span class="menu-title">Challenge</span>
                        <i class="mdi mdi-bulletin-board menu-icon"></i>
                    </a>
                    
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/todo/item/itemlist.do">
                        <span class="menu-title">Store</span>
                        <i class="mdi mdi-store menu-icon"></i>
                    </a>
                </li>
                <li class="nav-item">
            		<a class="nav-link" href="/todo/chart/chart.do">
	              		<span class="menu-title">Ranking</span>
		              	<i class="mdi mdi-trophy menu-icon"></i>
	            	</a>
          		</li>
                <li class="nav-item">
                    <a class="nav-link" href="/todo/board/boardmain.do">
                        <span class="menu-title">Board</span>
                        <i class="mdi mdi-bulletin-board menu-icon"></i>
                    </a>
                </li>
            </ul>
        </nav>
