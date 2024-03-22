package com.example.jobala.jobopen;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

<<<<<<< HEAD
import java.util.Optional;

=======
>>>>>>> fd8935b0c41ffa214c2675d5003f7dfc4d84936f
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class JobopenJPARepositoryTest {

    @Autowired
    private JobopenJPARepository jobopenJPARepository;

    @Test
<<<<<<< HEAD
    public void findByIdWithUser_test(){
        // given
        int jobopenId = 9;

        // when
        Optional<Jobopen> jobopenOP = jobopenJPARepository.findByIdWithUser(jobopenId);

        // then
        System.out.println("findByIdWithUser_test: "+ jobopenOP);
    }
=======
    public void deleteById_test(){
        // given
        int id = 1;

        // when
        jobopenJPARepository.deleteById(id);

        // then

    }

>>>>>>> fd8935b0c41ffa214c2675d5003f7dfc4d84936f
}