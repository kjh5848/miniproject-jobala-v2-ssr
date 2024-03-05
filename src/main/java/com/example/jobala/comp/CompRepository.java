package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CompRepository {
    private final EntityManager em;

    public CompResponse.scoutListDTO scoutList(){
        String q = """
                SELECT ut.name, rt.resume_title, ut.age, ut.address, rt.career
                FROM resume_tb rt
                INNER JOIN user_tb ut ON rt.user_id = ut.id
                """;

        Query query = em.createNativeQuery(q);
        JpaResultMapper jpaResultMapper = new JpaResultMapper();



        return null;
    }

    public List<Jobopen> findAll() {
        String q = """
                select * from jobopen_tb order by id desc;              
                """;
        Query query = em.createNativeQuery(q, Jobopen.class);
        return query.getResultList();
    }

    public void findById() {
        return;
    }

    @Transactional
    public void save() {
        return;
    }

    @Transactional
    public void upDate() {
        return;
    }

    @Transactional
    public void delete() {
        return;
    }
}
