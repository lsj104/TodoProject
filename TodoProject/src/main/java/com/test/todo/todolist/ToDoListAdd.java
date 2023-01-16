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

/**
 * 
 * ToDoList 추가 클래스
 * 
 * @author 4조
 *
 */
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
		String type = req.getParameter("type");
		
		System.out.println(content);
		
		int result = 0;
		
		if (content != null && !content.equals("") && content != "" ) {
		
			//1.
			ToDoListDAO dao = new ToDoListDAO();
			
			if (type.equals("1")) {
				result = dao.todolistAdd((String)session.getAttribute("auth"), content, type);
			} else if(type.equals("2")) {
				result = dao.todolistAdd((String)session.getAttribute("auth"), content, type);
			}
		
		}
 
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
			
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();
			
	}

}
