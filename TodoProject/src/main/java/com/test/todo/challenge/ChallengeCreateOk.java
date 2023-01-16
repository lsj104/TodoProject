package com.test.todo.challenge;

import java.io.IOException;
import java.net.URLEncoder;
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
 * 챌린지 생성완료 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/challenge/challengecreateok.do")
public class ChallengeCreateOk extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		System.out.println("tttt");
		
		
		HttpSession session = req.getSession();
		
		
		req.setCharacterEncoding("UTF-8");
		
		String seq = session.getAttribute("seq").toString();
		System.out.println("seq : " + seq);

		String title = req.getParameter("title");
		String category = req.getParameter("category");
		String mission = req.getParameter("mission");
		String memberCnt = req.getParameter("membercnt");
		String period = req.getParameter("period");
		System.out.println("title : " + title);
		System.out.println("category : " + category);
		System.out.println("mission : " + mission);
		System.out.println("memberCnt : " + memberCnt);
		System.out.println("period : " + period);
		
		
	
		ChallengeDAO dao = new ChallengeDAO();
		dao.createChallenge(Integer.parseInt(seq), title, Integer.parseInt(period), Integer.parseInt(memberCnt), mission, Integer.parseInt(category));
		
		resp.sendRedirect("challengemain.do");
//		RequestDispatcher dispatcher = req.getRequestDispatcher("/todo/challengemain.do");
//		dispatcher.forward(req, resp);

	}
}
