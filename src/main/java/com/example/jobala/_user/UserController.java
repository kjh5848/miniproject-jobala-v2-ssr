package com.example.jobala._user;

import com.example.jobala._core.util.ApiUtil;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final JobopenRepository jobopenRepository;

    private final HttpSession session;

    @GetMapping("/")
    public String mainForm(HttpServletRequest req) {
        List<Jobopen> jobopenList = userRepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "index";
    }

    // DEL: mainForm 삭제

    @GetMapping("/loginForm")
    public String loginForm() {

        return "/user/loginForm";
    }

    @PostMapping("/login")
    public String login(UserRequst.loginDTO reqDTO) {
        System.out.println(reqDTO);

        //유효성 검사(아이디가 15자 이상일때)       `
        if (reqDTO.getUsername().length() > 15) {
            System.out.println(1);
            return "/user/joinForm";
        }
        User user = userRepository.findByUsernameAndPassword(reqDTO);

        //권한체크(user=null 경우)
        if (user == null) {
            return "/user/joinForm";
        }

        //권한체크(개인 로그인 했을때, 기업 로그인 했을 때 nav바 다르게 노출)
        Boolean isCheck = false;
        if (user.getRole() == 0) {
            isCheck = true;

            session.setAttribute("isCheck", isCheck);
            session.setAttribute("sessionUser", user);
            return "redirect:/";
        }

        session.setAttribute("isCheck", isCheck);
        session.setAttribute("sessionUser", user);
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
        if (reqDTO.getCompname() == null) {
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
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) { // 회원가입 해도 된다.
            return new ApiUtil<>(true);
        } else { // 회원가입 하면 안된다.
            return new ApiUtil<>(false);
        }
    }


    @GetMapping("/logout")
    public String logout() {
        session.invalidate();

        return "redirect:/";
    }

}
 