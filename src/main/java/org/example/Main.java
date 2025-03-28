package org.example;


import java.sql.*;
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
                String sql = " insert into article (title, body) values ('" + title + "', '" + body + "');";
                dao.add(sql);
            }else if (cmd.equals("article list")) {
                System.out.println("==목록==");
                String sql = "select * from article";
                dao.list(sql);
        }
        }
        System.out.println("==프로그램 종료==");
        sc.close();
    }
}