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

@WebServlet("/member/mypoint.do")
public class MyPoint extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//MyPoint.java
		
		HttpSession session = req.getSession();
		
		String seq = (String)session.getAttribute("seq");
		String date = req.getParameter("date");
		
		MemberDAO dao = new MemberDAO();
		
		PlusRewardDAO pdao = new PlusRewardDAO();
		
		ArrayList<PointDTO> pointList = null;
		ArrayList<PlusRewardDTO> plusPointList = null;
		
		
		
		System.out.println(date);
		
		if (date == null || date == "") {
		
			pointList = dao.pointlist(seq);
			plusPointList =  pdao.pluspointlist((String)session.getAttribute("auth"));
			
		
		} else {
			
			String replaceDate = date.replace(" ", "");
			
			String[] splitDate = replaceDate.split("~");
			
			pointList = dao.checkPeriod(splitDate[0], splitDate[1], seq);
			plusPointList = pdao.checkPeriod(splitDate[0], splitDate[1], (String)session.getAttribute("auth"));
		}
		
		// 날짜 자르기
		for (PointDTO dto : pointList) {
			
			String regdate = dto.getRegdate();
			
			regdate = regdate.substring(0, 10);
			
			dto.setRegdate(regdate);
			
		}
		
		for (PlusRewardDTO pdto : plusPointList) {
			
			String regdate = pdto.getRegdate();
			
			regdate = regdate.substring(0,4)+ "-" + regdate.substring(5,7) + "-" + regdate.substring(8,10);
			
			pdto.setRegdate(regdate);
			
		}
		
		req.setAttribute("pointList", pointList);
		req.setAttribute("plusPointList", plusPointList);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/member/mypoint.jsp");
		dispatcher.forward(req, resp);

	}

}