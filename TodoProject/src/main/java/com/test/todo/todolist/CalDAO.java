package com.test.todo.todolist;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.todo.DBUtil;

/**
 * 
 * CalDAO
 * 
 * @author 4조
 *
 */
public class CalDAO {

	/**
	 * 
	 * 일정을 추가하는 메소드
	 * 
	 * @param content 내용
	 * @param start_date 시작일
	 * @param end_date 종료일
	 * @param mseq 회원번호
	 */
	public void setCalList(String content, String start_date, String end_date, int mseq) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		conn = DBUtil.open();
		
		// System.out.println(content + start_date + end_date);
		
		String sql = "insert into tblCalendar values (seqCal.nextVal, ?,?,?,?)"; // seq, content, start, end, meseq

		PreparedStatement pstmt = null;
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, content);
			pstmt.setString(2, start_date);
			pstmt.setString(3, end_date);
			pstmt.setInt(4, mseq);
			pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 * 일정 삭제 메소드
	 * 
	 * @param seq 일정 번호
	 */
	public void delCalList(int seq) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		conn = DBUtil.open();
	
		// System.out.println(content + start_date + end_date);
		
		String sql = "delete from tblCalendar where seq=? "; // seq, content, start, end, meseq

		PreparedStatement pstmt = null;
		
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * 일정을 출력하는 메소드
	 * 
	 * @param mseq 회원번호
	 * @return 일정 list
	 */
	public List<CalDTO> getCalList(String mseq) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<CalDTO> calList = null;
		
		conn = DBUtil.open();
		System.out.println("getCalList 접근");
		String sql = "select * from tblCalendar where mseq=?";

		PreparedStatement pstmt = null;
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(mseq));

			rs = pstmt.executeQuery();
			if(rs.next()) {
    	        calList = new ArrayList<>();
    	        do {
    	        	
    	        
    	        	CalDTO dto = new CalDTO(rs.getInt("seq"), rs.getString("content"), rs.getString("startdate"), rs.getString("endd"),
    	        			rs.getInt("mseq"));
    	        	// System.out.println("dto : " + dto.toString());
    	       
    	        	calList.add(dto);
    	        } while (rs.next());
		
			}
			
		}catch(Exception e) {
			
			e.printStackTrace();
		}
		return calList;
	}
}
