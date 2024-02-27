package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CompController {

    @GetMapping("/comp/joinForm")
    public String joinForm() {
        return "/comp/joinForm";
    }

    @GetMapping("/comp/noticeManage01")
    public String noticeManage01() {
        return "/comp/noticeManage01";
    }

    @GetMapping("/comp/noticeManage02")
    public String noticeManage02() {
        return "/comp/noticeManage02";
    }

    @GetMapping("/comp/noticeDetail3")
    public String noticeDetail3() {
        return "/comp/noticeDetail3";
    }

    @GetMapping("/comp/resumeDetail")
    public String resumeDetail() {
        return "/comp/resumeDetail";
    }

    @GetMapping("/comp/resumeDetail2")
    public String resumeDetail2() {
        return "/comp/resumeDetail2";
    }

    @GetMapping("/comp/mainForm")
    public String compMain() {
        return "/comp/mainForm";
    }

    @GetMapping("/comp/Mypage")
    public String compMypage() {
        return "/comp/MypageForm";
    }

    @GetMapping("/comp/supportManagement")
    public String supportManagement() {
        return "/comp/supportManagement";
    }

    @GetMapping("/comp/employmentAd")
    public String employmentAddForm() {
        return "/comp/employmentAd";
    }

    @GetMapping("/comp/logout")
    public String logout() {
        return "index";
    }

    @GetMapping("/comp/employmentAdForm")
    public String zz() {
        return "/comp/employmentAdForm";
    }
}