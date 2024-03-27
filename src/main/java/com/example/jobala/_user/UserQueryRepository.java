package com.example.jobala._user;

import com.example.jobala.jobopen.JobopenResponse;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserQueryRepository {
    private final EntityManager em;


    public List<JobopenResponse.ListDTO> findAll() {
        String q = """
                select jb.id, jb.jobopen_title, jb.comp_location, jb.career, jb.edu 
                from jobopen_tb jb order by id desc;              
                """;
        Query query = em.createNativeQuery(q);

        JpaResultMapper rm = new JpaResultMapper();
        List<JobopenResponse.ListDTO> jobopenList = rm.list(query, JobopenResponse.ListDTO.class);
        return jobopenList;
    }

    public User findById(int id) {
        String q = """
                select * from user_tb where id = ?
                """;
        Query query = em.createNativeQuery(q,User.class);
        query.setParameter(1, id);
        User user = (User) query.getSingleResult();
        return user;
    }
}
