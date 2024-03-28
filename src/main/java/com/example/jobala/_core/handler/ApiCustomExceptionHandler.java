package com.example.jobala._core.handler;
import com.example.jobala._core.utill.ApiUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

//RuntimeException이 터지면 해당 파일로 오류가 모인다.
//@RestControllerAdvice
public class ApiCustomExceptionHandler {

    //@ResponseStatus(HttpStatus.BAD_REQUEST)

    public ResponseEntity<?> ex400(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(400,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.BAD_REQUEST); // http body, header
    }


    public ResponseEntity<?> ex401(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(401,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.UNAUTHORIZED ); // http body, header
    }


    public ResponseEntity<?> ex403(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(403,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.FORBIDDEN ); // http body, header
    }



    public ResponseEntity<?> ex404(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(404,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.NOT_FOUND); // http body, header
    }


    public ResponseEntity<?> ex500(RuntimeException e) {
        ApiUtil<?> apiUtil = new ApiUtil<>(500,e.getMessage());// http body -> 구성한 객체
        return new ResponseEntity<>(apiUtil, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}