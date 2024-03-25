package com.example.jobala._user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

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

        @Test
            public void findById_test(){
                // given
            int id = 1;
                // when
            Optional<User> user = userJPARepository.findById(id);
            System.out.println("user tt = " + user);
                // then

            }

}