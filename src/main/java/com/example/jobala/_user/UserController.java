package com.example.jobala._user;

import com.example.jobala._core.util.ApiUtil;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;


    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String loign(UserRequst.loginDTO reqDTO){
        System.out.println(reqDTO);

        if (reqDTO.getUsername().length() < 15){
            return "/";
        }

        User user = userRepository.findByUsernameAndPassword(reqDTO);

        if (user == null){
            return "/";
        }else{
            session.setAttribute("sessionUser", user);
        }

        return "redirect:/";
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

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username){
        User user = userRepository.findByUsername(username);
        if(user == null){ // 회원가입 해도 된다.
            return new ApiUtil<>(true);
        }else{ // 회원가입 하면 안된다.
            return new ApiUtil<>(false);
        }
    }


    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }

}
 