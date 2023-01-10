package com.test.todo.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.todo.DBUtil;

public class PlusRewardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public PlusRewardDAO() {
		conn = DBUtil.open();
	}

	public int getPoint(String auth, int pseq) {
	      
	      //pseq > plusPoint의 번호
	      
	      try {

	         String sql = "insert into tblPlusReward (seq, mseq, regdate, pseq) values (seqPlusReward.nextVal,(select seq from tblMember where email='" + auth + "'),TO_CHAR(SYSDATE, 'YYYYMMDD')," + pseq +")";
	         
	         stat = conn.createStatement();
	         
	         return stat.executeUpdate(sql);
	         
	      } catch (Exception e) {
	         System.out.println("PlusRewardDAO.getPoint");
	         e.printStackTrace();
	      }
	      
	      return 0;
	   
	   }

	   public int isLoginPoint(String auth, int pseq) {
	      
	      try {
	         
	         String sql = "select * from tblPlusReward where (mseq = (select seq from tblMember where email = '"+ auth +"')) and (pseq = " + pseq + ") and (regdate = TO_CHAR(SYSDATE, 'YYYYMMDD'))";
	         
	         stat = conn.createStatement();
	               
	         return stat.executeUpdate(sql);
	         
	         
	      } catch (Exception e) {
	         System.out.println("PlusRewardDAO.isLogin");
	         e.printStackTrace();
	      }
	      
	      return 0;
	   }
	   
	   public int registerPoint() {
	      
	      try {

	         String sql = "insert into tblPlusReward (seq, mseq, regdate, pseq) values (seqPlusReward.nextVal,(select max(seq) from tblMember),TO_CHAR(SYSDATE, 'YYYYMMDD'), 1)";
	         
	         stat = conn.createStatement();
	         
	         return stat.executeUpdate(sql);
	         
	      } catch (Exception e) {
	         System.out.println("PlusRewardDAO.getPoint");
	         e.printStackTrace();
	      }
	      
	      return 0;
	   
	   }

	public PlusRewardDTO plusPoint(String auth) {
		
		try {
			
			String sql = "select sum(plusPoint) as plusPoint from vwPlusReward where mseq = (select seq from tblMember where email='" + auth + "')";
			
			stat = conn.createStatement();
		
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
			
				PlusRewardDTO pdto = new PlusRewardDTO();
				
				pdto.setPlusPoint(rs.getInt("plusPoint"));
				
				return pdto;
				
			}
			
		} catch (Exception e) {
			System.out.println("PlusRewardDAO.plusPoint");
			e.printStackTrace();
		}

		return null;
	}

	public ArrayList<PlusRewardDTO> pluspointlist(String auth) {
		
		try {
			
			String sql = "select * from vwPlusReward where mseq = (select seq from tblMember where email=?) order by seq desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			ArrayList<PlusRewardDTO> pluslist = new ArrayList<PlusRewardDTO>();
					
			while (rs.next()) {
				
				PlusRewardDTO pdto = new PlusRewardDTO();
				
				pdto.setSeq(rs.getString("seq"));
				pdto.setMseq(rs.getString("mseq"));
				pdto.setRegdate(rs.getString("regdate"));
				pdto.setPlusName(rs.getString("plusName"));
				pdto.setPlusPoint(rs.getInt("plusPoint"));
				
				pluslist.add(pdto);
				
			}
			
			return pluslist;
			
		} catch (Exception e) {
			System.out.println("PlusRewardDAO.pluspointlist");
			e.printStackTrace();
		}
		
		return null;
	}

	public ArrayList<PlusRewardDTO> checkPeriod(String startDate, String endDate, String auth) {
		
		try {
			
			String sql = "select * from vwPlusReward where mseq = (select seq from tblMember where email=?) and regdate between ? and ? order by seq desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			pstat.setString(2, startDate);
			pstat.setString(3, endDate);
			
			rs = pstat.executeQuery();
			
			ArrayList<PlusRewardDTO> pluslist = new ArrayList<PlusRewardDTO>();
					
			while (rs.next()) {
				
				PlusRewardDTO pdto = new PlusRewardDTO();
				
				pdto.setSeq(rs.getString("seq"));
				pdto.setMseq(rs.getString("mseq"));
				pdto.setRegdate(rs.getString("regdate"));
				pdto.setPlusName(rs.getString("plusName"));
				pdto.setPlusPoint(rs.getInt("plusPoint"));
				
				pluslist.add(pdto);
				
			}
			
			return pluslist;

		} catch (Exception e) {
			System.out.println("PlusRewardDAO.checkPeriod");
			e.printStackTrace();
		}
		
		return null;
	}	
		
}
