package org.myBoard.board.models.member;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.myBoard.board.commons.constants.Role;
import org.myBoard.board.controllers.members.JoinForm;
import org.myBoard.board.entities.Member;
import org.myBoard.board.repositories.MemberRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 회원 정보 추가, 수정
 * - 수정시 비밀번호는 값이 있을때만 수정
 */
@Service
@RequiredArgsConstructor
public class MemberSaveService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void save(JoinForm joinForm) {

        Member member = new ModelMapper().map(joinForm, Member.class);
        member.setRoles(Role.USER); // 일반회원

        member.setUserPw(passwordEncoder.encode(joinForm.getUserPw()));

        memberRepository.saveAndFlush(member);
    }


}
