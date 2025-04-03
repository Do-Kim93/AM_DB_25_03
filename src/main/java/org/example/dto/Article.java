package org.example.dto;

import java.util.Map;

public class Article {
    private int id;
    private String regDate;
    private String updateDate;
    private String title;
    private String body;
    private String loginId;
    private int loginIdNum;

    public Article(int id, String regDate, String updateDate, String title, String body, String loginId, int loginIdNum) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.title = title;
        this.body = body;
        this.loginIdNum = loginIdNum;
        this.loginId = loginId;
    }

    public Article(int id, String title, String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Article(Map<String, Object> articleMap) {
        this.id = (int) articleMap.get("id");
        this.regDate = (String) articleMap.get("regDate");
        this.updateDate = (String) articleMap.get("updateDate");
        this.title = (String) articleMap.get("title");
        this.body = (String) articleMap.get("body");
        this.loginIdNum = (int) articleMap.get("loginIdNum");
        this.loginId = (String) articleMap.get("loginId");
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLoginId() {
        return loginId;
    }
    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public int getLoginIdNum() {
        return loginIdNum;
    }

    public void setLoginIdNum(int loginIdNum) {
        this.loginIdNum = loginIdNum;
    }


}