package com.test.todo.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * UnregisterOk 클래스
 * 회원의 회원 탈퇴
 * 
 * @author 4조
 *
 */
@WebServlet("/member/unregisterok.do")
public class UnregisterOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//UnregisterOk.java
		
		HttpSession session = req.getSession();
		
		String seq = (String)session.getAttribute("seq");
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.unRegisterMember(seq);
		
		if (result == 1) {
			
			session.invalidate(); 
			
			resp.sendRedirect("/todo/home.do");
			
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
