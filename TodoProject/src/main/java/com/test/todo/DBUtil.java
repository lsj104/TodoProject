package com.test.todo;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * 데이터 베이스 연결 클래스
 * 
 * @author 4조
 *
 */
public class DBUtil {
    private static Connection conn = null;

    
    /**
     * DB연결
     * 
     * @return DB연결 여부
     */
    public static Connection open() {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String id = "team_4";
        String pw = "java1234";

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            conn = DriverManager.getConnection(url, id, pw);

            return conn;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}












































