package com.test.todo.member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/member/logout.do")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
    	HttpSession session = req.getSession();
    	
        session.removeAttribute("auth");
        session.removeAttribute("nickname");
        session.removeAttribute("message");
        session.removeAttribute("category");
        session.removeAttribute("seq");
        session.removeAttribute("pw");
        session.removeAttribute("image");
   
        resp.sendRedirect("/todo/member/login.do");
    }
}










































