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


@WebServlet("/challenge/challengejoin.do") // 챌린지 가입 java
public class ChallengeJoin extends HttpServlet {

	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ChallengeDAO dao = new ChallengeDAO();
		
		HttpSession session = req.getSession();
   		String seq = req.getParameter("seq");
		
		String mseq = session.getAttribute("seq").toString();
		String nickname = session.getAttribute("nickname").toString();	
			
		dao.challengejoin2(Integer.parseInt(seq),Integer.parseInt(mseq), nickname);
		// req.setAttribute("categorys", categorys);
		
		resp.sendRedirect("challengemain.do");
		
		//RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/challenge/challengejoin.jsp");
		//dispatcher.forward(req, resp);

	}
	

}
