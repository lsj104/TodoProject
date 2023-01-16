package com.test.todo.todolist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * TimeDel클래스
 * 시간표 삭제
 * @author 4조
 */
@WebServlet("/todolist/timedel.do")
public class TimeDel extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String mseq = (String) session.getAttribute("seq");

        TimeDAO dao = new TimeDAO();
        int result = dao.del(mseq);

        req.setAttribute("result", result);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/timedel.jsp");
        dispatcher.forward(req, resp);

    }
}
