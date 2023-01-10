package com.test.todo.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/emailok.do")
public class EmailOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		MemberDAO dao = new MemberDAO();
		
		int resultemail = dao.checkemail(req.getParameter("email"));
		
		resp.setCharacterEncoding("UTF-8");
		
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(resultemail);
		
		writer.close();
		
	}

}