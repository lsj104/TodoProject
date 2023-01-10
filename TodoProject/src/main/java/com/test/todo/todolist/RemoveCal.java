package com.test.todo.todolist;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/removeCal.do")
public class RemoveCal extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		CalDAO dao = new CalDAO();
		
		HttpSession session = req.getSession();
   	 
  		
  		// String nickname = session.getAttribute("nickname").toString();
//		String mseq = session.getAttribute("seq").toString();
		
		String seq = req.getParameter("seq");
	
		dao.delCalList(Integer.parseInt(seq));
	
		
		
		resp.sendRedirect("calmain.do");
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/calmain.jsp");
		//dispatcher.forward(req, resp);

	}
	

}