package org.myBoard.board.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.myBoard.board.commons.CommonException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice("org.myBoard.board.controllers")
public class CommonController {

    @ExceptionHandler(Exception.class)
    public String errorHandler(Exception e, Model model, HttpServletRequest request, HttpServletResponse response) {

        int status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        if (e instanceof CommonException) {
            CommonException commonException = (CommonException) e;
            status = commonException.getStatus().value(); // 별도의 응답코드를 가져올 수 있게 함
        }

        response.setStatus(status);

        String URL = request.getRequestURI();
        System.out.println(URL);

        model.addAttribute("status", status);
        model.addAttribute("path", URL);
        model.addAttribute("message", e.getMessage());
        model.addAttribute("exception", e);

        e.printStackTrace();

        return "error/common";
    }
}
