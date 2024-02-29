package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/")
    public String boardMain() {
        return "/board/mainForm";
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "/user/loginForm";
    }

}
