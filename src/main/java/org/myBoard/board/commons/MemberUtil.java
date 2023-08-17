package org.myBoard.board.commons;

import jakarta.servlet.http.HttpSession;
import org.modelmapper.ModelMapper;
import org.myBoard.board.commons.constants.Role;
import org.myBoard.board.entities.Member;
import org.myBoard.board.models.member.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberUtil {

    @Autowired
    private HttpSession session;

    /**
     * 로그인 여부
      * @return
     */
    public boolean isLogin() {

        return getMember() != null;
    }

    /**
     * 관리자 여부
     * @return
     */
    public boolean isAdmin() {

        return (isLogin() && getMember().getRoles() == Role.ADMIN);
    }

    public MemberInfo getMember() {

        MemberInfo memberInfo = (MemberInfo) session.getAttribute("memberInfo");

        return memberInfo;
    }

    public Member getEntity() {

        if (isLogin()) {
            Member member = new ModelMapper().map(getMember(), Member.class);
            return member;
        }

        return null;
    }
}
