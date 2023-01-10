package com.test.todo.todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/todolist/todolistadd.do")
public class ToDoListAdd extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ToDoListAdd.java
		//1. DB 작업 > DAO 위임'
		//2. 결과 + ?JSP 호출하기
		
		
		//세션 열기
		HttpSession session = req.getSession();
		
		req.setCharacterEncoding("UTF-8");
		String content = req.getParameter("content");
		
		System.out.println(content);
		
		int result = 0;
		
		if (content != null && !content.equals("") && content != "" ) {
		
			//1.
			ToDoListDAO dao = new ToDoListDAO();
			result = dao.todolistAdd((String)session.getAttribute("auth"), content);
		
		}
 
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
			
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();
			
	}

}
