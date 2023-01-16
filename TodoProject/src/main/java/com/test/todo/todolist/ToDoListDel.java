package com.test.todo.todolist;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * ToDoList  삭제 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/todolist/todolistdel.do")
public class ToDoListDel extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ToDoListDel.java

		HttpSession session = req.getSession();
		
		String seq = req.getParameter("seq");
		String type = req.getParameter("type");
		
		System.out.println("seq:"+seq);
		ToDoListDAO dao = new ToDoListDAO();
		int result = dao.todolistDel(seq, type);
		
		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
			
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();
		
	}

}
