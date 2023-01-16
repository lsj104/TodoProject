package com.test.todo.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.todo.DBUtil;

/**
 * MinusRewardDAO 클래스
 * 
 * @author 4조
 *
 */
public class MinusRewardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	

	/**
	 * 
	 * DB연결
	 * 
	 */
	public MinusRewardDAO() {
		
		conn = DBUtil.open();
	}

	/**
	 * 
	 * 아이템 구매 내역 추가 메소드
	 * 
	 * @param auth 로그인 계정
	 * @param itemSeq 아이템 번호
	 * @return 구매내역 추가 성공: 1, 실패: 0 반환
	 */
	public int minusRewaed(String auth, String itemSeq) {
		
		try {
			
			String sql = "insert into tblMinusReward (seq, mseq, regdate, iseq) values (seqMinusReward.nextVal, (select seq from tblMember where email='" + auth + "'),sysdate," + itemSeq +")";
			
			stat = conn.createStatement();
			
			//System.out.println(stat.executeUpdate(sql));
			return stat.executeUpdate(sql);
			
		} catch (Exception e) {
			System.out.println("MinusRewardDAO.minusRewaed");
			e.printStackTrace();
		}
		
		return 0;
	}

	/**
	 * 총 차감 포인트 메소드
	 * 
	 * @param auth 로그인 계정
	 * @return 차감 포인트를 가지고 있는 객체 반환
	 */
	public MinusRewardDTO minusPoint(String auth) {
		
		try {
			
			String sql = "select sum(minusPoint) as minusPoint  from vwMinusReward where mseq = (select seq from tblMember where email='" + auth + "')";
			
			stat = conn.createStatement();
		
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
			
				MinusRewardDTO mdto = new MinusRewardDTO();
				
				mdto.setMinusPoint(rs.getInt("minusPoint"));
				
				return mdto;

			}
			
		} catch (Exception e) {
			System.out.println("MinusRewardDAO.minusReward");
			e.printStackTrace();
		}
		
		return null;
	}

}
