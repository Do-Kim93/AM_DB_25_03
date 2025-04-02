package org.example.controller;

import org.example.Article;
import org.example.service.ArticleService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ArticleController {
    Connection conn;
    Scanner sc;
    String cmd;

    ArticleService articleService;

    public ArticleController(Scanner sc, Connection conn, String cmd) {
        this.conn = conn;
        this.sc = sc;
        this.articleService = new ArticleService();
        this.cmd = cmd;
    }

    public void doWrite() {
        System.out.println("==글쓰기==");
        System.out.print("제목 : ");
        String title = sc.nextLine();
        System.out.print("내용 : ");
        String body = sc.nextLine();


        int id = articleService.doWrite(conn, title, body);

        System.out.println(id + "번 글이 생성됨");


    }

    public void showList() {
        System.out.println("==목록==");
        List<Article> articles = new ArrayList<>();

        List<Map<String, Object>> articleListMap = articleService.showList(conn);

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }

        if (articles.size() == 0) {
            System.out.println("게시글이 없습니다");
            return;
        }

        System.out.println("  번호  /   제목  ");
        for (Article article : articles) {
            System.out.printf("  %d     /   %s   \n", article.getId(), article.getTitle());
        }
    }

    public void doModify() {
        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }

        Map<String, Object> articleMap = articleService.foundId(conn, id);

        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없음");
            return;
        }

        System.out.println("==수정==");
        System.out.print("새 제목 : ");
        String title = sc.nextLine().trim();
        System.out.print("새 내용 : ");
        String body = sc.nextLine().trim();
        articleService.doModify(conn, id, title, body);

        System.out.println(id + "번 글이 수정되었습니다.");
    }

    public void showDetail() {
        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }
        Map<String, Object> articleMap = articleService.showDetail(conn,id);
        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없음");
            return;
        }
        Article article = new Article(articleMap);
        System.out.println("번호 : " + article.getId());
        System.out.println("작성날짜 : " + article.getRegDate());
        System.out.println("수정날짜 : " + article.getUpdateDate());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
    }

    public void doDelete() {
        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }
        Map<String, Object> articleMap = articleService.doDeleteCheck(conn, id);
        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없음");
            return;
        }
        System.out.println("==삭제==");
        articleService.doDelete(conn, id);
        System.out.println(id + "번 글이 삭제되었습니다.");
    }
}
