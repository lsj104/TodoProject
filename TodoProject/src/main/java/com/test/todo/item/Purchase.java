package com.test.todo.item;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.todo.member.MemberDAO;
import com.test.todo.member.MemberDTO;

@WebServlet("/item/purchase.do")
public class Purchase extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//세션
		HttpSession session = req.getSession();
		
		//구매 아이템
		String itemSeq = req.getParameter("itemSeq");
		String messegeChange = req.getParameter("messegeChange");
		
		MinusRewardDAO mrdao = new MinusRewardDAO();
		int result = mrdao.minusRewaed((String)session.getAttribute("auth"), itemSeq);
		
		ItemDAO idao = new ItemDAO();
		int searchTheme = idao.searchTheme(itemSeq);
		int searchFont = idao.searchFont(itemSeq);
		int searchTodo = idao.searchTodo(itemSeq);
		int searchTimeTable = idao.searchTimeTable(itemSeq);
		int searchCal = idao.searchCal(itemSeq);
		int searchCir = idao.searchCir(itemSeq);
		int searchMessege = idao.searchMessege(itemSeq);
		
		MemberDAO mdao = new MemberDAO();
		MemberDTO mdto = new MemberDTO();
			
		if (searchTheme == 1 || searchFont == 1) {
			
			if (searchTheme == 1) {
				int theme = idao.themeUpdate((String)session.getAttribute("auth"), itemSeq);
			} else {
				int font = idao.fontUpdate((String)session.getAttribute("auth"), itemSeq);	
			}
			
			MemberDTO option = mdao.option(mdto,(String)session.getAttribute("auth"));
			
			System.out.println("option:" + option);
			
			session.setAttribute("theme", option.getTheme());
            session.setAttribute("fonttype", option.getFonttype());
			
		}
		
		if (searchTodo == 1 ) {
			session.setAttribute("subToDo", "y");
		}
		if (searchTimeTable  == 1 ) {
			session.setAttribute("timeTable", "y");
		}
		if (searchCal == 1 ) {
			session.setAttribute("timeCal", "y");
		}
		if (searchCir == 1 ) {
			session.setAttribute("timeCir", "y");
		}
		if (searchMessege == 1 ) {
			mdao.messegeChange((String)session.getAttribute("auth"),messegeChange);
		}
		
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();

		
	}

}
