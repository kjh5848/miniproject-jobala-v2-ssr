package com.example.jobala.resume;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(ResumeQueryRepository.class)
@DataJpaTest
class ResumeQueryRepositoryTest {

    @Autowired
    private ResumeQueryRepository resumeQueryRepository;

    @Test
    public void findByResumeTitleLike_test() {
        String resumeTitle = "프론트";

//        List<Resume> resumeList = resumeQueryRepository.findByResumeTitleLike(resumeTitle);

//        System.out.println("findByResumeTitleLike_test : "+resumeList.size());
    }
}