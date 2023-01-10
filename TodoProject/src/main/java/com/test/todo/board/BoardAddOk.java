package com.test.todo.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/boardaddok.do")
public class BoardAddOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//AddOk.java
		//1. 인코딩 + 데이터 가져오기
		//2. DB 작업 > DAO 위임 > insert
		//3. 결과
		//4. 피드백
		

		
		HttpSession session = req.getSession();
		
		String email = (String)session.getAttribute("auth");
		
		
		//1.
		req.setCharacterEncoding("UTF-8");
		
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		//2.
		
		
		BoardDAO dao = new BoardDAO();
		
		int result = dao.add(email,title,content);
		
		System.out.println(result);
		
		//4.
		if (result == 1) {
			resp.sendRedirect("/todo/board/boardmain.do");
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();			
		}
		

	}

}

































