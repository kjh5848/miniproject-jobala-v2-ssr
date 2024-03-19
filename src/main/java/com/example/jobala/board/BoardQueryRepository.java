package com.example.jobala.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardQueryRepository {
    private final EntityManager em;

    @Transactional
    public void save(BoardRequest.SaveDTO requestDTO, int userId) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, user_id, created_at) values(?,?,?, now())");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, userId);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById(int id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1, id);
        query.executeUpdate();
    }

    @Transactional
    public void update(BoardRequest.UpdateDTO requestDTO, int id) {
        Query query = em.createNativeQuery("update board_tb set title=?, content=? where id = ?");
        query.setParameter(1, requestDTO.getTitle());
        query.setParameter(2, requestDTO.getContent());
        query.setParameter(3, id);

        query.executeUpdate();
    }

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

    public List<BoardResponse.MainDetailDTO> findAllWithUser() {
        String q = """
                select bt.id, bt.user_id, bt.title, bt.created_at, ut.username from board_tb bt 
                inner join user_tb ut on bt.user_id = ut.id order by bt.id DESC
                """;
        Query query = em.createNativeQuery(q);
        // qlrm
        JpaResultMapper rm = new JpaResultMapper();
        List<BoardResponse.MainDetailDTO> respDTO = rm.list(query, BoardResponse.MainDetailDTO.class);
        return respDTO;
    }
}