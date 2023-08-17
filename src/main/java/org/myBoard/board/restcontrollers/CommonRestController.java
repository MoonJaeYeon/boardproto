package org.myBoard.board.restcontrollers;

import org.myBoard.board.commons.CommonException;
import org.myBoard.board.commons.rests.JSONData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice("org.myBoard.board.restcontrollers")
public class CommonRestController {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONData<Object>> errorHandler(Exception e) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 기본값
        if (e instanceof CommonException) { // e가 있을 때
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus();
        }

        JSONData<Object> jsonData = JSONData.builder()
                .success(false) // 기본값 false
                .message(e.getMessage())
                .status(status)
                .build();

        return ResponseEntity.status(status).body(jsonData);
    }
}
