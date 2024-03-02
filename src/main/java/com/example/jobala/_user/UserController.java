package com.example.jobala._user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;


    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(UserRequst.joinDTO reqDTO) {
        System.out.println(reqDTO);

//     개인 회원가입
       Integer isCheck;
       if (reqDTO.getCompname() == null){
           isCheck = 0;
           Integer role = isCheck;
           reqDTO.setRole(role);
           userRepository.userSave(reqDTO);

           return "/user/loginForm";
       }

//      기업 회원가입
        isCheck = 1;
        Integer role = isCheck;
        reqDTO.setRole(role);
        userRepository.compSave(reqDTO);


        return "/user/loginForm";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
 