<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<header>
	<h1>
		<i class="fas fa-coffee" style="<c:if test="${not empty auth and lv == 1}">color: cornflowerblue;</c:if><c:if test="${not empty auth and lv == 2}">color: tomato;</c:if>"></i>
		Toy Project
		
		<c:if test="${not empty auth}">
		<span>by ${auth}</span>
		</c:if>
	</h1>
	<nav>
		<ul>
			<li><a href="/toy/index.do">Home</a>
			
			<c:if test="${empty auth}">
			<li><a href="/toy/member/register.do">Register</a>
			<li><a href="/toy/member/login.do">Login</a>
			</c:if>
			
			<c:if test="${not empty auth}">
			<li><a href="/toy/member/unregister.do">Unregister</a>
			<li><a href="/toy/member/logoutok.do">Logout</a>
			<li><a href="/toy/member/info.do">Info</a>
			</c:if>
			
			<li><a href="/toy/board/list.do">Board</a>
			
			<c:if test="${not empty auth and lv == 2}">
			<li><a href="/toy/admin/chart.do">Chart</a>
			</c:if>
			
			<li><a href="#!">OOO</a>
		</ul>
	</nav>
</header>
