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
 * Cirmain 클래스
 * 원형 시간표 클래스
 * @author 4조
 */
@WebServlet("/todolist/cirmain.do")
public class CirMain extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String content = req.getParameter("content");
        String mseq = (String) session.getAttribute("seq");

        CirDTO dto = new CirDTO();
        dto.setMseq(mseq);
        dto.setStarttime(starttime);
        dto.setEndtime(endtime);
        dto.setContent(content);

        CirDAO dao = new CirDAO();
        ArrayList<CirDTO> clist1 = dao.listChart1(dto);
//        ArrayList<CirDTO> clist2 = dao.listChart2();

        req.setAttribute("clist1", clist1);
//        req.setAttribute("clist2", clist2);

        System.out.println(clist1);


        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/cirmain.jsp");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/circlecalendar.jsp");
        dispatcher.forward(req, resp);

    }

}