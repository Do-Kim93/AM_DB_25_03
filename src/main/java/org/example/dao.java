package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    static List<Article> articles = new ArrayList<>();

    static void add(String sql) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            int result = pstmt.executeUpdate();
            if (result == 1) System.out.println("insert success");
            else System.out.println("insert fail");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    static void list(String sql) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            System.out.println("   아이디   /   번호    /    제목    ");
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                Article article = new Article(id, title, body);
                articles.add(article);
                if (articles == null) {
                    System.out.println("게시글이 없음");
                    return;
                }else {
                    System.out.printf("   %d     /   %s     /   %s    \n", id, title, body);
                }


            }


        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
