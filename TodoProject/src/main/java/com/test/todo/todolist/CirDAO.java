package com.test.todo.todolist;

import com.test.todo.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class CirDAO {

    private Connection conn;
    private Statement stat;
    private PreparedStatement pstat;
    private ResultSet rs;

    public CirDAO() {
        conn = DBUtil.open();
    }

    public ArrayList<CirDTO> listChart1(CirDTO dto) {

        try {

            String sql = "select * from TBLCIRCLETABLE where MSEQ=?";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getMseq());

            rs = pstat.executeQuery();

            ArrayList<CirDTO> list = new ArrayList<CirDTO>();

            while (rs.next()) {
                CirDTO result = new CirDTO();
                result.setSeq(rs.getString("seq"));
//                result.setNickname(rs.getString("nickname"));
                result.setContent(rs.getString("content"));
                result.setStarttime(rs.getString("starttime"));
                result.setEndtime(rs.getString("endtime"));
//                result.setMseq(rs.getString("mseq"));

                list.add(result);

            }

            return list;


        } catch (Exception e) {
//            System.out.println("CirDAO.listChart1");
            e.printStackTrace();
        }

        return null;
    }

    public void add(CirDTO dto) {

        try {
            CirDAO dao = new CirDAO();

            String sql = "insert into tblCircletable values (seqCircleTable.nextval, ? , ? , ? , ? )";

            pstat = conn.prepareStatement(sql);
            pstat.setString(1, dto.getContent());
            pstat.setString(2, dto.getStarttime());
            pstat.setString(3, dto.getEndtime());
            pstat.setString(4, dto.getMseq());

            rs = pstat.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public int del(String mseq) {
        try {
            String sql = "delete from TBLCIRCLETABLE where MSEQ = ?";
            pstat = conn.prepareStatement(sql);
            pstat.setString(1, mseq);
            return pstat.executeUpdate();
        } catch (Exception e) {

        }
        return 0;
    }
}




































