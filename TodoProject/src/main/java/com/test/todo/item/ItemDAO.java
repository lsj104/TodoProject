package com.test.todo.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.todo.DBUtil;

public class ItemDAO {
	
	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;
	
	public ItemDAO() {
		conn = DBUtil.open();
	}

	public ArrayList<ItemDTO> list(HashMap<String, String> map) {

		try {
			
			String sql = String.format("select a.*, (select count(*) from tblmember m inner join tblminusreward mr on m.seq = mr.mseq where m.email = '%s' and mr.iseq = a.seq) as isown from (select rownum as rnum, i.* from tblItem i) a where rnum between %s and %s", map.get("email"),  map.get("startRow"), map.get("endRow"));
			
			
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();
			
			while(rs.next()) {
				
				ItemDTO dto = new ItemDTO() ;
				
				dto.setSeq(rs.getString("seq"));
				dto.setItemName(rs.getString("itemName"));
				dto.setItemCost(rs.getString("itemCost"));
				dto.setImage(rs.getString("image"));
				
				dto.setIsown(rs.getInt("isown"));
				
				list.add(dto);
				
			}
			
			
			return list;
			
		} catch (Exception e) {
			System.out.println("ItemDAO.list");
			e.printStackTrace();
		}
		
		return null;
	}

	public int getTotalCount(HashMap<String, String> map) {
		
		try {
			
			String sql = "select count(*) as cnt from tblItem";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			if (rs.next()) {
				return rs.getInt("cnt");
			}
			
			
		} catch (Exception e) {
			System.out.println("ItemDAO.getTotalCount");
			e.printStackTrace();
		}
		
		return 0;
	}
	
public int searchTheme(String itemSeq) {
		
		try {
			
			String sql = String.format("select count(itemname) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "테마");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
			
		} catch (Exception e) {
			System.out.println("ItemDAO.searchTheme");
			e.printStackTrace();
		}
		
		
		return 0;
	}

	public int searchFont(String itemSeq) {
		
		try {
			
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "폰트");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
		} catch (Exception e) {
			System.out.println("ItemDAO.searchFont");
			e.printStackTrace();
		}
		
		return 0;
	}
	
	public int themeUpdate(String auth, String itemSeq) {
		
		try {
			
			String sql = "update tblOption set theme = (select itemname from tblItem where seq = ? ) where seq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			pstat.setString(2, auth);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ItemDAO.optionUpdate");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int fontUpdate(String auth, String itemSeq) {

		try {
			
			String sql = "update tblOption set fonttype = (select itemname from tblItem where seq = ? ) where seq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			pstat.setString(2, auth);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("ItemDAO.fontUpdate");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int searchTodo(String itemSeq) {

		try {
			
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "투두");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}		
					
		} catch (Exception e) {
			System.out.println("ItemDAO.serchTodo");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int searchTimeTable(String itemSeq) {
		
		try {
			
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "타임");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
			
		} catch (Exception e) {
			System.out.println("ItemDAO.serchTimeblock");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int searchCal(String itemSeq) {
		
		try {
		
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "캘린더");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
			
		} catch (Exception e) {
			System.out.println("ItemDAO.searchCal");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int searchCir(String itemSeq) {
		
		try {
			
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "표");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
			
			
		} catch (Exception e) {
			System.out.println("ItemDAO.searchCir");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int searchMessege(String itemSeq) {

		try {
			
			String sql = String.format("select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'", "응원");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, itemSeq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				int cnt = Integer.parseInt(rs.getString("cnt"));
				return cnt;
			}
			
		} catch (Exception e) {
			System.out.println("ItemDAO.searchMessege");
			e.printStackTrace();
		}
		
		
		return 0;
	}
	

}
