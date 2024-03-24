package com.example.jobala.apply;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplyJPARepositoryTest {

    @Autowired
    private ApplyJPARepository applyJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findById_test() {
        // given
        int id = 1;
        // when
        Optional<Apply> applyOP = applyJPARepository.findById(id);
        // then
        if (applyOP.isPresent()) {
            Apply apply = applyOP.get();
            System.out.println("findById_test : " + apply.getState());
        }
    }

    @Test
    public void 지원한모든목록조회_test() {
        // given

        // when

        // then
    }

    @Test
    public void 상태별지원목록조회_test() {
        // given

        // when

        // then
    }

    @Test
    public void 제안받은공고조회_test() {
        // given

        // when

        // then
    }
}