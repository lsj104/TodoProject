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
 * CirclePopupOk클래스
 * 원형 시간표를 수정하는 팝업
 * @author 4조
 */
@WebServlet("/todolist/circlepopupok.do")
public class CirclePopupOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String content = req.getParameter("content");
        String mseq = (String) session.getAttribute("seq");

        CirDTO dto = new CirDTO();

        dto.setStarttime(starttime);
        dto.setEndtime(endtime);
        dto.setContent(content);
        dto.setMseq(mseq);

        CirDAO dao = new CirDAO();

        dao.add(dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/circlepopupok.jsp");
        dispatcher.forward(req, resp);
    }
}
