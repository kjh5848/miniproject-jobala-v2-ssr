package com.example.jobala._user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserJPARepositoryTest {
    @Autowired
    private UserJPARepository userJPARepository;

    @Test
        public void findAll_test(){
            // given

            // when
        List<User> userList = userJPARepository.findAll();
        userList.forEach(System.out::println);
            // then


        }

}