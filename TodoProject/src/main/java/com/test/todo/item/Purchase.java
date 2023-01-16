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
/**
 * 
 * 아이템 구매 클래스
 * 상점에서 구매한 아이템을 구매 내역에 추가하고 사용자의 옵션을 변경한다. 
 * 
 * @author 4조
 *
 */
@WebServlet("/item/purchase.do")
public class Purchase extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


		//세션
		HttpSession session = req.getSession();
		
		//구매 아이템
		String itemSeq = req.getParameter("itemSeq");
		
		MinusRewardDAO mrdao = new MinusRewardDAO();
		int result = mrdao.minusRewaed((String)session.getAttribute("auth"), itemSeq);
		
		ItemDAO idao = new ItemDAO();
		int searchTheme = idao.searchTheme(itemSeq);
		int searchFont = idao.searchFont(itemSeq);
		int searchTodo = idao.searchTodo(itemSeq);
		int searchTimeTable = idao.searchTimeTable(itemSeq);
		int searchCal = idao.searchCal(itemSeq);
		int searchCir = idao.searchCir(itemSeq);
		
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
			MemberDTO  subToDo = mdao.isSubToDo((String)session.getAttribute("auth"));
			session.setAttribute("subToDo", subToDo.getSubToDo());
		}
		if (searchTimeTable  == 1 ) {
			MemberDTO timeTable = mdao.isTimeTable((String)session.getAttribute("auth"));
			session.setAttribute("timeTable",timeTable.getTimeTable());
		}
		if (searchCal == 1 ) {
			MemberDTO timeCal = mdao.isTimeCal((String)session.getAttribute("auth"));
			session.setAttribute("timeCal", "y");
		}
		if (searchCir == 1 ) {
			MemberDTO timeCir = mdao.isTimeCir((String)session.getAttribute("auth"));
			session.setAttribute("timeCir", "y");
		}
		
		PrintWriter writer = resp.getWriter();		
		writer.print(result);
		writer.close();

		
	}

}
