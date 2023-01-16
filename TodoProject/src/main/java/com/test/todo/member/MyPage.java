package com.test.todo.member;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * MyPage 클래스
 * 회원이 설정한 카테고리 변경, 개인 정보 수정, 포인트 내역 조회, 회원 탈퇴가 가능합니다.
 * 
 * @author 4조
 *
 */
@WebServlet("/member/mypage.do")
public class MyPage extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// MyPage.java
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberDTO> list = dao.list();
		
		req.setAttribute("list", list);
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/mypage.jsp");
		dispatcher.forward(req, resp);

	}

}