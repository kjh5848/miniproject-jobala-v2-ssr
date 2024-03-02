package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
 