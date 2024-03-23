package com.example.jobala.apply;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplyJPARepositoryTest {

    @Autowired
    private ApplyJPARepository applyJPARepository;

    @Autowired
    private EntityManager em;

    @Test
     public void _test(){
         // given

         // when

         // then
     }
}