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

@WebServlet("/challenge/challengesearch.do")
public class ChallengeSearch  extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("search : " + req.getParameter("search"));
	
		HttpSession session = req.getSession();
		String mseq = session.getAttribute("seq").toString();
		
		ChallengeDAO dao = new ChallengeDAO();
		
		List<ChallengeDTO> allDtoList = dao.getAllChallengeList(Integer.parseInt(mseq));

		
		req.setAttribute("allList", allDtoList);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/challenge/challengemain.jsp");
		dispatcher.forward(req, resp);

	}
}
