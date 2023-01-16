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

/**
 *
 * 댓글을 추가하는 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/board/answeraddok.do")
public class AnswerAddOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		// 1.
		req.setCharacterEncoding("UTF-8");

		String content = req.getParameter("content");
		String qseq = req.getParameter("qseq");

		System.out.println(content);

		System.out.println(qseq);

		String mseq = (String) session.getAttribute("seq");

		// 2.
		AnswerDTO dto = new AnswerDTO();

		dto.setContent(content);
		dto.setQseq(qseq);
		dto.setName((String) session.getAttribute("auth"));
		dto.setMseq((String) session.getAttribute("seq"));

		BoardDAO dao = new BoardDAO();

		int result = dao.addComment(dto);

		if (result == 1) {
			resp.sendRedirect("/todo/board/view.do?seq=" + qseq);
		} else {
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('failed');");
			writer.print("history.back();");
			writer.print("</script>");
			writer.close();
		}

		/*
		 * RequestDispatcher dispatcher =
		 * req.getRequestDispatcher("/WEB-INF/views/board/answeraddok.jsp");
		 * dispatcher.forward(req, resp);
		 */

	}

}
