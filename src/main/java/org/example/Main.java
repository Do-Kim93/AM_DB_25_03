package org.example;


import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("==프로그램 시작==");

        Scanner sc = new Scanner(System.in);


        while (true) {
            System.out.print("명령어 > ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("exit")) {
                break;
            }
            if (cmd.equals("article write")) {
                System.out.println("==글쓰기==");
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();
                String regDate = LocalDateTime.now().toString();
                String updateDate = LocalDateTime.now().toString();
                String sql = " insert into article (regDate, updateDate, title, body) values ('" + regDate + "', '" + updateDate + "',  '" + title + "', '" + body + "')";
                dao.add(sql);
            } else if (cmd.equals("article list")) {
                System.out.println("==목록==");
                String sql = "select * from article order by id desc;";
                dao.list(sql);
            } else if (cmd.startsWith("article modify")) {
                System.out.println("==수정==");
                int idnum = Integer.parseInt(cmd.split(" ")[2]);
                System.out.print("제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("내용 : ");
                String body = sc.nextLine().trim();
                String updateDate = LocalDateTime.now().toString();
                String sql = "UPDATE article SET updateDate = '" + updateDate + "', title = '" + title + "', `body` = '" + body + "' WHERE id = " + idnum + ";";
                dao.modify(sql);

            } else if (cmd.startsWith("article delete")) {
                int idnum = Integer.parseInt(cmd.split(" ")[2]);
                String sql = "delete from article where id = '" + idnum + "';";
                dao.delete(sql, idnum);
            }
        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}