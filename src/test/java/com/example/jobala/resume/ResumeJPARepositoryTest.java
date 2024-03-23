package com.example.jobala.resume;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@DataJpaTest
class ResumeJPARepositoryTest {

    @Autowired
    private ResumeJPARepository resumeJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void save_test() {
        // given
        String resumeTitle = "이력서 제목 예시";
        String hopeJob = "희망 직무 예시";
        String career = "경력 내용 예시";
        String license = "자격증 내용 예시";
        String content = "추가 내용 예시";
        String edu = "학력 내용 예시";

        // 더미 스킬 데이터 생성 및 추가
        List<String> skills = Arrays.asList( "스킬1", "스킬2", "스킬3" );

        User user = User.builder()
                .id(1)
                .name("이름")
                .email("이메일")
                .phone("폰")
                .address("주소")
                .age(Date.valueOf("2001-01-01"))
                .password("1234")
                .username("유저네임")
                .build();

        ResumeRequest.SaveDTO reqDTO = new ResumeRequest.SaveDTO(
                resumeTitle, hopeJob, career, license, content, edu, skills
        );

        // when
        Resume resume = resumeJPARepository.save(reqDTO.toEntity(user));
        System.out.println("resume = " + resume);

        // then
    }

    @Test
    public void deleteById_test() {
        // given
        int resumeId = 1;

        // when
        resumeJPARepository.deleteById(resumeId);
        em.flush();

        // then
        System.out.println("deleteById_test: 삭제 성공");
    }
}