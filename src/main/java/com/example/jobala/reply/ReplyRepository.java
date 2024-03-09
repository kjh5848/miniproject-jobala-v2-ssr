package com.example.jobala.reply;

import com.example.jobala._user.User;
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

    public Reply findById(int id) {
        String q = "select * from reply_tb where id = ?";
        Query query = em.createNativeQuery(q, Reply.class);
        query.setParameter(1, id);

        try {
            return (Reply) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }

    }

    @Transactional
    public void deleteById(int id) {
        String q = "delete from reply_tb where id = ?";
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        query.executeUpdate();
    }


    @Transactional
    public List<ReplyResponse.ReplyListDTO> replyList(int boardId) {
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

    @Transactional
    public void save(ReplyRequest.WriteDTO reqDTO, User sessionUser) {
        Query query = em.createNativeQuery("insert into reply_tb(comment, board_id, user_id, created_at) values(?,?,?, now())");
        query.setParameter(1, reqDTO.getComment());
        query.setParameter(2, reqDTO.getBoardId());
        query.setParameter(3, sessionUser.getId());

        query.executeUpdate();
    }

    public List<ReplyResponse.ReplyDTO> findByBoardId(int boardId, User sessionUser) {
        String a = """
                select rt.id, rt.user_id, rt.comment, ut.username from reply_tb rt inner join user_tb ut on rt.user_id = ut.id where rt.board_id = ? 
                """;
        Query query = em.createNativeQuery(a);
        query.setParameter(1, boardId);
        List<Object[]> obs = query.getResultList();

        return obs.stream().map(object -> new ReplyResponse.ReplyDTO(object, sessionUser)).toList();
    }
}
