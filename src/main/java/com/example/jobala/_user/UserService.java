package com.example.jobala._user;

import com.example.jobala._core.errors.exception.Exception400;
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

    public User 로그인(UserRequest.LoginDTO reqDTO) {
        return userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
    }

    @Transactional
    public User 회원가입(UserRequest.JoinDTO reqDTO) {
        Optional<User> userOP = userJPARepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) {
            throw new Exception400("중복된 유저네임입니다.");
        }
        User user = null;
        if (reqDTO.getRole() == 1) {
            user = userJPARepository.save(reqDTO.toCompEntity());
        } else if (reqDTO.getRole() == 0) {
            user = userJPARepository.save(reqDTO.toGuestEntity());
        }
        return user;
    }

    public Optional<User> 중복체크(String username) {
        return userJPARepository.findByUsername(username);
    }

}
