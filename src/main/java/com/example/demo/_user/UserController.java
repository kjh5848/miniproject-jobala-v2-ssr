package com.example.demo._user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {


    @GetMapping("/user/loginForm")
    public String loginForm() {
        return "/layout/loginForm";
    }

    @GetMapping("/user/searchEmployAd")
    public String as() {
        return "/user/searchEmployAd";
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/user/mypage")
    public String userMypage() {
        return "/user/mypage";
    }

    @GetMapping("/user/resume")
    public String resume() {
        return "/user/resume";
    }

    @GetMapping("/user/resumeWrite")
    public String resumeWrite() {
        return "/user/resumeWrite";
    }

    @GetMapping("/user/resume2")
    public String resume2() {
        return "/user/resume2";
    }

    @GetMapping("/user/clippedCompany")
    public String clippedCompany() {
        return "/user/clippedCompany";
    }

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
        return "compMyPageAd";
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
