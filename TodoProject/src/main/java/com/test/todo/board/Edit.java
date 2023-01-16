package com.test.todo.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Edit 클래스 게시글 수정
 * 
 * @author 4조
 */
@WebServlet("/board/edit.do")
public class Edit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Edit.java
				//1. ������ ��������(seq)
				//2. DB �۾� > DAO ���� > select
				//3. ��� ��ȯ + JSP ȣ���ϱ�
				
				//1.
				String seq = req.getParameter("seq");
				
				//2.
				BoardDAO dao = new BoardDAO();
				
				BoardDTO dto = dao.get(seq);
				
				//3.
				req.setAttribute("dto", dto);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/edit.jsp");
		dispatcher.forward(req, resp);

	}

}
































