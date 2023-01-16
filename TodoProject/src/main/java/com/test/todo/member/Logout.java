package com.test.todo.member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * Logout 클래스
 * 사용자의 이메일, 닉네임, 응원메시지를 세션에서 제거합니다.
 * @author 4조
 */
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










































