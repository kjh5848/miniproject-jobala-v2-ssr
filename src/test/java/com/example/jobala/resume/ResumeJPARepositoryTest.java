package com.example.jobala.resume;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.JobopenRequest;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@DataJpaTest
class ResumeJPARepositoryTest {

    @Autowired
    private ResumeJPARepository resumeJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByUserId_test(){
        // given
        int userId = 1;
        // when
        List<Resume> resume = resumeJPARepository.findByUserId(userId);

        System.out.println("findByUserIdJoinScrap_test : " + resume.size());

        // then

    }
    @Test
    public void 이력서스킬_test(){
        // given
        String skillName = "HTML";

        // when
        List<Resume> resumeList = resumeJPARepository.findAll();
        List<String> skillNames = resumeList.stream()
                .map(resume -> resume.getSkills())
                .toList();

        StringBuilder queryBuilder = new StringBuilder("select * from user_tb where skills like '%");
        for (int i = 0; i < skillNames.size(); i++) {
            String skills = skillNames.get(i);
            if (skills.contains(skillName) || skills.contains(",")) {
                queryBuilder.append(skills);
            }
        }
        String q2 = queryBuilder.toString();
        System.out.println("_tt" + q2);


//        List<String> skills = Arrays.stream(resume.getSkills().replaceAll("[\\[\\]\"]", "").split(",")).toList();

//        String skillsString = String.join(", ", skills);
//        System.out.println("skills _tt = \"" + skillsString + "\"");

        // then

    }

    @Test
    public void 스크랩한이력서보기_test(){
        // given
        int id = 10;
        // when
        List<ResumeResponse.ScrapDTO> resume = resumeJPARepository.findByUserIdJoinScrap(id);

        System.out.println("findByUserIdJoinScrap_test : " + resume);

        // then

    }

    @Test
        public void 이력서보기_test(){
            // given
        int id = 1;
            // when
        Resume resume = resumeJPARepository.findById(id).get();
        System.out.println("resume.getSkills() = " + resume.getSkills());

        List<String> skills = Arrays.stream(resume.getSkills().replaceAll("[\\[\\]\"]", "").split(",")).toList();

        String skillsString = String.join(", ", skills);
        System.out.println("skills _tt = \"" + skillsString + "\"");

        // then

        }

    @Test
    public void 이력서등록_test() {
        // given
        String hopeJob="백엔드";
        String edu = "고등학교 졸업";
        String resumeTitle = "테스트타이틀";
        String career = "신입";
        String license = "정보처리기사";
        String content = "그냥 뽑아줘";
        List<String> skills = new ArrayList<>();
        skills.add("Spring");
        skills.add("HTML");
        skills.add("jQuery");

        ResumeRequest.SaveDTO reqDTO = new ResumeRequest.SaveDTO(
                resumeTitle,
                hopeJob,
                career,
                license,
                content,
                edu,
                skills);
        User sessionUser = User.builder()
                .id(1)
                .address("서울특별시 강남구")
                .username("cos1")
                .email("actor1@nate.com")
                .password("1234")
                .name("이병헌")
                .phone("01011112223")
                .role(0)
                .build();
        // when
        resumeJPARepository.save(reqDTO.toEntity(sessionUser));

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