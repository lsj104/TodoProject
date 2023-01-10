package com.test.todo.board;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/view.do")
public class View extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//View.java
		//1. 데이터 가져오기(seq)
		//2. DB 작업 > DAO 위임 > select
		//3. 결과 + JSP 호출하기
		
		
		HttpSession session = req.getSession();
		String email = (String)session.getAttribute("auth");
		
		//1.
		String seq = req.getParameter("seq");
		
		
		
		//2.
		BoardDAO dao = new BoardDAO();
		
		
		if (session.getAttribute("readcount") == null || session.getAttribute("readcount").toString().equals("n")) {
			//2.3
			dao.addReadCount(seq);
			
			session.setAttribute("readcount", "y");
		}
		
		
		BoardDTO dto = dao.get(seq);
		
		
		//2.5
		//- 데이터 조작
		String content = dto.getContent();
		String title = dto.getTitle();
		
		
		//태그 이스케이프
		content = content.replace("<", "&lt;").replace(">", "&gt;");
		title = title.replace("<", "&lt;").replace(">", "&gt;");
		
		
		dto.setTitle(title);
		
		
		//개행 문자 처리
		content = content.replace("\r\n", "<br>");
		
		dto.setContent(content);
		
		ArrayList<AnswerDTO> alist = dao.alist(seq);
		
		//3.
		req.setAttribute("dto", dto);
		
		req.setAttribute("alist", alist);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/view.jsp");
		dispatcher.forward(req, resp);

	}

}















































