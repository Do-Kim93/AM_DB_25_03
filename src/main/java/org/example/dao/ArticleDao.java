package org.example.dao;

import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class ArticleDao {



    public int doWrite(Connection conn, String title, String body) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO article");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("title = ?,", title);
        sql.append("`body` = ?;", body);

        return DBUtil.insert(conn, sql);
    }


    public List<Map<String, Object>> showList(Connection conn) {

        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("ORDER BY id DESC");
        return DBUtil.selectRows(conn, sql);
    }

    public Map<String, Object> foundId(Connection conn, int id) {
        SecSql sql = new SecSql();
        sql.append("SELECT *");
        sql.append("FROM article");
        sql.append("WHERE id = ?;", id);
        return DBUtil.selectRow(conn, sql);
    }

    public int doModify(Connection conn, int id, String title, String body) {
        SecSql sql = new SecSql();
        sql.append("UPDATE article");
        sql.append("SET updateDate = NOW()");
        if (title.length() > 0) {
            sql.append(", title = ?", title);
        }
        if (body.length() > 0) {
            sql.append(",`body` = ?", body);
        }
        sql.append("WHERE id = ?;", id);

        return DBUtil.update(conn, sql);
    }
    public int doDelete(Connection conn, int id) {
        SecSql sql = new SecSql();
        sql.append("DELETE FROM article");
        sql.append("WHERE id = ?;", id);

        return DBUtil.delete(conn, sql);
    }
}
