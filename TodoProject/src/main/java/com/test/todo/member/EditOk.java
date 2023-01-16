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
 * EditOk 클래스
 * 회원이 설정한 카테고리 변경 작업을 합니다.
 * 
 * @author 4조
 *
 */
@WebServlet("/member/editok.do")
public class EditOk extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//EditOk.java
		
		HttpSession session = req.getSession();
		
		String seq = (String)session.getAttribute("seq");
		String value = req.getParameter("value"); 
		
		
		req.setCharacterEncoding("UTF-8");
		
		MemberDAO dao = new MemberDAO();
		
		int result = dao.editCategory(value, seq);
		
		if (result == 1) {
			
			session.setAttribute("category", value);
			
			resp.sendRedirect("/todo/member/mypage.do");
			
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
