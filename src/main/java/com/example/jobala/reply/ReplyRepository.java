package com.example.jobala.reply;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class ReplyRepository {
    private final EntityManager em;

    @Transactional
    public List<ReplyResponse.ReplyListDTO> replyList(int boardId){
        String q = """
                select bt.id, rt.username, rt.comment 
                from reply_tb rt 
                inner join board_tb bt on rt.board_id = bt.id 
                where bt.id = ?
                """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, boardId);
        JpaResultMapper rm = new JpaResultMapper();
        List<ReplyResponse.ReplyListDTO> respDTO = rm.list(query, ReplyResponse.ReplyListDTO.class);
        return respDTO;

    }
}
