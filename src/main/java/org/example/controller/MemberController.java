package org.example.controller;

import org.example.dto.Member;
import org.example.service.MemberService;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static org.example.App.loginMember;

public class MemberController {
    private Connection conn;
    private Scanner sc;

    private MemberService memberService;

    public MemberController(Scanner sc, Connection conn) {
        this.sc = sc;
        this.conn = conn;
        this.memberService = new MemberService();
    }

    public void doJoin() {
        String loginId = null;
        String loginPw = null;
        String loginPwConfirm = null;
        String name = null;
        System.out.println("==회원가입==");
        while (true) {
            System.out.print("로그인 아이디 : ");
            loginId = sc.nextLine().trim();

            if (loginId.length() == 0 || loginId.contains(" ")) {
                System.out.println("아이디 똑바로 써");
                continue;
            }
            boolean isLoginIdDup = memberService.isLoginIdDup(loginId, conn);

            System.out.println(isLoginIdDup);

            if (isLoginIdDup) {
                System.out.println(loginId + "은(는) 이미 사용중");
                continue;
            }
            break;

        }
        while (true) {
            System.out.print("비밀번호 : ");
            loginPw = sc.nextLine().trim();

            if (loginPw.length() == 0 || loginPw.contains(" ")) {
                System.out.println("비번 똑바로 써");
                continue;
            }

            boolean loginCheckPw = true;

            while (true) {
                System.out.print("비번 확인 : ");
                loginPwConfirm = sc.nextLine().trim();

                if (loginPwConfirm.length() == 0 || loginPwConfirm.contains(" ")) {
                    System.out.println("비번 확인 똑바로 써");
                    continue;
                }

                if (loginPw.equals(loginPwConfirm) == false) {
                    System.out.println("일치하지 않아");
                    loginCheckPw = false;
                }
                break;
            }
            if (loginCheckPw) {
                break;
            }
        }
        while (true) {
            System.out.print("이름 : ");
            name = sc.nextLine();

            if (name.length() == 0 || name.contains(" ")) {
                System.out.println("이름 똑바로 써");
                continue;
            }
            break;
        }


        int id = memberService.doJoin(conn, loginId, loginPw, name);

        System.out.println(id + "번 회원이 가입됨");


    }

    public void showList() {
        System.out.println("==회원 목록==");
        List<Member> members = new ArrayList<>();

        List<Map<String, Object>> memberListMap = memberService.showList(conn);

        for (Map<String, Object> memberMap : memberListMap) {
            members.add(new Member(memberMap));
        }

        if (members.size() == 0) {
            System.out.println("게시글이 없습니다");
            return;
        }

        System.out.println("  번호  /   제목  ");
        for (Member member : members) {
            System.out.printf("  %d     /   %s    /   %s   /   %s    /    %s  \n", member.getId(), member.getRegDate().split(" ")[0], member.getUpdateDate().split(" ")[0], member.getLoginId(), member.getName());
        }
    }

    public void doLogin() {
        if (loginMember != null) {
            System.out.println("이미 로그인 되어있슴");
            return;
        }
        System.out.println("==로그인==");
        int i = 1;
        while (i<=3) {
            if (i == 3) {
                System.out.println("마지막 기회다");
            }
            System.out.print("아이디 입력 : ");
            String loginId = sc.nextLine().trim();
            System.out.print("비밀번호 입력 : ");
            String loginPw = sc.nextLine().trim();
//            boolean isLoginIdDup = memberService.isLoginIdDup(loginId, conn);
            Map<String, Object> isLoginPwDup = memberService.isLoginPwDup(loginId, conn);
            loginMember = new Member(isLoginPwDup);
            if (loginId.equals(loginMember.getLoginId())) {
                if (loginPw.equals(loginMember.getLoginPw())) {
                    System.out.println(loginMember.getLoginId()+ "님 반갑습니다.");
                    break;
                }else {
                    System.out.println("비밀번호가 다릅니다");
                    i++;
                }
            }else {
                System.out.println("아이디가 다릅니다");
                i++;
            }
        }
    }
    public void doLogout() {
        if (loginMember != null) {
            loginMember = null;
            try {
                System.out.println(loginMember.getLoginId()+"님 로그아웃 실패");
            }catch (NullPointerException e) {
                System.out.println("로그아웃 완료");
            }
        }else System.out.println("로그인부터해");

    }

    public void showDetail(String cmd) {
        System.out.println("==상세보기==");

        if (loginMember==null) {
            System.out.println("로그인 해");
            return;
        }else {
            System.out.println(loginMember);
        }


    }
}
