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
 * 
 * 타임테이블에 일덩을 추가하는 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/todolist/popupok.do")
public class PopUpOk extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String starttime = req.getParameter("starttime");
        String endtime = req.getParameter("endtime");
        String content = req.getParameter("content");
        String mseq = (String) session.getAttribute("seq");

        TimeDTO dto = new TimeDTO();

        dto.setStarttime(starttime);
        dto.setEndtime(endtime);
        dto.setContent(content);
        dto.setMseq(mseq);

        TimeDAO dao = new TimeDAO();

        dao.add(dto);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/popupok.jsp");
        dispatcher.forward(req, resp);
    }
}