package com.test.todo.item;

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

import com.test.todo.member.PlusRewardDAO;


@WebServlet("/item/itemlist.do")
public class ItemList extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//ItemList.java
		
		//1. DB 작업 > DAO 위임
		//2. 결과 + ?JSP 호출하기
		
		//페이징
		String page = req.getParameter("page");
		
		
		int nowPage = 0; 	//현재 페이지
		int startRow = 0;	//페이지 내의 시작 rnum
		int endRow = 0;		//페이지 내의 끝 rnum
		int pageSize = 8; 	//한 페이지 당 출력할 게시물 수

		int totalCount = 0; //전체 게시물 수
		int totalPage = 0;	//전체 페이지 수
		
		
		// 받아온 페이지 값이 없으면  nowPage=1
		if (page == null || page == "") nowPage = 1;
		else nowPage = Integer.parseInt(page);

		// startRow, endRow : 한 페이지의 start, end row 계산
		startRow = ((nowPage - 1) * pageSize) + 1;
		endRow = startRow + pageSize - 1;
		
		HashMap<String, String> map = new HashMap<String,String>();
		map.put("startRow",startRow + "");
		map.put("endRow",endRow + "");
		
		HttpSession session = req.getSession();
		
		map.put("email", (String)session.getAttribute("auth"));
		
		//1.
		ItemDAO dao = new ItemDAO();
		ArrayList<ItemDTO> list = dao.list(map);
		
		String pagebar = "";	//페이지 바 태그
		
		totalCount = dao.getTotalCount(map);
		totalPage = (int)Math.ceil((double)totalCount/pageSize);	// Math.ceil() : 올림
		
		int blockSize = 10; //하단 페이지 수, 한번에 보여지는 페이지 수
		int n = 0;			//출력될 페이지 번호
		int loop = 0;		//루프 변수
		
		loop = 1;
		n = ((nowPage -1)/ blockSize) * blockSize + 1;
		
		pagebar += "<table id='page-bar'>";
		pagebar += "<tr>";
		pagebar += "<td>";
		
		if (n == 1 ) { 
			pagebar += String.format("<a href='#!'style='cursor: not-allowed;'><i class=\"fa-solid fa-angles-left\"></i></a>");
		} else { 
			pagebar += String.format("<a href='/todo/itemlist.do?page=%d'><i class=\"fa-solid fa-angles-left\"></i></a>", n-1);
		}
		
		pagebar += "</td>";
		
		while (!(loop > blockSize || n > totalPage)) {
			
			pagebar += "<td>";
			
			if(nowPage == n) {
				
				pagebar += String.format(" <a href='#!'>%d</a> ", n);
			} else {
				pagebar += String.format(" <a href='/todo/item/itemlist.do?page=%d'>%d</a> ", n, n);
			}
			
			pagebar += "</td>";
			
			loop++;
			n++;
			
		}
		
		pagebar += "<td>";
		
		if (n > totalPage ) { 
			pagebar += String.format("<a href='#!'style='cursor: not-allowed;'><i class=\"fa-solid fa-angles-right\"></i></a>");
		} else {
			pagebar += String.format("<a href='/todo/item/itemlist.do?page=%d'><i class=\"fa-solid fa-angles-right\"></i></a>", n);
		}
		
		pagebar += "</td>";
		pagebar += "</tr>";
		pagebar += "</table>";
		
		
		//MinusReward
		MinusRewardDAO mdao = new MinusRewardDAO();
		int minusPoint = mdao.minusPoint((String)session.getAttribute("auth")).getMinusPoint();
		
		//PlusReward
		PlusRewardDAO pdao = new PlusRewardDAO();
		int plusPoint = pdao.plusPoint((String)session.getAttribute("auth")).getPlusPoint();
		
		int totalPoint =  plusPoint - minusPoint;
		
		
		System.out.println(totalPoint);
		
		//2.
		req.setAttribute("list", list);
		req.setAttribute("map", map);
		
		req.setAttribute("totalCount", totalCount);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("nowPage", nowPage);
		req.setAttribute("pagebar", pagebar);
		
		req.setAttribute("totalPoint", totalPoint);

		RequestDispatcher dispatcher = req.getRequestDispatcher("/WEB-INF/views/item/itemlist.jsp");
		dispatcher.forward(req, resp);

	}

}

