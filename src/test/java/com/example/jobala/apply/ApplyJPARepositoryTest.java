package com.example.jobala.apply;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;
import java.util.function.IntToDoubleFunction;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplyJPARepositoryTest {

    @Autowired
    private ApplyJPARepository applyJPARepository;

    @Autowired
    private JobopenJPARepository jobopenJPARepository;
    @Autowired
    private ResumeJPARepository resumeJPARepository;
    @Autowired
    private EntityManager em;

    @Test
        public void 지원후저장_test(){
            // given
        Integer jobopenId = 1;
        Integer resumeId = 1;
        Integer userId =1;
        String status = "검토중";

        Jobopen jobopen = jobopenJPARepository.findById(jobopenId).get();
        Resume resume = resumeJPARepository.findById(resumeId).get();

        ApplyRequest.ApplyRequestDTO reqDTO = new ApplyRequest.ApplyRequestDTO( resumeId,jobopenId,userId,status
        );

        User sessionUser = User.builder()
                .id(1)
                .address("서울특별시 강남구")
                .username("cos1")
                .email("actor1@nate.com")
                .password("1234")
                .name("김주혁")
                .phone("01011112223")
                .role(0)
                .build();

        // when
        Apply apply = applyJPARepository.save(reqDTO.toEntity(resume, jobopen, sessionUser));
        System.out.println("apply tt = " + apply);
            // then
        }

    @Test
        public void findAll_test(){
            // given

            // when
        List<Apply> applies = applyJPARepository.findAll();

            // then
        System.out.println("applies tt = " + applies);
        }

    @Test
    public void findById(){
        // given
      int id = 1;
        // when
    Apply apply = applyJPARepository.findById(id).get();

        // then
    System.out.println("apply tt = " + apply);
    }

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Apply> applyOP = applyJPARepository.findById(id);
        // then
        if (applyOP.isPresent()) {
            Apply apply = applyOP.get();
            System.out.println("findById_test : " + apply.getState());
        }
    }

    @Test
    public void countJobopenApplyById_test() {

        int jobopenId = 1;

        int count = applyJPARepository.countJobopenApplyById(jobopenId);

        int expectedCount = 1;
        assertThat(count).isEqualTo(expectedCount);

        System.out.println("예상 count " + expectedCount + " 실제 count " + count);
    }
}