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
import org.springframework.web.bind.annotation.ResponseBody;
import com.example.jobala._core.errors.exception.Exception401;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserQueryRepository userRepository;
    private final JobopenQueryRepository jobopenRepository;
    private final PicQueryRepository picRepository;
    private final UserJPARepository userJPARepository;
    private final HttpSession session;

    @GetMapping("/")
    public String mainForm(HttpServletRequest req, PicRequest.UploadDTO reqDTO) {
        List<JobopenResponse.ListDTO> jobopenList = userRepository.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "index";
    }

    // DEL: mainForm 삭제

    // DEL: loginFrorm 삭제

    @PostMapping("/login")
    public String login(UserRequest.loginDTO reqDTO) {
        // 유효성 검사
        if (reqDTO.getUsername().length() > 15) {
            // 유효하지 않은 경우 에러 페이지나 로그인 폼으로 리다이렉션
            return "redirect:/user/loginForm";
        }

        try {
            // userRepository에서 username과 password를 사용하여 사용자 검색
            User user = userRepository.findByUsernameAndPassword(reqDTO);

            // 권한 체크
            Boolean isCheck = false;
            if (user.getRole() == 0) { // 가정: 역할 0이 개인 사용자
                isCheck = true;
            }

            // 세션에 사용자 정보 및 권한 체크 결과 설정
            session.setAttribute("isCheck", isCheck);
            session.setAttribute("sessionUser", user);

            // 로그인 성공 후 리다이렉트
            return "redirect:/";
        } catch (EmptyResultDataAccessException e) {
            // username 또는 password가 잘못된 경우 예외 처리
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "/user/joinForm";
    }

    @PostMapping("/join")
    public String join(UserRequest.joinDTO reqDTO) {
        System.out.println(reqDTO);

        //     개인 회원가입

        if (reqDTO.getCompname() == null) {
            userRepository.userSave(reqDTO);

            return "/user/loginForm";
        }

        //      기업 회원가입
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
 