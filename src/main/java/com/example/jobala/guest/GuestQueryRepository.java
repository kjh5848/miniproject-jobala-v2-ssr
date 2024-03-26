package com.example.jobala.guest;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.qlrm.mapper.JpaResultMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class GuestQueryRepository {
    private final EntityManager em;


    public List<Resume> findResumeById(int userId) {
        Query query = em.createNativeQuery("select * from resume_tb where user_id = ? order by id desc", Resume.class);
        query.setParameter(1, userId);

        List<Resume> resumeList = query.getResultList();
        return resumeList;
    }
}