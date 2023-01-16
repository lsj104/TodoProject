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

/**
 * 
 * 챌린지 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/challenge/challengemain.do")
public class ChallengeMain extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.out.println("search : " + req.getParameter("search"));
		ChallengeDAO dao = new ChallengeDAO();
		
		HttpSession session = req.getSession();
  		
  		String nickname = (String)session.getAttribute("nickname");
		
		String search = req.getParameter("search");
		String mseq = session.getAttribute("seq").toString();
		
		
		if(search != null && search != "") {
			List<ChallengeDTO> searchList = dao.searchChallengeList(search);
			req.setAttribute("searchList", searchList);
		}else {
			List<ChallengeDTO> dtoList = dao.getChallengeList(nickname, mseq);
			List<ChallengeDTO> allDtoList = dao.getAllChallengeList(Integer.parseInt(mseq));
			System.out.println("list" + allDtoList.toString());
			req.setAttribute("list", dtoList);
			req.setAttribute("allList", allDtoList);
		}
	
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/challenge/challengemain.jsp");
		dispatcher.forward(req, resp);

	}
	

}
