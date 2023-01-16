package com.test.todo.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * MyPageEdit 클래스
 * 회원 프로필 사진, 닉네임, 비밀번호 변경이 가능합니다.
 * 
 * @author 4조
 *
 */
@WebServlet("/member/mypageedit.do")
public class MyPageEdit extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MyPageEdit.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/mypageedit.jsp");
		dispatcher.forward(req, resp);

	}

}