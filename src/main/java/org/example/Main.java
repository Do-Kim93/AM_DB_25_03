package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("article write")) {

                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");
                    System.out.println("==글쓰기==");
                    System.out.print("제목 : ");
                    String title = sc.nextLine().trim();
                    System.out.print("내용 : ");
                    String body = sc.nextLine().trim();
                    String sql = " insert into article (title, body) values ('" + title + "', '" + body + "');";
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
            } else if (cmd.equals("article list")) {
                System.out.println("==목록==");
                try {
                    Class.forName("org.mariadb.jdbc.Driver");
                    String url = "jdbc:mariadb://127.0.0.1:3306/AM_DB_25_03?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul";
                    conn = DriverManager.getConnection(url, "root", "");
                    System.out.println("연결 성공!");
                    String sql = "select * from article";
                    pstmt = conn.prepareStatement(sql);
                    rs = pstmt.executeQuery();
                    System.out.println("   아이디   /   번호    /    제목    ");
                    while (rs.next()) {
                        int id = rs.getInt("id");
                        String title = rs.getString("title");
                        String body = rs.getString("body");
                        System.out.printf("   %d     /   %s     /   %s    \n", id,title,body);

                }


            } catch(ClassNotFoundException e){
                System.out.println("드라이버 로딩 실패" + e);
            } catch(SQLException e){
                System.out.println("에러 : " + e);
            } finally{
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

        System.out.println("==프로그램 종료==");
        sc.close();
}
}