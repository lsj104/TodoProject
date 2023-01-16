package com.test.todo.todolist;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * CirclePopup 클래스
 * 원형 시간표를 수정하는 팝업
 * @author 4조
 */
@WebServlet("/todolist/circlepopup.do")
public class CirclePopup extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/circlepopup.jsp");
        dispatcher.forward(req, resp);
    }
}
