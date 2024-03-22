package com.example.jobala._user;

import com.example.jobala.Pic.PicQueryRepository;
import com.example.jobala.Pic.PicRequest;
import com.example.jobala._core.utill.ApiUtil;
import com.example.jobala.jobopen.JobopenQueryRepository;
import com.example.jobala.jobopen.JobopenResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.jobala._core.errors.exception.Exception401;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserQueryRepository userRepository;
    private final JobopenQueryRepository jobopenRepository;
    private final PicQueryRepository picRepository;
    private final HttpSession session;


    @GetMapping("/")
    public String mainForm(HttpServletRequest req, PicRequest.UploadDTO reqDTO) {
        List<JobopenResponse.ListDTO> jobopenList = userRepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "index";
    }

    // DEL: mainForm 삭제

    // TODO: loginFrorm 삭제예정
    @GetMapping("/loginForm")
    public String loginForm() {
        return "/user/loginForm";
    }



    //서비스 변경 완료
    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
        try {
            // userRepository에서 username과 password를 사용하여 사용자 검색
            User sessionUser = userService.로그인(reqDTO);
            session.setAttribute("sessionUser",sessionUser);
            // 권한 체크
            Boolean isCheck = false;
            if (sessionUser.getRole() == 0) {
                isCheck = true;
            }

            session.setAttribute("isCheck", isCheck);
            session.setAttribute("sessionUser", sessionUser);

            return "redirect:/";
        } catch (EmptyResultDataAccessException e) {
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }
    }

    //TODO: joinForm추후 삭제예정
    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO, HttpServletRequest req) {
        User user = userService.회원가입(reqDTO);
        req.setAttribute("user" ,user);
        return "/user/loginForm";
    }

    @GetMapping("/api/username-same-check")
    public @ResponseBody ApiUtil<?> usernameSameCheck(String username) {
        Optional<User> user = userService.중복체크(username);
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
 