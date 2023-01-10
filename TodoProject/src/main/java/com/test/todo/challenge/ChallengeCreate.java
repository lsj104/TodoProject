package com.test.todo.challenge;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/challenge/challengecreate.do")
public class ChallengeCreate extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ChallengeDAO dao = new ChallengeDAO();
		
		HttpSession session = req.getSession();
   	 
		
			
			List<CategoryDTO> categorys = dao.getCategoryList();
			req.setAttribute("categorys", categorys);
	
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/challenge/challengecreate.jsp");
		dispatcher.forward(req, resp);

	}
	

}
