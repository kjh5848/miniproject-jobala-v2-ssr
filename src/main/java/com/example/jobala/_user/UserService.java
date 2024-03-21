package com.example.jobala._user;

import com.example.jobala._core.errors.exception.Exception401;
import com.example.jobala._core.errors.exception.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserJPARepository userJPARepository;

    public User 로그인(UserRequest.loginDTO reqDTO) {
        return userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
    }

    @Transactional
    public void 회원가입(UserRequest.joinDTO reqDTO, Integer sessionUserId) {
        User user = userJPARepository.findById(sessionUserId)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));
        user.setPassword(reqDTO.getPassword());
        user.setEmail(reqDTO.getEmail());
    }

    public Optional<User> 중복체크(String username) {
        return userJPARepository.findByUsername(username);
    }

}
