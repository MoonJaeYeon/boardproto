package org.myBoard.board.commons;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;

import java.util.ResourceBundle;

/**
 * 공통 예외
 *
 */
public class CommonException extends RuntimeException{
    protected static ResourceBundle bundleValidation; // 메세지 코드를 불러올 수 있는 번들
    protected static ResourceBundle bundleError; // 메세지 코드를 불러올 수 있는 번들

    protected HttpStatus httpStatus; // 응답 코드

    static {
        bundleValidation = ResourceBundle.getBundle("messages.validations");
        bundleError = ResourceBundle.getBundle("messages.errors");
    }

    public CommonException(String message) { // 메세지만 넣었을 때는 서버오류
        super(message);
        httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public CommonException(String message, HttpStatus httpStatus) { // 세부적으로 응답코드
        super(message);
        this.httpStatus = httpStatus;
    }

    public HttpStatus getStatus() {
        return httpStatus;
    }
}
