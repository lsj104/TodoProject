package com.test.todo.todolist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

/**
 * TimeTable 클래스
 * 시간표
 * @author 4조
 */
@WebServlet("/todolist/timetable.do")
public class TimeTable extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String content = req.getParameter("content");
        String mseq = (String) session.getAttribute("seq");

        TimeDTO dto = new TimeDTO();
        dto.setMseq(mseq);
        dto.setStarttime(starttime);
        dto.setEndtime(endtime);
        dto.setContent(content);

        TimeDAO dao = new TimeDAO();
        ArrayList<TimeDTO> list = dao.timetable(dto);

//        System.out.println(content);

        req.setAttribute("list", list);

        System.out.println(list);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/timetable.jsp");
        dispatcher.forward(req, resp);
    }
}
