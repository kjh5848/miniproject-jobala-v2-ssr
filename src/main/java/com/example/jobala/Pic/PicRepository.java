package com.example.jobala.Pic;

import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PicRepository {
    private final EntityManager em;

    @Transactional
    public void upload(String title, String imgFilename){
        Query query = em. createNativeQuery("select max(id) from resume_tb");
        Integer resumeId = (Integer) query.getSingleResult();

        Query query2 = em.createNativeQuery("insert into pic_tb(title, img_filename, resume_id) values(?,?,?)");
        query2.setParameter(1, title);
        query2.setParameter(2, imgFilename);
        query2.setParameter(3, resumeId);
        query2.executeUpdate();
    }

    public Pic findById(int id){
        Query query = em.createNativeQuery("select * from pic_tb where user_id = ?", Pic.class);
        query.setParameter(1, id);
        return (Pic) query.getSingleResult();
    }
}