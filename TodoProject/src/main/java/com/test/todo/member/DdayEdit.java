package com.test.todo.member;

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
 * 디데이 수정 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/todolist/ddayedit.do")
public class DdayEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String ddayname = req.getParameter("ddayname");
		String ddate = req.getParameter("ddate");
		
		System.out.println("ddayname" + ddayname);
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.ddayEdit((String)session.getAttribute("auth"), ddayname, ddate);
		
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();
		
	}

}

