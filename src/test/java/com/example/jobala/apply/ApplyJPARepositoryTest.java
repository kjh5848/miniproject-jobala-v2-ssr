package com.example.jobala.apply;

import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ApplyJPARepositoryTest {

    @Autowired
    private ApplyJPARepository applyJPARepository;

    @Autowired
    private EntityManager em;

    @Test
        public void findByUserId_test(){
            // given
            int id = 1;
            // when
        List<Apply> applies = applyJPARepository.findApplyCompByUserId(id);
        applies.forEach(System.out::println);

            // then

        }

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
    public void countJobopenApplyById_test() {

        int jobopenId = 1;

        int count = applyJPARepository.countJobopenApplyById(jobopenId);

        int expectedCount = 1;
        assertThat(count).isEqualTo(expectedCount);

        System.out.println("예상 count " + expectedCount + " 실제 count " + count);
    }
}