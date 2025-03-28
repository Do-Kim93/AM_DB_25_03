package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dao {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;


    static void add(String sql) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            int result = pstmt.executeUpdate();
            if (result == 1) System.out.println("글이 작성 되었습니다.");
            else System.out.println("insert fail");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
        List<Article> articles = new ArrayList<>();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String body = rs.getString("body");
                String regDate = rs.getString("regDate");
                String updateDate = rs.getString("updateDate");
                Article article = new Article(id, regDate, updateDate, title, body);
                articles.add(article);
            }
            if (!articles.isEmpty()) {
                System.out.println(articles.size());

                System.out.println("   아이디   /   번호    /    제목        /     작성날짜                   /             수정날짜     ");
                for (int i = 0; i < articles.size(); i++) {
                    System.out.printf("   %d     /   %s     /   %s         /   %s         /   %s    \n", articles.get(i).getId(), articles.get(i).getTitle(), articles.get(i).getBody(), articles.get(i).getRegDate(), articles.get(i).getUpdateDate());
                }

            }else System.out.println("게시글 없음");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    static void modify(String sql) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            int result = pstmt.executeUpdate();
            if (result == 1) System.out.println("글이 수정 되었습니다.");
            else System.out.println("해당글 없음");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void delete(String sql, int idnum) {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
            conn = DriverManager.getConnection(url, "root", "");
            System.out.println("연결 성공!");
            pstmt = conn.prepareStatement(sql);
            int result = pstmt.executeUpdate();
            if (result == 1) System.out.println(idnum+"번 글이 삭제 되었습니다.");
            else System.out.println("글없음");

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패" + e);
        } catch (SQLException e) {
            System.out.println("에러 : " + e);
        } finally {
            try {
                if (pstmt != null && !pstmt.isClosed()) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
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
