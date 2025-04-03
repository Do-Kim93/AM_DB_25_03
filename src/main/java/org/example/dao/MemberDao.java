package org.example.dao;

import org.example.util.DBUtil;
import org.example.util.SecSql;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MemberDao {



    public boolean isLoginIdDup(String loginId, Connection conn) {
        SecSql sql = new SecSql();

        sql.append("SELECT COUNT(*) > 0");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DBUtil.selectRowBooleanValue(conn, sql);
    }
    public Map<String, Object> isLoginPwDup(String loginId, Connection conn) {
        SecSql sql = new SecSql();

        sql.append("SELECT *");
        sql.append("FROM `member`");
        sql.append("WHERE loginId = ?;", loginId);

        return DBUtil.selectRow(conn, sql);
    }
    public int doJoin(Connection conn, String loginId, String loginPw, String name) {
        SecSql sql = new SecSql();

        sql.append("INSERT INTO `member`");
        sql.append("SET regDate = NOW(),");
        sql.append("updateDate = NOW(),");
        sql.append("loginId = ?,", loginId);
        sql.append("loginPw = ?,", loginPw);
        sql.append("`name` = ?;", name);

        return DBUtil.insert(conn, sql);
    }
    public List<Map<String, Object>> showList(Connection conn) {
        SecSql sql = new SecSql();

        sql.append("SELECT * FROM `member`");
        sql.append("ORDER BY id DESC;");

        return DBUtil.selectRows(conn, sql);
    }


}
