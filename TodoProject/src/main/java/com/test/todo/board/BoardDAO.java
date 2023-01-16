package com.test.todo.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.todo.DBUtil;
import com.test.todo.board.BoardDTO;

/**
 * BoardDAO
 * 
 * @author 4조
 */
public class BoardDAO {

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	/**
	 * 
	 * DB연결 메소드
	 * 
	 */
	public BoardDAO() {
		conn = DBUtil.open();
	}

	/**
	 * 회원 번호에 따른 게시글을 출력하는 메소드
	 * 
	 * @param seq 회원 번호
	 * @return 게시글 객체
	 */
	public BoardDTO get(String seq) {

		try {

			String sql = "select q.seq as seq, m.seq as mseq, q.qdate as qdate, q.title as title, q.content as content, q.readcount as readcount, i.nickname as nickname, i.image as image from tblQuestionBoard q inner join tblMember m on m.seq = q.Mseq inner join tblMemberInfo i on m.seq = i.mseq where q.seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			if (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setMseq(rs.getString("mseq"));
				dto.setNickname(rs.getString("nickname"));
				dto.setQdate(rs.getString("qdate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setImage(rs.getString("image"));

				return dto;

			}

		} catch (Exception e) {
			System.out.println("BoardDAO.get");
			e.printStackTrace();

		}

		return null;

	}

	/**
	 * 게시글 list를 확인하는 메소드
	 * 
	 * @param map 검색어 및 검색 종류의 정보를 가진 HashMap
	 * @return 게시글 list
	 */
	public ArrayList<BoardDTO> list(HashMap<String, String> map) {

		try {

			String sql = "";
			String where = "";

			if (map.get("isSearch").equals("y")) {

				/*
				 * if (!map.get("column").equals("all")) { where =
				 * String.format("where %s like '%%' || '%s' || '%%'" , map.get("column") ,
				 * map.get("word")); } else { where = String.
				 * format("where title like '%%' || '%s' || '%%' or contnent like '%%' || '%s' || '%%'"
				 * , map.get("word") , map.get("word")); }
				 */

				where = String.format("where %s like '%%%s%%'", map.get("column"), map.get("word"));
			}

			sql = String.format("select \r\n" + "    q.seq as seq,\r\n" + "    q.qdate as qdate,\r\n"
					+ "    q.title as title,\r\n" + "    q.content as content,\r\n"
					+ "    q.readcount as readcount,\r\n" + "    i.nickname as nickname,\r\n"
					+ "    i.image as image\r\n" + "from tblQuestionBoard q inner join tblMember m\r\n"
					+ "				on m.seq = q.Mseq inner join tblMemberInfo i on m.seq = i.mseq %s", where);

			pstat = conn.prepareStatement(sql);

			rs = pstat.executeQuery();

			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();

			while (rs.next()) {

				BoardDTO dto = new BoardDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setQdate(rs.getString("qdate"));
				dto.setTitle(rs.getString("title"));
				dto.setContent(rs.getString("content"));
				dto.setReadcount(rs.getString("readcount"));
				dto.setNickname(rs.getString("nickname"));
				dto.setImage(rs.getString("image"));

				list.add(dto);

			}

			return list;

		} catch (Exception e) {
			System.out.println("BoardDAO.list");
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 게시글 추가 메소드
	 * 
	 * @param email   회원 계정
	 * @param title   게시글 제목
	 * @param content 게시글 내용
	 * @return 추가 성공: 1, 실패: 0
	 */
	public int add(String email, String title, String content) {
		try {

			String sql = "insert into tblQuestionBoard (seq, Mseq, Qdate, title, content, readcount) "
					+ "values (seqQboard.nextVal, (select seq from tblMember where email = ?), default, ?, ?, default)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, email);
			pstat.setString(2, title);
			pstat.setString(3, content);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.add");
			e.printStackTrace();
		}

		return 0;

	}

	/**
	 * 게시글 수정 메소드
	 * 
	 * @param dto 게시글 객체
	 * @return 수정 성공: 1, 실패: 0
	 */
	public int edit(BoardDTO dto) {

		try {

			String sql = "update tblQuestionBoard set title = ?, content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getTitle());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.edit");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 게시글 삭제 메소드
	 * 
	 * @param seq 게시글 번호
	 * @return 삭제 성공: 1, 실패: 0
	 */
	public int del(String seq) {

		try {

			String sql = "delete from tblQuestionBoard where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.del");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 조회수 누적 메소드
	 * 
	 * @param seq 게시글 번호
	 */
	public void addReadCount(String seq) {

		try {

			String sql = "update tblQuestionBoard set readcount = readcount + 1 where seq = ?";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addReadCount");
			e.printStackTrace();
		}

	}

	/**
	 * 해당 게시글에 댓글 추가 메소드
	 * 
	 * @param dto 답변 객체
	 * @return 추가 성공: 1, 실패: 0
	 */
	public int addComment(AnswerDTO dto) {

		try {

			String sql = "insert into tblAnswerBoard(seq, Mseq, Qseq, Adate, content) values"
					+ "(seqAboard.nextVal, (select seq from tblMember where email = ?), ?, default, ?)";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, dto.getName());
			pstat.setString(2, dto.getQseq());
			pstat.setString(3, dto.getContent());

			return pstat.executeUpdate();

		} catch (Exception e) {
			System.out.println("BoardDAO.addComment");
			e.printStackTrace();
		}

		return 0;
	}

	/**
	 * 댓글 리스트 메소드
	 * 해당 게시글의 모든 댓글을 list 반환한다.
	 * 
	 * @param seq 게시글 번호
	 * @return 해당 게시글의 댓글 list
	 */
	public ArrayList<AnswerDTO> alist(String seq) {

		try {

			String sql = "select tblAnswerBoard.*, (select nickname from tblMemberInfo i inner join tblMember m on i.mseq = m.seq where i.mseq = tblAnswerBoard.mseq) as nickname, (select image from tblMemberInfo i inner join tblMember m on i.mseq = m.seq where i.mseq = tblAnswerBoard.mseq) as image from tblAnswerBoard where qseq = ? order by seq desc";

			pstat = conn.prepareStatement(sql);

			pstat.setString(1, seq);

			rs = pstat.executeQuery();

			ArrayList<AnswerDTO> alist = new ArrayList<AnswerDTO>();

			while (rs.next()) {

				AnswerDTO dto = new AnswerDTO();

				dto.setSeq(rs.getString("seq"));
				dto.setContent(rs.getString("content"));
				dto.setQseq(seq);
				dto.setAdate(rs.getString("adate"));
				dto.setMseq(rs.getString("mseq"));
				dto.setNickname(rs.getString("nickname"));
				dto.setImage(rs.getString("image"));

				System.out.println(dto);

				alist.add(dto);
			}

			return alist;

		} catch (Exception e) {
			System.out.println("BoardDAO.alist");
			e.printStackTrace();
		}

		return null;
	}

}
