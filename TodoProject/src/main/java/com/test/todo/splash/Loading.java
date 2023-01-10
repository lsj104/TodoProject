package com.test.todo.splash;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/splash/loading.do")
public class Loading extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	
    	
    	RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/splash/loading.jsp");
        dispatcher.forward(req, resp);
    }
}
