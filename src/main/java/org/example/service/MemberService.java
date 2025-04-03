package org.example.service;

import org.example.dao.MemberDao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

public class MemberService {
    private MemberDao memberDao;
    public MemberService() {
        this.memberDao = new MemberDao();

    }

    public boolean isLoginIdDup(String loginId, Connection conn) {
        return memberDao.isLoginIdDup(loginId, conn);
    }
    public int doJoin(Connection conn, String loginId, String loginPw, String name) {
        return memberDao.doJoin(conn,loginId,loginPw,name);
    }

    public List<Map<String, Object>> showList(Connection conn) {
        return memberDao.showList(conn);
    }

    public Map<String, Object> isLoginPwDup(String loginId, Connection conn) {
        return memberDao.isLoginPwDup(loginId, conn);
    }
}
