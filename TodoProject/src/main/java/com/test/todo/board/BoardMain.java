package com.test.todo.board;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 
 * 게시판에서 모든 게시글 출력 및 검색어에 따른 게시글을 출력하는 클래스
 * 
 * @author 4조
 *
 */
@WebServlet("/board/boardmain.do")
public class BoardMain extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		
		session.setAttribute("readcount", "n");
		
		String column = req.getParameter("column");
		String word = req.getParameter("word");
		String isSearch = "n";
		
		if((column == null || word == null) || (column == "" || word == "")) {
			isSearch = "n";
		} else {
			isSearch = "y";
		}
		
		HashMap<String, String> map = new HashMap<String, String>();
		
		map.put("column", column);
		map.put("word", word);  
		map.put("isSearch", isSearch);
		
		BoardDAO dao = new BoardDAO();
		
		ArrayList<BoardDTO> list = dao.list(map);
		
		for(BoardDTO dto : list) {
			String regdate = dto.getQdate();
			regdate = regdate.substring(0, 10);
			dto.setQdate(regdate);
			
			String title = dto.getTitle();
			
			
			if (isSearch.equals("y") && column.equals("title")) {
		           
		          
		          title = title.replace(word, "<span style=\"background-color: #F9DFFF;\">" + word + "</span>");
		           
		          dto.setTitle(title);
		           
		        }
				
		}
		
		
      System.out.println(list.toString());
		req.setAttribute("list", list);
		
		req.setAttribute("map", map);
		

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/board/boardmain.jsp");
		dispatcher.forward(req, resp);

	}

}
