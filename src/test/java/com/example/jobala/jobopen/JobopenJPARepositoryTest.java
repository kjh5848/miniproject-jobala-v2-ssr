package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@DataJpaTest
class JobopenJPARepositoryTest {

    @Autowired
    private JobopenJPARepository jobopenJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void skill_test(){
        // given
        String skillName = "[Java]";

        // when
        List<Jobopen> jobopenList = jobopenJPARepository.findAll();
        String skills = String.valueOf(jobopenList.stream().map(jobopen -> jobopen.getSkills().equals(skillName)).toList());
        System.out.println("skills = " + skills);

        // then

    }

    @Test
    public void 공고수정_test(){
        //given
        int id = 1;

        Jobopen jobopen = jobopenJPARepository.findById(id).get();
        // UpdateDTO 인스턴스 생성

        // 각 속성 설정
        String jobopenTtile = "몰라";
        String career = "Mid-level";
        String edu = "Bachelor's degree";
        String jobType = "Full-time";
        String salary = "100000";
        String hopeJob = "Software Engineer";
        String compLocation = "Seoul";
        String endTime = "2024-04-01";
        List<String> skills = Arrays.asList("스킬1" ,"스킬2", "스킬3");
        JobopenRequest.UpdateDTO reqDTO = new JobopenRequest.UpdateDTO(jobopenTtile,career,edu,jobType,salary,hopeJob,compLocation,endTime,skills);
        // UpdateDTO 인스턴스에 각 속성 설정

        jobopen.setJobopenUpdate(reqDTO);

        // 각 속성 값이 올바르게 설정되었는지 확인
        Jobopen jobopen2 = jobopenJPARepository.findById(id).get();

//        System.out.println("jobopen2 = " + jobopen2);
        //when
        //then
    }

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
        JobopenRequest.SaveDTO reqDTO = new JobopenRequest.SaveDTO(edu, jobopenTitle, career, jobType, salary, hopeJob, compLocation, endTime, skills);
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
        em.flush();

        // then

    }

//    @Test
//    public void findByIdWithUser_test() {
//        // given
//        int jobopenId = 9;
//
//        // when
//        Optional<Jobopen> jobopenOP = jobopenJPARepository.findByIdWithUser(jobopenId);
//
//        // then
//        System.out.println("findByIdWithUser_test: " + jobopenOP);
//    }
}