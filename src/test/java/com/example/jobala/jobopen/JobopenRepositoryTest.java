package com.example.jobala.jobopen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;


@Import(JobopenRepository.class) // 내가 만든 클래스는 import 해줘야 함.
@DataJpaTest // DB 관련 객체들이 IoC에 뜬다.
class JobopenRepositoryTest {

    @Autowired // Test에서 DI 하는 코드
    private JobopenRepository jobopenRepository;


    @Test
    public void findByIdWithUser() {
        int id = 1;
        Jobopen jobopen = jobopenRepository.findByIdWithUser(2);
        System.out.println("jobopen = " + jobopen);
    }


    @Test
    public void findAll() {
        int idx = 1;
        //List<Jobopen> jobopenList = jobopenRepository.findAll();
        // System.out.println("jobopenList = " + jobopenList);

    }
}