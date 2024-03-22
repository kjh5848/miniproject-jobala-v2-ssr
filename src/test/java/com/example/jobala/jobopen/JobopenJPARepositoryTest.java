package com.example.jobala.jobopen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobopenJPARepositoryTest {

    @Autowired
    private JobopenJPARepository jobopenJPARepository;

    @Test
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        jobopenJPARepository.deleteById(id);

        // then

    }

}