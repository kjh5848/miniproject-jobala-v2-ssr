package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobopenJPARepositoryTest {

    @Autowired
    private JobopenJPARepository jobopenJPARepository;

    @Test
    public void 공고등록_test() {
        // given
        String edu = "고등학교 졸업";
        String jobopenTitle = "테스트타이틀";
        String career = "신입";
        String jobType = "정규직";
        String salary = "3000만원 이상";
        String hopeJob = "프론트엔드";
        String compLocation = "서울";
        Date endTime = Date.valueOf("2024-03-28");
        String content = null;
        List<String> skills = new ArrayList<>();
        skills.add("Spring");
        skills.add("HTML");
        skills.add("jQuery");
        JobopenRequest.SaveDTO reqDTO = new JobopenRequest.SaveDTO(edu, jobopenTitle, career, jobType, salary, hopeJob, compLocation, content, endTime, skills);
        User sessionUser = User.builder()
                .id(1)
                .compNum("827-546-7895")
                .ceo("이병헌")
                .compname("쿠팡")
                .address("서울특별시 강남구")
                .username("com1")
                .email("actor1@nate.com")
                .password("1234")
                .name("이병헌")
                .phone("01011112223")
                .role(1)
                .build();
        // when
        jobopenJPARepository.save(reqDTO.toEntity(sessionUser));


    }

    @Test
    public void deleteById_test() {
        // given
        int id = 1;

        // when
        jobopenJPARepository.deleteById(id);


        // then

    }
}