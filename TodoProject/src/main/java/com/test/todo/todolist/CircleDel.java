package com.test.todo.todolist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/todolist/circledel.do")
public class CircleDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        String mseq = (String) session.getAttribute("seq");

        CirDAO dao = new CirDAO();
        int result = dao.del(mseq);

        req.setAttribute("result", result);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/circledel.jsp");
        dispatcher.forward(req, resp);
    }
}
