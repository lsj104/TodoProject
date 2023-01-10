package com.test.todo.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/member/mypageedit.do")
public class MyPageEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MyPageEdit.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/mypageedit.jsp");
		dispatcher.forward(req, resp);

	}

}