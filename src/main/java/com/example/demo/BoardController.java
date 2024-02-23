package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;

public class BoardController {

    @GetMapping("/")
    public String index(){

        return "/";
    }
}
