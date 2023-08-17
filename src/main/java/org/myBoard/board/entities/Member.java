package org.myBoard.board.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.myBoard.board.commons.constants.Role;

@Entity @Data @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_member_userNm", columnList = "userNm"),
        @Index(name = "idx_member_email", columnList = "email")
})
public class Member extends BaseEntitiy{
    @Id @GeneratedValue // 기본키
    private Long userNo;   // 회원번호

    @Column(length = 40, nullable = false, unique = true) // 길이 40자, null안됨, 필수항목
    private String userId; // 회원 아이디

    @Column(length = 65,nullable = false)
    private String userPw; // 회원 비밀번호

    @Column(length = 40, nullable = false)
    private String userNm; // 회원 명

    @Column(length = 100, nullable = false)
    private String email;  // 이메일

    @Column(length = 11)
    private String mobile; // 휴대전화 번호

    @Lob
    private String termsAgree; // 약관 동의 내역

    @Enumerated(EnumType.STRING)
    @Column(length = 10, nullable = false)
    private Role roles = Role.USER;
}
