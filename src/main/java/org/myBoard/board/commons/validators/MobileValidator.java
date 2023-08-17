package org.myBoard.board.commons.validators;

public interface MobileValidator {
    default boolean mobileNumCheck(String mobile) {
        /**
         * 010-3481-2101
         * 010_3481_2101
         * 010 3481 2101
         *
         * 1. 형식의 통일화 - 숫자가 아닌 문자 전부 제거 -> 숫자
         * 2. 패턴 생성 체크
         */
        mobile = mobile.replaceAll("\\D", "");
        String pattern ="^01[016]\\d{3,4}\\d{4}$"; // ^ : 시작되는 패턴, $ : 끝나는 패턴, d : 출력 숫자, {} : 앞의 패턴의 범위


        return mobile.matches(pattern); // 패턴이 일치하는지 확인
    }
}
