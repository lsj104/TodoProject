package com.test.todo.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/board/delok.do")
public class DelOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//DelOk.java
				//1. ������ ��������(seq)
				//2. DB �۾� > DAO ���� > delete
				//3. ���
				//4. �ǵ��
				
				HttpSession session = req.getSession();
				
				
				//1.
				req.setCharacterEncoding("UTF-8");
				
				String seq = req.getParameter("seq");
				
				//2.
				BoardDAO dao = new BoardDAO();
				
				int result = dao.del(seq);
				
				
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




































