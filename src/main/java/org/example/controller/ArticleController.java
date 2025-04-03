package org.example.controller;

import org.example.container.Container;
import org.example.dto.Article;
import org.example.service.ArticleService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ArticleController {

    private ArticleService articleService;
    private Scanner sc;

    public ArticleController() {
        this.articleService = Container.articleService;
        this.sc = Container.sc;

    }

    public void doWrite() {
        if (Container.session.loginedMember != null) {
            System.out.println("==글쓰기==");
            System.out.print("제목 : ");
            String title = sc.nextLine();
            System.out.print("내용 : ");
            String body = sc.nextLine();

            int id = articleService.doWrite(title, body, Container.session.loginedMember.getId());
            System.out.println(id + "번 글이 생성됨");
        } else if (Container.session.loginedMember == null) {
            System.out.println("==비회원 글쓰기==");
            System.out.println("수정/삭제를 위한 비번 숫자만");
            int pw = sc.nextInt();
            sc.nextLine();
            System.out.print("제목 : ");
            String title = sc.nextLine();
            System.out.print("내용 : ");
            String body = sc.nextLine();

            int id = articleService.doWrite(title, body, pw);
            System.out.println(id + "번 글이 생성됨");
        }
    }

    public void showList() {
        System.out.println("==목록==");

        List<Article> articles = articleService.getArticles();

        if (articles.size() == 0) {
            System.out.println("게시글이 없습니다");
            return;
        }
        if (articles.size() <= 10) {
            System.out.println("  번호  /   제목  /   회원번호  /  작성자아이디  ");
            for (Article article : articles) {
                System.out.printf("  %d     /   %s    /  %d     /   %s   \n", article.getId(), article.getTitle(), article.getLoginIdNum(), article.getLoginId());
            }
        } else {
            int p = articles.size()/10+1;
            while (true) {
                System.out.printf("페이지 숫자만 입력 %d 페이지 까지 존재함\n 그만 보고 싶으면 종료 입력",p);
                String cmd = sc.nextLine();
                if (cmd.equals("종료")) {
                    break;
                }else {
                    int paging = Integer.parseInt(cmd);
                    paging = (paging-1)*10;
                    List<Article> articless = articleService.getArticles(paging);
                    System.out.println("  번호  /   제목  /   회원번호  /  작성자아이디  ");
                    for (Article article : articless) {
                        System.out.printf("  %d     /   %s    /  %d     /   %s   \n", article.getId(), article.getTitle(), article.getLoginIdNum(), article.getLoginId());
                    }
                }

            }

        }
    }

    public void doModify(String cmd) {

        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }

        Map<String, Object> articleMap = articleService.getArticleById(id);

        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없어");
            return;
        }
        if (Container.session.loginedMember != null) {
            String loginCheckNum = Container.session.loginedMember.getLoginId();
//            Article article = new Article(articleMap);
//            int articleCheckNum = article.getLoginIdNum();
            String articleCheckNum = (String) articleMap.get("loginId");
            if (loginCheckNum.equals(articleCheckNum)) {
                System.out.println("==수정==");
                System.out.print("새 제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String body = sc.nextLine().trim();

                articleService.doUpdate(id, title, body);

                System.out.println(id + "번 글이 수정되었습니다.");
            } else System.out.println("수정 권한이 없음");
        } else {
            System.out.println("비밀번호 입력");
            int pw = sc.nextInt();
            sc.nextLine();
            if (pw == (int) articleMap.get("loginIdNum")) {
                System.out.println("==수정==");
                System.out.print("새 제목 : ");
                String title = sc.nextLine().trim();
                System.out.print("새 내용 : ");
                String body = sc.nextLine().trim();

                articleService.doUpdate(id, title, body);

                System.out.println(id + "번 글이 수정되었습니다.");
            } else System.out.println("권한 없음");
        }


    }

    public void showDetail(String cmd) {

        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }

        System.out.println("==상세보기==");

        Map<String, Object> articleMap = articleService.getArticleById(id);

        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없어");
            return;
        }

        Article article = new Article(articleMap);

        System.out.println("번호 : " + article.getId());
        System.out.println("작성날짜 : " + article.getRegDate());
        System.out.println("수정날짜 : " + article.getUpdateDate());
        System.out.println("제목 : " + article.getTitle());
        System.out.println("내용 : " + article.getBody());
        System.out.println("작성자 : " + article.getLoginId());
    }

    public void doDelete(String cmd) {

        int id = 0;

        try {
            id = Integer.parseInt(cmd.split(" ")[2]);
        } catch (Exception e) {
            System.out.println("번호는 정수로 입력해");
            return;
        }

        Map<String, Object> articleMap = articleService.getArticleById(id);

        if (articleMap.isEmpty()) {
            System.out.println(id + "번 글은 없어");
            return;
        }
        if (Container.session.loginedMember != null) {
            int loginCheckNum = Container.session.loginedMember.getId();
//            Article article = new Article(articleMap);
//            int articleCheckNum = article.getLoginIdNum();
            int articleCheckNum = (int) articleMap.get("loginIdNum");
            if (loginCheckNum == articleCheckNum) {
                System.out.println("==삭제==");

                articleService.doDelete(id);

                System.out.println(id + "번 글이 삭제되었습니다.");
            } else System.out.println("권한 없음");
        } else {
            System.out.println("비밀번호 입력");
            int pw = sc.nextInt();
            sc.nextLine();
            if (pw == (int) articleMap.get("loginIdNum")) {
                System.out.println("==삭제==");

                articleService.doDelete(id);
            } else System.out.println("권한 없음");

        }
    }
}