package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class CompJPARepositoryTest {

    @Autowired
    private  CompJPARepository compJPARepository;


    @Test
    public void findByUserIdOrderByDesc_test() {
    // given
    Integer userID = 1;
    //when
    List<Jobopen> jobopens = compJPARepository.findByUserIdOrderByDesc(userID);
        System.out.println(jobopens);
    //then

    }
}
