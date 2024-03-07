package com.example.jobala.scrap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import static org.junit.jupiter.api.Assertions.*;

@Import(ScrapRepository.class)
@DataJpaTest
class ScrapRepositoryTest {

    @Test
    void findById() {
        // given

        // when

        // then

    }

    @Test
    void findCompScrapByIds() {
    }

    @Test
    void compScrapSave() {
    }

    @Test
    void compScrapDelete() {
    }
}