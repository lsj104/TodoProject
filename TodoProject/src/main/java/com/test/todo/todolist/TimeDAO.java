package com.test.todo.todolist;

import com.test.todo.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


/**
 * TimeDAO 클래스
 * 시간표
 * @author 4조
 */
public class TimeDAO {
    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    /**
	 * 
	 * DB연결
	 * 
	 */
    public TimeDAO() {
        conn = DBUtil.open();
    }


    /**
     * 
     * 로그인한 회원의 시간표 데이터를 가져오는 메소드
     * 
     * @param dto 로그인한 회원의 시간표 객체
     * @return 회원의 시간표 리스트
     */
    public ArrayList<TimeDTO> timetable(TimeDTO dto) {
        try {
            String sql = "select * from TBLTIMEBLOCK where MSEQ=?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getMseq());

            rs = pstat.executeQuery();

            ArrayList<TimeDTO> list = new ArrayList<TimeDTO>();

            while (rs.next()) {
                TimeDTO result = new TimeDTO();
                result.setContent(rs.getString("content"));
                result.setStarttime(rs.getString("starttime"));
                result.setEndtime(rs.getString("endtime"));
                result.setSeq(rs.getString("seq"));
                list.add(result);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 시간표 추가 메소드
     * 
     * @param dto 추가하려는 시간표 객체
     */
    public void add(TimeDTO dto) {

        System.out.println(dto);

        try {

            TimeDAO dao = new TimeDAO();
            int result = dao.exist(dto);

            String sql = "";

            System.out.println("result: " + result);

            if (result > 0) {

                sql = "delete from tblTimeblock where seq = ?";

                pstat = conn.prepareStatement(sql);
                pstat.setInt(1, result);

                pstat.executeUpdate();

            }


            sql = "insert into tblTimeblock values (seqTimeblock.nextval, ?, ?, ?, ?)";


            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getContent());
            pstat.setString(2, dto.getStarttime());
            pstat.setString(3, dto.getEndtime());
            pstat.setString(4, dto.getMseq());

            rs = pstat.executeQuery();


        } catch (Exception e) {
            System.out.println("add: ");
            e.printStackTrace();
        }
    }

    /**
     * 로그인한 사용자가 추가하려는 시간에 데이터가 존재하는지 확인하는 메소드
     * 
     * @param dto 추가하려는 시간표 객체
     * @return 존재: 양의 정수 반환  
     */
    private int exist(TimeDTO dto) {

        try {

            String sql = String.format("select nvl(seq, 0) as seq from tblTimeblock " +
                            "where ((%s >= starttime and %s < endtime) or (%s >= starttime and %s < endtime)) or (%s <= starttime and %s > endtime) and mseq=%s"
                    , dto.getStarttime(), dto.getStarttime(), dto.getEndtime(), dto.getEndtime(), dto.getStarttime(), dto.getEndtime(), dto.getMseq());

//            String sql = select nvl(seq, 0) as seq from tblTimeblock where ((? >= starttime and ? < endtime) or (? >= starttime and ? < endtime)) or (? <= starttime and ? > endtime) and mseq=?;


            System.out.println("start: " + dto.getStarttime());
            System.out.println("end: " + dto.getEndtime());
            System.out.println("mseq: " + dto.getMseq());

            System.out.println(sql);

            pstat = conn.prepareStatement(sql);
//            pstat.setString(1, dto.getStarttime());
//            pstat.setString(2, dto.getStarttime());
//            pstat.setString(3, dto.getEndtime());
//            pstat.setString(4, dto.getEndtime());
//            pstat.setString(5, dto.getStarttime());
//            pstat.setString(6, dto.getEndtime());

//            pstat.setString(7, dto.getMseq());

            rs = pstat.executeQuery();

            if (rs.next()) {
                System.out.println(1111);
                return rs.getInt("seq");
            }

        } catch (Exception e) {
            System.out.println("exist");
            System.out.println(e);
        }

        return 0;

    }

    /**
     * 시간표를 지우는 메소드
     * 
     * @param mseq 회원 번호
     * @return 삭제 성공: 1, 실패: 0 반환
     */
    public int del(String mseq) {
        try {
            String sql = "delete from TBLTIMEBLOCK where MSEQ = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, mseq);
            return pstat.executeUpdate();
        } catch (Exception e) {

        }
        return 0;
    }


}
