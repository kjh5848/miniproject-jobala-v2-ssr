package com.example.jobala.board;

import com.example.jobala._user.User;
import jakarta.persistence.EntityManager;
import net.bytebuddy.TypeCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardJPARepositoryTest {
    //기본
    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findAll_test(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        //when
        List<Board> boardList = boardJPARepository.findAll(sort);

        //then
        System.out.println("findAll_test : " +boardList);

    }

    //findById
    @Test
    public void findById_test(){
    //given
        int id = 1;
    //when
        Optional<Board> boardOP = boardJPARepository.findById(id);

        if(boardOP.isPresent()){
            Board board = boardOP.get();
            System.out.println("findById_test : "+board.getTitle());
        }


    //then
    }

    @Test
    public void save_test(){
        //given
        User sessionUser = User.builder().id(1).build();
        Board board = Board.builder()
                .title("hi")
                .content("bi")
                .user(sessionUser)
                .build();
        //when
        boardJPARepository.save(board);


        //then
        System.out.println("save_test : id : "+board.getId());
    }

}