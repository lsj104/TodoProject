package com.test.todo.member;

import com.test.todo.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * MemberDAO 클래스
 * @author 4조
 */
public class MemberDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
	 * 
	 * DB연결
	 * 
	 */
    public MemberDAO() {
        conn = DBUtil.open();
    }

    /**
     * 로그인 메소드
     * 사용자가 입력한 아이디, 비밀번호에 맞는 개인정보를 MemberDTO 객체에 담아 반환합니다.
     * 
     * @param dto MemberDTO Member 데이터 객체
     * 
     * @return 로그인한 멤버의 데이터 객체를 반환합니다.
     */
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
    
    /**
     * 카테고리 list 메소드
     * 
     * @return 모든 카테고리를 조회한 list를 반환합니다.
     * 
     */
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

	/**
     * 카테고리 수정 메소드
     * 회원이 선택한 카테고리 이름과 회원번호를 받아 수정하고 결과값(0 or 1)을 반환합니다.
     * 
     * @param value	카테고리 이름
	 * @param seq 	회원번호
	 * 
	 * @return 수정 성공: 1, 실패: 0 반환
	 * 
     */
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
	
	 /**
     * 비밀번호 수정 메소드
     * 회원번호, 새 비밀번호를 입력받아 수정하고 결과값(0 or 1)을 반환합니다.
	 * 
	 * @param seq 회원번호
	 * @param inputConfirmPassword 새 비밀번호
	 * 
	 * @return 수정 성공: 1, 실패: 0 반환
     */
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

	/**
     * 닉네임 수정 메소드
     * 회원번호, 닉네임을 입력받아 수정하고 결과값(0 or 1)을 반환합니다.
	 * 
	 * @param seq			회원번호
	 * @param inputUsername	닉네임
	 * 
	 * @return 수정 성공: 1, 실패: 0 반환 
     * 
     */
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

	/**
     * 회원 탈퇴 메소드
     * 회원번호를 입력받아 회원탈퇴를 진행하고 결과값(0 or 1)을 반환합니다.
	 * 
	 * @param seq 회원번호
	 * 
	 * @return 성공: 1, 실패: 0 반환
     */
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

	 /**
     * 
     * 포인트 조회 메소드
     * 회원번호를 넘겨받아 포인트 내역을 조회해 반환합니다.
	 * 
	 * @param seq 회원번호
	 * 
	 * @return 포인트 내역 리스트
	 * 
     */
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

	
	/**
     *
     * 포인트 기간 조회 메소드
     * 시작날짜, 끝날짜, 회원번호를 받아 포인트 내역을 조회해 반환합니다.
	 * 
	 * @param startDate	시작날짜
	 * @param endDate	끝날짜
	 * @param seq		회원번호
	 * 
	 * 
	 * @return 사용자가 설정한 기간 내의 포인트 내역 list
	 * 
     */
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
	
	/**
	 * 
	 * 이메일 중복 여부를 검사하는 메소드
	 * 
	 * @param email 이메일
	 * 
	 * @return 가입 계정: 1 , 미가입 계정: 0
	 */
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

	/**
	 * 닉네임 중복 여부를 검사하는 메소드
	 * 
	 * @param nickname 닉네임
	 * 
	 * @return 중복: 1 중복X: 0
	 */
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

	 /**
     * 입력된 회원 정보(email, pw, active)를 등록하는 메소드
     * 
     * @param mdto 회원 정보 객체
     * 
     * @return 등록 성공: 1, 실패: 0
     */
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

	/**
	 * 입력된 회원 상세 정보(nickname, Cseq, ddayname, ddate, atablenaming, btablenaming, chalcnt, image, message, point)를 등록하는 메소드
	 * 
	 * @param midto 회원정보 객체
	 * 
	 * @return 등록 성공: 1, 실패: 0
     */
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



	/**
	 * 비밀번호 찾기 시 임시비밀번호로 사용자의 비밀번호를 수정하는 메소드 
	 * 
	 * @param dto 회원 객체
	 * 
	 * @return 수정 성공: 1, 실패: 0
	 */
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

	/**
     * 누적 포인트 상위 10명의 사용자 정보를 가져오는 메소드
     * 모든 회원의 획득 포인트를 합산하여 1위부터 10위까지 출력한다. 
	 * 
	 * @return 획득포인트 합산 결과 1위부터 10위까지의 회원 리스트
	 */	
	public ArrayList<MemberInfoDTO> pointRank() {

		try {
			
			String sql = "select image, nickname, (select sum(pluspoint)as totalpluspoint from vwPlusReward where mseq = m.mseq group by mseq) as point from tblMemberInfo m where rownum <= 10 order by point desc";
			
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

	/**
     * 
     * 프로필 이미지 변경 메소드
     * 회원번호, 파일명을 받아 수정한 뒤 결과값(0 or 1)을 반환합니다.
     * 
     * @param seq 회원번호
	 * @param filename 파일명
	 * 
	 * @return 수정 성공: 1, 실패 0 
     */
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

	/**
     * 닉네임 중복 검사 메소드
     * 수정할 닉네임, 회원번호를 받아 중복검사를 하고 결과값을 반환합니다.
	 * 
	 * @param inputUsername	닉네임
	 * @param seq			회원번호
     * 
     * @return 중복: 1, 중복X: 0
     * 
     */
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
	
	/**
     * 사용자의 옵션 구매 여부를 가져오는 메소드
	 * 
	 * @param dto 회원 객체
	 * @param email 회원 계정
	 * 
	 * @return 옵션 객체
     */
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


	
	/**
	 * 
	 * 회원가입시, 회원의 기본옵션을 등록하는 메소드
	 * 
	 */
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

	/**
	 * 
	 * 응원 메세지를 변경하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @param messageChange 변경 응원메세지
	 * @return 수정 성공:1, 실패:0
	 */
	public int messageChange(String auth, String messageChange) {
		
		try {
			
			String sql = "update tblMemberInfo set message = ? where mseq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, messageChange);
			pstat.setString(2, auth);
			
			return pstat.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("MemberDAO.messageChange");
			e.printStackTrace();
		}
		
		return 0;
		
	}
	
	/**
	 * 
	 * 구매 내역에 투두리스트 추가권 여부를 확인하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 존재: 1, 존재: 0
	 */
	public MemberDTO isSubToDo(String auth) {
		
		try {
			
			String sql = String.format("select count(*) as cnt from vwMinusReward where mseq = (select seq from tblMember where email = ?) and itemname like '%%' || '%s' ||'%%'", "투두리스트 추가권");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO dto = new MemberDTO();

				dto.setSubToDo(rs.getString("cnt"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.isSubToDo");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * 구매 내역에 타임테이블 추가권 여부를 확인하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 존재: 1, 존재: 0
	 */
	
	public MemberDTO isTimeTable(String auth) {
		
		try {
			
			String sql = String.format("select count(*) as cnt from vwMinusReward where mseq = (select seq from tblMember where email = ?) and itemname like '%%' || '%s' ||'%%'", "타임테이블 추가권");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setTimeTable(rs.getString("cnt"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.isTimeTable");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * 구매 내역에 캘린더 추가권 여부를 확인하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 존재: 1, 존재: 0
	 */
	public MemberDTO isTimeCal(String auth) {
		
		try {
			
			String sql = String.format("select count(*) as cnt from vwMinusReward where mseq = (select seq from tblMember where email = ?) and itemname like '%%' || '%s' ||'%%'", "캘린더 추가권");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setTimeCal(rs.getString("cnt"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.isTimeCal");
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 
	 * 구매 내역에 24시간 계획표 추가권 여부를 확인하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 존재: 1, 존재: 0
	 */
	public MemberDTO isTimeCir(String auth) {
		
		try {
			
			String sql = String.format("select count(*) as cnt from vwMinusReward where mseq = (select seq from tblMember where email = ?) and itemname like '%%' || '%s' ||'%%'", "24시간 계획표 추가권");
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				MemberDTO dto = new MemberDTO();
				
				dto.setTimeCir(rs.getString("cnt"));
				
				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.isTimeCir");
			e.printStackTrace();
		}
		
		return null;
	}

	/**
	 * 
	 * 회원이 설정한 디데이를 출력하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 회원 정보 객체
	 */
	public MemberInfoDTO getDdate(String auth) {
		
		try {
			
			String sql = "select message, ddayname, ddate from tblMemberInfo where mseq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			String ddate = "";
			
			if (rs.next()) {
				
				MemberInfoDTO dto = new MemberInfoDTO();
				
				dto.setMessage(rs.getString("message"));
				dto.setDdayname(rs.getString("ddayname"));
				dto.setDdate(rs.getString("ddate"));

				return dto;
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.getDDate");
			e.printStackTrace();
		}
		
		return null;
	}

	
	/**
	 * 
	 * 디데이 수정 메소드
	 * 
	 * @param auth 회원 계정
	 * @param ddayname 디데이 이름
	 * @param ddate 디데이 날짜
	 * 
	 * @return 수정 성공: 1, 실패: 0
	 */
	public int ddayEdit(String auth, String ddayname, String ddate) {
		
		try {
			
			String sql = "update tblMemberInfo set ddayname = ?, ddate = ? where mseq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, ddayname);
			pstat.setString(2, ddate);
			pstat.setString(3, auth);
			
			return pstat.executeUpdate(); 
			
		} catch (Exception e) {
			System.out.println("MemberDAO.ddayEdit");
			e.printStackTrace();
		}
		
			
		
		return 0;
	}

	/**
	 * 
	 * 응원 메세지를 출력하는 메소드
	 * 
	 * @param auth 회원 객체
	 * @return 응원메세지
	 */
	public String getMessage(String auth) {
		
		try {
			
			String sql = "select message from tblMemberInfo where mseq = (select seq from tblMember where email = ?)";
			
			pstat = conn.prepareStatement(sql);
			
			pstat.setString(1, auth);
			
			rs = pstat.executeQuery();
			
			if(rs.next()) {
				return rs.getString("message");
			}
			
		} catch (Exception e) {
			System.out.println("MemberDAO.getMessage");
			e.printStackTrace();
		}
		
		return null;
	}

	

}

