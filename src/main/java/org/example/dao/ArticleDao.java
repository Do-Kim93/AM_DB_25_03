package org.example.dao;

import org.example.container.Container;
import org.example.dto.Article;
import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticleDao {

    public int doWrite(String title, String body, int id) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO article");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("title = ?,", title);
        sql.append("`body`= ?,", body);
        sql.append("loginIdNum = ?;", id);


        return DBUtil.insert(Container.conn, sql);
    }

    public List<Article> getArticles(int paging) {
        SecSql sql = new SecSql();
        sql.append("SELECT a.*, m.loginId");
        sql.append("FROM article as a");
        sql.append("left join `member` as m on a.loginIdNum = m.id");
        sql.append("ORDER BY a.id DESC");
        sql.append("limit ?, ", paging);
        sql.append("?; ", 10);

        List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

        List<Article> articles = new ArrayList<>();

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }
        return articles;
    }
    public List<Article> getArticles() {
        SecSql sql = new SecSql();
        sql.append("SELECT a.*, m.loginId");
        sql.append("FROM article as a");
        sql.append("left join `member` as m on a.loginIdNum = m.id");
        sql.append("ORDER BY a.id DESC;");

        List<Map<String, Object>> articleListMap = DBUtil.selectRows(Container.conn, sql);

        List<Article> articles = new ArrayList<>();

        for (Map<String, Object> articleMap : articleListMap) {
            articles.add(new Article(articleMap));
        }
        return articles;
    }

    public Map<String, Object> getArticleById(int id) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM article as a");
        sql.append("left join `member` as m on a.loginIdNum = m.id");
        sql.append("WHERE a.id = ?", id);

        return DBUtil.selectRow(Container.conn, sql);
    }

    public void doUpdate(int id, String title, String body) {
        SecSql sql = new SecSql();
        sql.append("UPDATE article");
        sql.append("SET updateDate = NOW()");
        if (title.length() > 0) {
            sql.append(",title = ?", title);
        }
        if (body.length() > 0) {
            sql.append(",`body` = ?", body);
        }
        sql.append("WHERE id = ?", id);

        DBUtil.update(Container.conn, sql);
    }

    public void doDelete(int id) {
        SecSql sql = new SecSql();
        sql.append("DELETE FROM article");
        sql.append("WHERE id = ?", id);

        DBUtil.delete(Container.conn, sql);
    }
}