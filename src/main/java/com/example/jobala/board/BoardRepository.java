package com.example.jobala.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final EntityManager em;

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
