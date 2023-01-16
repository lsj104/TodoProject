package com.test.todo.item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.todo.DBUtil;

/**
 * ItemDAO 클래스
 * 
 * @author 4조
 *
 */

public class ItemDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * 
	 * DB연결
	 * 
	 */
	public ItemDAO() {
		conn = DBUtil.open();
	}

	/**
	 * Item list와 보유 여부를 확인 메소드
	 * 
	 * @param map 로그인한 사용자의 이메일, 페이징 startRow, endRow
	 * @return Item list를 반환한다
	 */
	public ArrayList<ItemDTO> list(HashMap<String, String> map) {

		try {

			String sql = String.format(
					"select a.*, (select count(*) from tblmember m inner join tblminusreward mr on m.seq = mr.mseq where m.email = '%s' and mr.iseq = a.seq) as isown from (select rownum as rnum, i.* from tblItem i) a where rnum between %s and %s",
					map.get("email"), map.get("startRow"), map.get("endRow"));

			stat = conn.createStatement();

			rs = stat.executeQuery(sql);

			ArrayList<ItemDTO> list = new ArrayList<ItemDTO>();

			while (rs.next()) {

				ItemDTO dto = new ItemDTO();

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

	/**
	 * Item list를 페이징 하기위한 Item의 총 개수 확인 메소드
	 * 
	 * 
	 * @return Item의 총 개수를 반환한다.
	 */
	public int getTotalCount() {

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

	/**
	 * Item번호로 itemname에 "테마" 유무 확인 후 테마 관련 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return Theme 관련 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchTheme(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemname) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"테마");

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

	/**
	 * Item번호로 itemname에 "폰트" 유무 확인 후 폰트 관련 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return Font 관련 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchFont(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"폰트");

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

	/**
	 * 테마관련 아이템 구매시, option 테이블의 theme 수정 메소드
	 * 
	 * @param auth    로그인계정
	 * @param itemSeq Item 번호
	 * @return theme 수정 성공: 1 반환, 실패: 0 반환
	 */
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

	/**
	 * 폰트관련 아이템 구매시, option 테이블의 fonttype 수정 메소드
	 * 
	 * @param auth    로그인계정
	 * @param itemSeq Item 번호
	 * @return theme 수정 성공: 1 반환, 실패: 0 반환
	 */
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

	/**
	 * Item번호로 itemname에 "투두" 유무 확인 후 sub투두리스트 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return sub투두리스트 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchTodo(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"투두");

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

	/**
	 * Item번호로 itemname에 "타임" 유무 확인 후 타임테이블 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return 타임테이블 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchTimeTable(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"타임");

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

	/**
	 * Item번호로 itemname에 "캘린더" 유무 확인 후 캘린더 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return 캘린더 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchCal(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"캘린더");

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

	/**
	 * Item번호로 itemname에 "표" 유무 확인 후 24시간계획표 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return 24시간계획표 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchCir(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"표");

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

	/**
	 * Item번호로 itemname에 "응원" 유무 확인 후 응원메세지 Item인지 판단하는 메소드
	 * 
	 * @param itemSeq Item 번호
	 * @return 응원메세지 Item이 맞으면 양의 정수를 반환한다.
	 */
	public int searchMessege(String itemSeq) {

		try {

			String sql = String.format(
					"select count(itemName) as cnt from tblItem where (seq = ?) and itemname like '%%' || '%s' ||'%%'",
					"응원");

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
