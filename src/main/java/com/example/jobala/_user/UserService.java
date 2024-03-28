package com.example.jobala._user;

import com.example.jobala._core.errors.exception.Exception400;
import com.example.jobala._core.errors.exception.Exception401;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JobopenJPARepository jobopenJPARepository;
    private final UserJPARepository userJPARepository;

    // 로그인
    public User login(UserRequest.LoginDTO reqDTO) {
        return userJPARepository.findByUsernameAndPassword(reqDTO.getUsername(), reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
    }

    // 회원가입
    @Transactional
    public User join(UserRequest.JoinDTO reqDTO) {
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

    // 중복체크
    public Optional<User> usernameSameCheck(String username) {
        return userJPARepository.findByUsername(username);
    }

    public User guestInfo(Integer id) {
        return userJPARepository.findById(id).orElseThrow(() -> new Exception404("유저의 정보를 찾을 수 없습니다."));
    }

}
