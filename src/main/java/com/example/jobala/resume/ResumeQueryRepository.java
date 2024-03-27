package com.example.jobala.resume;

import com.example.jobala._user.User;
import com.google.gson.Gson;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ResumeQueryRepository {
    private final EntityManager em;

    public List<ResumeResponse.ListDTO> findByResumeTitleLike(String resumeTitle) {
        Query query = em.createQuery("SELECT b FROM Resume b WHERE b.resumeTitle LIKE :resumeTitle");
        query.setParameter("resumeTitle", "%"+resumeTitle+"%");

        List<Resume> resumeList = query.getResultList();
        return resumeList.stream().map(resume -> new ResumeResponse.ListDTO(resume)).toList();
    }

}