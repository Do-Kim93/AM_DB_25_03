package org.example.service;

import org.example.dao.ArticleDao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleService {
    private ArticleDao articleDao;
    public ArticleService() {
        this.articleDao = new ArticleDao();
    }
    public int doWrite(Connection conn, String title, String body) {
        return articleDao.doWrite(conn,title,body);
    }
    public List<Map<String, Object>> showList(Connection conn) {
        return articleDao.showList(conn);
    }
    public Map<String, Object> foundId(Connection conn, int id) {
        return articleDao.foundId(conn, id);
    }
    public void doModify(Connection conn, int id, String title, String body) {
        articleDao.doModify(conn, id, title, body);
    }
    public Map<String, Object> showDetail(Connection conn, int id) {
        return articleDao.foundId(conn, id);
    }
    public Map<String, Object> doDeleteCheck(Connection conn, int id) {
        return articleDao.foundId(conn, id);
    }
    public void doDelete(Connection conn, int id) {
        articleDao.doDelete(conn, id);
    }
}
