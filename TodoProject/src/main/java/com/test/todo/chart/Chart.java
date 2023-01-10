package com.test.todo.chart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.todo.item.MinusRewardDAO;
import com.test.todo.member.MemberDAO;
import com.test.todo.member.MemberInfoDTO;
import com.test.todo.member.PlusRewardDAO;

@WebServlet("/chart/chart.do")
public class Chart extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		MemberDAO dao = new MemberDAO();
		
		ArrayList<MemberInfoDTO> mlist = dao.pointRank();
		
		System.out.println(mlist.toString());
		
		req.setAttribute("mlist", mlist);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/chart/chart.jsp");
		dispatcher.forward(req, resp);

		
	}

}