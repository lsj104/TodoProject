package com.test.todo.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.todo.DBUtil;

public class MinusRewardDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public MinusRewardDAO() {
		
		conn = DBUtil.open();
	}

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
