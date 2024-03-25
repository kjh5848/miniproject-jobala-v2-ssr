package com.example.jobala.apply;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Import(ApplyQueryRepository.class)
@DataJpaTest
class ApplyQueryRepositoryTest {

    @Autowired
    private ApplyQueryRepository applyQueryRepository;

    @Test
        public void findApplyCompByUserId_test(){
            // given
          int id = 1;
            // when
        List<ApplyResponse.ApplyDTO> applyDTOList = applyQueryRepository.findApplyCompByUserId(id);
        applyDTOList.forEach(System.out::println);
            // then

        }
}