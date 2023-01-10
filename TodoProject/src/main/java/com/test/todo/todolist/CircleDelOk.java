package com.test.todo.todolist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/todolist/circledelok.do")
public class CircleDelOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        req.setCharacterEncoding("UTF-8");

        String mseq = req.getParameter("seq");

        CirDAO dao = new CirDAO();

        dao.del(mseq);

        int result = dao.del(mseq);

        if (result == 1) {
            resp.sendRedirect("/todo/timetable.do");
        } else {
            PrintWriter writer = resp.getWriter();
            writer.print("<script>");
            writer.print("alert('failed');");
            writer.print("history.back();");
            writer.print("</script>");
            writer.close();
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/circledelok.jsp");
        dispatcher.forward(req, resp);
    }
}
