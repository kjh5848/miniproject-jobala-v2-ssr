package com.example.jobala.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

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

}