package com.test.todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * Home 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/home.do")
public class Home extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//Home.java

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/home.jsp");
		dispatcher.forward(req, resp);

	}
	

}
