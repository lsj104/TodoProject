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
 * 응원메세지 수정 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/member/messagechange.do")
public class MessageChange extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		String messageChange = req.getParameter("messageChange");
		
		System.out.println("message:" + messageChange);
		
		MemberDAO mdao = new MemberDAO();
		
		int result = mdao.messageChange((String)session.getAttribute("auth"),messageChange);
		
		System.out.println(result);
		
		String message =  mdao.getMessage((String)session.getAttribute("auth"));
		
		session.setAttribute("message", message);
		
		
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();

	}

}

