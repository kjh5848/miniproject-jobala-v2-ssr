package com.example.jobala.user;

import com.example.jobala._user.User;
import com.example.jobala._user.UserRepository;
import com.example.jobala._user.UserRequest;
import jakarta.servlet.http.HttpSession;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserRepository.class) // IoC 등록코드
@DataJpaTest // Datasource(connection pool), EntityManager
public class UserRepositoryTest {

    @Autowired // DI
    private UserRepository userRepository;

    @Test
    public void findByUsername_test() {
        // given
        UserRequest.loginDTO reqDTO = new UserRequest.loginDTO();
        reqDTO.setUsername("com1");
        reqDTO.setPassword("1234");

        // when
        User user = userRepository.findByUsernameAndPassword(reqDTO);

        // then
        Assertions.assertThat(user.getUsername()).isEqualTo("com1");
    }
}