package com.example.jobala.resume;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class ResumeJPARepositoryTest {

    @Autowired
    private ResumeJPARepository resumeJPARepository;

    @Autowired
    private EntityManager em;

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