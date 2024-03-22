package com.example.jobala.jobopen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobopenJPARepositoryTest {

    @Autowired
    private JobopenJPARepository jobopenJPARepository;

    @Test
    public void findByIdWithUser_test(){
        // given
        int jobopenId = 9;

        // when
        Optional<Jobopen> jobopenOP = jobopenJPARepository.findByIdWithUser(jobopenId);

        // then
        System.out.println("findByIdWithUser_test: "+ jobopenOP);
    }
}