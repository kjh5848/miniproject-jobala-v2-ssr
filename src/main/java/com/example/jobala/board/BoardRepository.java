package com.example.jobala.board;

import com.example.jobala.comp.CompResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

    public BoardResponse.BoardDetailDTO findById(int id) {
        String q = """
               select b.id, b.title, b.content, b.user_id, u.username 
               from board_tb b 
               inner join user_tb u on b.user_id = u.id 
               where b.id = ?
               """;
        Query query = em.createNativeQuery(q);
        query.setParameter(1, id);

        JpaResultMapper rm = new JpaResultMapper();
        BoardResponse.BoardDetailDTO responseDTO = rm.uniqueResult(query, BoardResponse.BoardDetailDTO.class);
        return responseDTO;
    }



    public List<BoardResponse.DetailDTO> findAllWithUser() {
        String q = """
                select bt.id, bt.title, bt.created_at, ut.username from board_tb bt 
                inner join user_tb ut on bt.user_id = ut.id
                """;
        Query query = em.createNativeQuery(q);
        // qlrm
        JpaResultMapper rm = new JpaResultMapper();
        List<BoardResponse.DetailDTO> respDTO = rm.list(query, BoardResponse.DetailDTO.class);
        return respDTO;
    }
}
