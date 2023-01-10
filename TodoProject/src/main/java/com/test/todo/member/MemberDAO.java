package com.test.todo.member;

import com.test.todo.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class MemberDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public MemberDAO() {
        conn = DBUtil.open();
    }

    public MemberDTO login(MemberDTO dto) {

        try {
        	
            String sql = "select m.seq as seq, email, pw, active, nickname, message, name, image from tblMember m inner join tblMemberinfo i on m.seq = i.mseq inner join tblCategory c on c.seq = i.cseq where email=? and pw=? and active='y'";
            
            pstat = conn.prepareStatement(sql);

            pstat.setString(1, dto.getEmail());
            pstat.setString(2, dto.getPw());

            rs = pstat.executeQuery();

            if (rs.next()) {

                MemberDTO result = new MemberDTO();
                
                dto.setSeq(rs.getString("seq"));
                dto.setNickname(rs.getString("nickname"));
                dto.setMessage(rs.getString("message"));
                dto.setEmail(rs.getString("email"));
                dto.setActive(rs.getString("active"));
				dto.setCategory(rs.getString("name")); 
				dto.setImage(rs.getString("image"));
				
				System.out.println(dto);

                return dto;
            }
            
        } catch (Exception e) {
            System.out.println("MemberDAO.login");
            e.printStackTrace();
        }

        return null;
    }

	public ArrayList<MemberDTO> list() {
		
		try {

			String sql = "select name from tblCategory order by seq";
			
			pstat = conn.prepareStatement(sql);
			
			rs = pstat.executeQuery();
			
			ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();
			
			while(rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setCategory(rs.getString("name"));
				
				list.add(dto);
				
			}
			
			return list;
			
					
		} catch (Exception e) {
			System.out.println("MemberDAO.list");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public int editCategory(String value, String seq) {
		
		try {
			
			String sql = "update tblMemberInfo set cseq = (select seq from tblCategory where name = ?) where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, value);
			pstat.setString(2, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.editCategory");
			e.printStackTrace();
		}
		
		return 0;
		
	}

	public int editPw(String seq, String inputConfirmPassword) {
		
		try {
			
			String sql = "update tblMember set pw = ? where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, inputConfirmPassword);
			pstat.setString(2, seq);
			
			return pstat.executeUpdate();
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.editPw");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int editNickname(String seq, String inputUsername) {
		
		try {
			
			String sql = "update tblMemberInfo set nickname = ? where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, inputUsername);
			pstat.setString(2, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.editNickname");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int unRegisterMember(String seq) {
		
		try {
			
			String sql = "update tblMember set pw ='0000', active = 'n' where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.unRegisterMember");
			e.printStackTrace();
		}
		
		return 0;
	}

	public ArrayList<PointDTO> pointlist(String seq) {
		
		try {
			
			String sql = "select m.seq as mrseq, mseq, regdate, iseq, itemname, itemcost, image from tblMinusReward m inner join tblItem i on  m.iseq = i.seq where mseq = ? order by mrseq desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			ArrayList<PointDTO> pointList = new ArrayList<PointDTO>();
			
			while(rs.next()) {
				
				PointDTO dto = new PointDTO();
				
				dto.setSeq(rs.getString("mrseq"));
                dto.setMseq(rs.getString("mseq"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setIseq(rs.getString("iseq"));
                dto.setItemname(rs.getString("itemname"));
				dto.setItemcost(rs.getString("itemcost")); 
				dto.setImage(rs.getString("image")); 
				
				pointList.add(dto);
				
			}
			
			return pointList;
			
		} catch (Exception e) {
			System.out.println("MemberDAO.pointlist");
			e.printStackTrace();
		}
		
		
		return null;
	}

	public ArrayList<PointDTO> checkPeriod(String startDate, String endDate, String seq) {
		
		try {
			
			String sql = "select m.seq as mrseq, mseq, regdate, iseq, itemname, itemcost, image from tblMinusReward m inner join tblItem i on  m.iseq = i.seq where mseq = ? and regdate between ? and ? order by mrseq desc";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, seq);
			pstat.setString(2, startDate);
			pstat.setString(3, endDate);
			
			rs = pstat.executeQuery();
			
			ArrayList<PointDTO> list = new ArrayList<PointDTO>();
			
			while(rs.next()) {
				
				PointDTO dto = new PointDTO();
				
				dto.setSeq(rs.getString("mrseq"));
                dto.setMseq(rs.getString("mseq"));
                dto.setRegdate(rs.getString("regdate"));
                dto.setIseq(rs.getString("iseq"));
                dto.setItemname(rs.getString("itemname"));
				dto.setItemcost(rs.getString("itemcost")); 
				dto.setImage(rs.getString("image")); 
				
				list.add(dto);
				
			}
			
			System.out.println(list);
			
			return list;
			
		} catch (Exception e) {
			System.out.println("MemberDAO.checkPeriod");
			e.printStackTrace();
		}
		
		
		return null;
	}
	
	public int checkemail(String email) {

		try {
			
			String sql = "select count(*) as cnt from tblMember where email = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, email);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt"); // 1 or 0
			}
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.checkemail");
			e.printStackTrace();
		}

		return 0;
	}

	public int checknick(String nickname) {

		try {
			
			String sql = "select count(*) as cnt from tblMemberInfo where nickname = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, nickname);

			rs = pstat.executeQuery();

			if (rs.next()) {

				return rs.getInt("cnt"); // 1 or 0
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.checknick");
			e.printStackTrace();
		}
		
		return 0;
	}


	public int registerMember(MemberDTO mdto) {

		try {
			
			String sql = "insert into tblMember(seq, email, pw, active) values (seqMember.nextVal, ?, ?, default)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, mdto.getEmail());
			pstat.setString(2, mdto.getPw());

			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.registerMember");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int registerMemberInfo(MemberInfoDTO midto) {

		try {
			
			String sql = "insert into tblMemberInfo(Mseq, nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point) values ((select max(seq) from tblMember), ?, (select seq from tblCategory where name=?), ?, ?, ?, ?, default, ?, default, default)";

			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, midto.getNickname());
			pstat.setString(2, midto.getCategory());
			pstat.setString(3, midto.getDdayname());
			pstat.setString(4, midto.getDdate());
			pstat.setString(5, "");
			pstat.setString(6, "");
			pstat.setString(7, midto.getImage());

			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.registerMemberInfo");
			e.printStackTrace();
		}
		
		return 0;
	}




	public int findPw(MemberDTO dto) {

		try {
			
			String sql = "update tblMember set pw = ? where email = ?";
			
			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getPw());
			pstat.setString(2, dto.getEmail());

			rs = pstat.executeQuery();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.findPw");
			e.printStackTrace();
		}
		
		return 0;
	}


	public ArrayList<MemberInfoDTO> pointRank() {

		try {
			
			String sql = "select image, nickname, point from tblMemberInfo  where rownum <= 10 order by point desc";
			
			stat = conn.createStatement();
			
			rs = stat.executeQuery(sql);
			
			ArrayList<MemberInfoDTO> list = new ArrayList<MemberInfoDTO>();
			
			while (rs.next()) {
				
				MemberInfoDTO dto = new MemberInfoDTO();
				
				dto.setImage(rs.getString("image"));
				dto.setNickname(rs.getString("nickname"));
				dto.setPoint(rs.getString("point"));
				
				list.add(dto);
			}
			
			return list;
			
		} catch (Exception e) {
			System.out.println("MemberDAO.pointRank");
			e.printStackTrace();
		}
		
		return null;
	}

	
	public int editImage(String seq, String filename) {
		
		try {
			
			String sql = "update tblMemberInfo set image = ? where mseq = ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, filename);
			pstat.setString(2, seq);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.editImage");
			e.printStackTrace();
		}
		
		return 0;
	}

	public int isDuplicate(String inputUsername, String seq) {
		
		try {
			
			String sql = "select count(nickname) as cnt from tblMemberInfo where nickname = ? and mseq != ?";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, inputUsername);
			pstat.setString(2, seq);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				
				return rs.getInt("cnt"); // 1 or 0
				
			}
			
			
			
		} catch (Exception e) {
			System.out.println("MemberDAO.isDuplicate");
			e.printStackTrace();
		}
		
		return 0;

		
		
	}
	
	public MemberDTO option(MemberDTO dto, String email) {

	      try {
	         
	         String sql = "select * from tblOption where seq = (select seq from tblMember where email = ?)";
	         
	         pstat = conn.prepareStatement(sql);

	         pstat.setString(1, email);

	         rs = pstat.executeQuery();

	         if(rs.next()) {

	            MemberDTO option = new MemberDTO();
	            
	            dto.setTheme(rs.getString("theme"));
	            dto.setFonttype(rs.getString("fonttype"));
	         
	         }
	         return dto;
	         
	         
	      } catch (Exception e) {
	         System.out.println("MemberDAO.theme");
	         e.printStackTrace();
	      }
	      
	      return null;
	   }


	public void optionCreate() {
		
		try {
			
			String sql = "insert into tblOption (seq, fonttype, theme) values ((select max(seq) from tblMember), default, default)";
			
			stat = conn.createStatement();
			
			stat.execute(sql);
			
		} catch (Exception e) {
			System.out.println("MemberDAO.optionCreate");
			e.printStackTrace();
		}
		
	}

	public void messegeChange(String auth, String messegeChange) {
		
		try {
			
			String sql = "update tblMemberInfo set messege = ? where seq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, messegeChange);
			pstat.setString(2, auth);
			
		} catch (Exception e) {
			System.out.println("MemberDAO.messegeChange");
			e.printStackTrace();
		}
		
	}

	

}











































