package com.test.todo.todolist;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 일정 추가 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/insertCal.do")
public class InsertCal extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CalDAO dao = new CalDAO();
		
		HttpSession session = req.getSession();
   	 
  		
  		String nickname = session.getAttribute("nickname").toString();
		String mseq = session.getAttribute("seq").toString();
		
		String content = req.getParameter("content");
		String start_date = req.getParameter("start_date");

		String end_date = req.getParameter("end_date");
		
		System.out.println(content + start_date + end_date);
		
		dao.setCalList(content, start_date, end_date, Integer.parseInt(mseq));
		
		resp.sendRedirect("calmain.do");
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/calmain.jsp");
		//dispatcher.forward(req, resp);

	}
	

}