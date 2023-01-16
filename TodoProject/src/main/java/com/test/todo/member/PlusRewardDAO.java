package com.test.todo.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.test.todo.DBUtil;

/**
 * 
 * PlusRewardDAO
 * 
 * @author 4조
 *
 */
public class PlusRewardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * 
	 * DB연결
	 * 
	 */
	public PlusRewardDAO() {
		conn = DBUtil.open();
	}

	/**
	 * 
	 * 획득한 포인트 내역을 추가하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @param pseq 획득 포인트 번호
	 * @return 추가 성공: 1, 실패: 0
	 */
	public int getPoint(String auth, int pseq) {

		// pseq > plusPoint의 번호

		try {

			String sql = "insert into tblPlusReward (seq, mseq, regdate, pseq) values (seqPlusReward.nextVal,(select seq from tblMember where email='"
					+ auth + "'),TO_CHAR(SYSDATE, 'YYYYMMDD')," + pseq + ")";

			stat = conn.createStatement();

			return stat.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println("PlusRewardDAO.getPoint");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * 
	 * 당일 로그인 포인트를 획득했는지 확인하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @param pseq 획득 포인트 번호
	 * @return 획득: 1, 미획득: 0
	 */
	public int isLoginPoint(String auth, int pseq) {

		try {

			String sql = "select * from tblPlusReward where (mseq = (select seq from tblMember where email = '" + auth
					+ "')) and (pseq = " + pseq + ") and (regdate = TO_CHAR(SYSDATE, 'YYYYMMDD'))";

			stat = conn.createStatement();

			return stat.executeUpdate(sql);

		} catch (Exception e) {
			System.out.println("PlusRewardDAO.isLogin");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 
	 * 회원가입시, 획득한 포인트 내역 추가 메소드
	 * 
	 * @return 추가 성공: 1, 실패: 0
	 */
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

	/**
	 * 
	 * 총 획득 포인트를 출력하는 메소드
	 * 
	 * @param auth 회원 계정
	 * @return 포인트 내역 객체
	 */
	public PlusRewardDTO plusPoint(String auth) {

		try {

			String sql = "select sum(plusPoint) as plusPoint from vwPlusReward where mseq = (select seq from tblMember where email='"
					+ auth + "')";

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

	/**
	 * 
	 * 획득 포인트 내역을 출력하는 메소드
	 * 
	 * @param auth 계정
	 * @return 획득 포인트 list
	 */
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

	
	/**
	 * 
	 * 회원이 설정한 기간 내의 획득 포인트를 출력하는 메소드
	 * 
	 * @param startDate 조회 시작일
	 * @param endDate 조회 종료일
	 * @param auth 회원 계정
	 * @return 획득 포인트 list
	 */
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

	/**
	 * 
	 * 총 획득 포인트를 회원의 정보에 업데이트하는 메소드 
	 * 
	 * @param auth 회원계정
	 */
	public void updatePoint(String auth) {

		try {

			String sql = "update tblMemberInfo set point = (select sum(pluspoint) from vwPlusReward where mseq = (select seq from tblMember where email=?)) where mseq = (select seq from tblMember where email=?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, auth);
			pstat.setString(2, auth);

		} catch (Exception e) {
			System.out.println("PlusRewardDAO.updatePoint");
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * 
	 * 회원가입시 획득한 포인트를 회원 정보에 업데이트 하는 메소드
	 * 
	 */
	public void updatePoint() {

		try {

			String sql = "update tblMemberInfo set point = (select sum(pluspoint) from vwPlusReward where mseq = (select max(seq) from tblMember)) where mseq = (select max(seq) from tblMember)";

			stat = conn.createStatement();

			stat.execute(sql);

		} catch (Exception e) {
			System.out.println("PlusRewardDAO.updatePoint");
			e.printStackTrace();
		}

	}

}
