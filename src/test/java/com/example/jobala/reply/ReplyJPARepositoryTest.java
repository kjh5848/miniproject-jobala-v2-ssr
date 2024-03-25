package com.example.jobala.reply;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class ReplyJPARepositoryTest {
    @Autowired
    private ReplyJPARepository replyJPARepository;
    @Autowired
    private EntityManager em;

    @Test
    public void deleteById(){

            //given
            int id = 1;

            //when
            replyJPARepository.deleteById(id);

            //then
            em.flush();


    }


    @Test
    public void findById_test(){
        //given
        int id = 1;
        //when
        Optional<Reply> replyOP = replyJPARepository.findById(id);

        if(replyOP.isPresent()){
            Reply reply = replyOP.get();
            System.out.println("findById_test : "+reply.getBoard().getTitle());
        }


        //then
    }

    //save
//    @Test
//    public void save_test(){
//        // given
//
//       User sessionUser = new User();
//
//        Reply reply = Reply.builder()
//                .
//                .build();
//
//        // when
//        replyJPARepository.save();
//
//        // then
//        System.out.println("save_test : "+ board.getId());

//    }

}