package com.test.todo.todolist;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.todo.member.MemberDAO;
import com.test.todo.member.MemberInfoDTO;


/**
 * 
 * Main ToDoList 클래스
 * 로그인한 회원의 Main ToDoList와 회원이 설정한 디데이 출력
 * 
 * @author haha
 *
 */
@WebServlet("/todolist/todolistmain.do")
public class ToDoListMain extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		//ToDolist

		//세션 열기
		HttpSession session = req.getSession();
		//1. DB 작업 > DAO 위임'
		
		//2. 결과 + ?JSP 호출하기
		
		//1.
		
		ToDoListDAO dao = new ToDoListDAO();
		ArrayList<ToDoListDTO> todolist = dao.todolist((String)session.getAttribute("auth"), "1");
		
		//ArrayList<ChallengeDTO> challengetodolist = dao.challengeToDoList((String)session.getAttribute("auth"));
		
		MemberDAO mdao = new MemberDAO();
		MemberInfoDTO midto = mdao.getDdate((String)session.getAttribute("auth"));
		
		if (midto != null) {
		
			//문자열 > Date
			String ddate = midto.getDdate();
	
			SimpleDateFormat simpleDateFormat =  new SimpleDateFormat("yyyy-MM-dd kk:mm:ss");
			
			try {
				Date date = simpleDateFormat.parse(ddate);
				
				Calendar today = Calendar.getInstance();
				Calendar d_day = Calendar.getInstance();
				
				d_day.setTime(date);
				
				long l_today = today.getTimeInMillis()/(24*60*60*1000);
				long l_dday = d_day.getTimeInMillis()/(24*60*60*1000);
				
				int gap = (int)Math.floor(l_dday-l_today);
				
				if (gap > 0 ) {
					midto.setDdate(String.format("D - %s",gap));
				} else if (gap < 0) {
					midto.setDdate(String.format("D + %s",gap));
				} else {
					midto.setDdayname("Today is");
					midto.setDdate(String.format("%s",(int)Math.floor(l_today)));
				}
			
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			
			//2.
			req.setAttribute("todolist", todolist);
			req.setAttribute("midto", midto);
		
			RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/todolist/todolistmain.jsp");
			dispatcher.forward(req, resp);
		
		} else {
			
			resp.setCharacterEncoding("UTF-8");
			resp.setContentType("text/html; charset=UTF-8");
			
			PrintWriter writer = resp.getWriter();
			writer.print("<script>");
			writer.print("alert('로그인 후 사용하세요');");
			writer.print("location.href='/todo/member/login.do';");
			writer.print("</script>");
			writer.close();
		}
		
		

	}

}
