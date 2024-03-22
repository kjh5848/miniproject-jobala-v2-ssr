package com.example.jobala.scrap;

import com.example.jobala.resume.ResumeJPARepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ScrapJPARepositoryTest {

    @Autowired
    private ScrapJPARepository scrapJPARepository;
    @Autowired
    private ResumeJPARepository resumeJPARepository;

    @Test
    public void 회사가스크랩_test() {

    }
}