package com.example.jobala._core.handler;

import com.example.jobala._core.utill.Script;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice // 비정상적일때 처리하는 응답 에러 컨트롤러 -> view(파일)를 리턴 -> @ResponseBody붙이면 됨
// @RestControllerAdvice : 응답을 직접 반환
// @Controller -> @ResponseBody붙이면 됨
public class CustomExceptionHandler {

    // 어떤 에러가 나타날때 처리할지 -> 분기해야함
    @ExceptionHandler(Exception.class)
    public @ResponseBody String error1(Exception e) {
        return Script.back(e.getMessage());
    }
}