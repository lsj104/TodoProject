package com.test.todo.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 이메일을 입력받아 임시 비밀번호를 생성하기 위한 서블릿
 * @author Seojin
 *
 */
@WebServlet("/member/findpwok.do")
public class FindPwOk extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = new MemberDTO();
		
		String pw = req.getParameter("code");
		String email = req.getParameter("email");
		
		System.out.println(pw);
		
		dto.setPw(pw);
		dto.setEmail(email);
		
		int result = dao.findPw(dto);

		
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		
		PrintWriter writer = resp.getWriter();
		
		writer.print(result);

		
		writer.close();

	}

}