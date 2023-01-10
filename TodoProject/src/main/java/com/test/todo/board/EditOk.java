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

@WebServlet("/board/editok.do")
public class EditOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditOk.java
				//1. ������ ��������
				//2. DB �۾� > DAO ���� > update
				//3. ���
				//4. �ǵ��
				
				HttpSession session = req.getSession();
				
				
				//1.
				req.setCharacterEncoding("UTF-8");
				
				String title = req.getParameter("title");
				String content = req.getParameter("content");
				String seq = req.getParameter("seq");
				
				//2.
				BoardDTO dto = new BoardDTO();
				
				dto.setTitle(title);
				dto.setContent(content);
				dto.setSeq(seq);
						
				
				BoardDAO dao = new BoardDAO();
				
				int result = dao.edit(dto);
				
				
				//4.
				if (result == 1) {
					resp.sendRedirect("/todo/boardmain.do");
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