package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala.guest.GuestResponse;
import com.example.jobala.resume.Resume;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class JobopenQueryRepository {
    private final EntityManager em;

    public List<Resume> findResumeById(User user) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, user.getId());

        return query.getResultList();

    }

    public List<Jobopen> findJobopenById(User user) {
        Query query = em.createNativeQuery("select * from jobopen_tb where user_id = ? order by id desc", Jobopen.class);
        query.setParameter(1, user.getId());

        return query.getResultList();
    }

}
