package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class GuestController {


    @GetMapping("/guest/joinForm")
    public String joinForm(){
        return "/guest/joinForm";
    }




}
