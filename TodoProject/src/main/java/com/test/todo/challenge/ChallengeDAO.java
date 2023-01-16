package com.test.todo.challenge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.test.todo.DBUtil;

/**
 * 
 * ChallengeDAO
 * 
 * @author 4조
 *
 */
public class ChallengeDAO {
	
	/**
	 * 
	 * 검색한 챌린지를 출력하는 메소드
	 * 
	 * @param searchText 검색어
	 * @return 검색한 챌린지 list
	 */
	public List<ChallengeDTO> searchChallengeList(String searchText) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<ChallengeDTO> challengeList = null;
		
		conn = DBUtil.open();
		
		String sql = "select * from tblChallenge where name like '%" + searchText +"%'";

		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);

	

			rs = pstmt.executeQuery();
			if(rs.next()) {
    	        challengeList = new ArrayList<>();
    	        do {
    	        	ChallengeDTO dto = new ChallengeDTO(rs.getInt("seq"), rs.getString("createDate"), rs.getString("duedatenumber"),
	    			rs.getString("membercnt"), rs.getInt("cseq"), rs.getString("name"), rs.getString("hseq"), rs.getString("mission"));
    	      
    	        	System.out.println("dto : " + dto.toString());
    	       
    	        	challengeList.add(dto);
    	        } while (rs.next());
		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return challengeList;
	}
	
	/**
	 * 
	 * 가입하지 않은 챌린지를 출력하는 메소드
	 * 
	 * @param mseq 회원번호
	 * @return 챌린지 list
	 */
	public List<ChallengeDTO> getAllChallengeList(int mseq) {
		Connection conn = null;
		PreparedStatement pstat = null;
		ResultSet rs = null;
		ArrayList<ChallengeDTO> challengeList = null;
		
		conn = DBUtil.open();
		
		try {
			
			String sql = "select * from tblChallenge WHERE seq NOT IN (select cseq from tblChallengeMember where mseq = ?)";
			
			pstat = conn.prepareStatement(sql);

			pstat.setInt(1, mseq);
			
			rs = pstat.executeQuery();
			if(rs.next()) {
    	        challengeList = new ArrayList<>();
    	        do {
    	        	ChallengeDTO dto = new ChallengeDTO(rs.getInt("seq"), rs.getString("createDate"), rs.getString("duedatenumber"),
	    			rs.getString("membercnt"), rs.getInt("cseq"), rs.getString("name"), rs.getString("hseq") , rs.getString("mission"));
    	       	    	   
    	        	System.out.println("dto : " + dto.toString());
    	       
    	        	challengeList.add(dto);
    	        } while (rs.next());
		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return challengeList;
	}
	
	/**
	 * 
	 * 가입한 챌린지를 출력하는 메소드
	 * 
	 * @param nickname 닉네임
	 * @param mseq 회원번호
	 * @return 가입한 챌린지 list
	 */
	public List<ChallengeDTO> getChallengeList(String nickname, String mseq) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<ChallengeDTO> challengeList = null;
		
		conn = DBUtil.open();
		
		String sql = "select * from tblChallenge where seq = (select cseq from tblChallengeMember where mseq= (select mseq from tblMemberInfo where nickname=?))";

		PreparedStatement pstmt = null;
		
		try {
			System.out.println("nickname : " + nickname);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, nickname);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
    	        
				challengeList = new ArrayList<>();
    	        	
    	        ChallengeDTO dto = new ChallengeDTO(rs.getInt("seq"), rs.getString("createDate"), rs.getString("duedatenumber"),
	    			rs.getString("membercnt"), rs.getInt("cseq"), rs.getString("name"), rs.getString("hseq"), rs.getString("mission"));
    	      
    	        	// System.out.println("dto : " + dto.toString());
    	       
    	        	challengeList.add(dto);
		
			} 
			return challengeList;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return challengeList;
	}
	
	/**
	 * 
	 * 카테고리 리스트 출력 메소드
	 * 
	 * 
	 * @return 카테고리 리스트
	 */
	public List<CategoryDTO> getCategoryList() { // 카테고리 리스트
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<CategoryDTO> categoryList = null;
		
		conn = DBUtil.open();
		
		String sql = "select * from tblCategory";

		PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
    	        categoryList = new ArrayList<>();
    	        do {
    	        	
    	        	CategoryDTO dto = new CategoryDTO(rs.getInt("seq"), rs.getString("name"));
    	        	 System.out.println("dto : " + dto.toString());
    	       
    	        	categoryList.add(dto);
    	        } while (rs.next());
		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return categoryList;
	}
	
	
	/**
	 * 
	 * 챌린지 추가 메소드 
	 * 
	 * @param mseq  회원번호
	 * @param name 챌린지 이름
	 * @param period 모집기간
	 * @param membercnt 가입가능한 회원수
	 * @param mission 공동미션
	 * @param category 카테고리
	 * 
	 * @return 실패시 null반환
	 */
	public List<ChallengeDTO> createChallenge(int mseq, String name, int period, int membercnt, String mission, int category) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		ArrayList<ChallengeDTO> challengeList = null;
		
		conn = DBUtil.open();
//		String sql = "insert into tblChallenge values (mseq, default, period, membercnt, , '정처기 챌린지', 1);  ";
	    String sql = "insert into tblChallenge (seq, createDate, duedatenumber, membercnt, cseq, name, hseq, mission) values (seqChallenge.nextVal, default, ?, ?, ?, ?, ?, ? )";		
	    
	    PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, period);
			pstmt.setInt(2, membercnt);
			pstmt.setInt(3, category);
			pstmt.setString(4, name);
			pstmt.setInt(5, mseq); 
			pstmt.setString(6, mission);
			pstmt.executeQuery();
		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return challengeList;
	}
	
	//추가
	/**
	 * 챌린지 가입 메소드
	 * 
	 * @param mseq 회원번호
	 * @param nickname 닉네임
	 */
	public void challengejoin(int mseq, String nickname) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		System.out.println("접근");
		conn = DBUtil.open();
//		String sql = "insert into tblChallenge values (mseq, default, period, membercnt, , '정처기 챌린지', 1);  ";
	    String sql = "insert into tblChallengeMember (seq,cseq,mseq,cnickname,ccnt) values (seqchallengemember.nextVal, (select max(seq) from tblChallenge), ?, ?, default)";		
	    
	    PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, mseq);
			pstmt.setString(2, nickname);
			pstmt.executeUpdate();
			System.out.println("접근2");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 
	 * 챌린지 가입 메소드
	 * 
	 * @param seq 챌린지 번호
	 * @param mseq 회원번호
	 * @param nickname 닉네임
	 */
	public void challengejoin2 (int seq, int mseq, String nickname) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		
		System.out.println("접근");
		conn = DBUtil.open();
//		String sql = "insert into tblChallenge values (mseq, default, period, membercnt, , '정처기 챌린지', 1);  ";
	    String sql = "insert into tblChallengeMember (seq, cseq, mseq, cnickname, ccnt) values (seqchallengemember.nextVal," + seq + "," + mseq + ",'" +  nickname + "', default)";		
	    
	    
	    System.out.println("seq : " + seq);
	    System.out.println("mseq : " + mseq);
	    System.out.println("nickname : " + nickname);
	    
	    PreparedStatement pstmt = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, seq);
			//pstmt.setInt(2, mseq);
			//pstmt.setString(3, nickname);
			pstmt.executeQuery();
			System.out.println("접근2");
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
} 
