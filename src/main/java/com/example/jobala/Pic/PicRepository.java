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
    public void resumeUpload(String title, String imgFilename){
        Query query = em. createNativeQuery("select max(id+1) from resume_tb");
        Integer resumeId = (Integer) query.getSingleResult();

        Query query2 = em.createNativeQuery("insert into pic_tb(title, img_filename, resume_id) values(?,?,?)");
        query2.setParameter(1, title);
        query2.setParameter(2, imgFilename);
        query2.setParameter(3, resumeId);
        query2.executeUpdate();
    }

    public Pic guestFindByPic(int id){
        Query query = em.createNativeQuery("SELECT * FROM pic_tb WHERE resume_id = ?", Pic.class);
        query.setParameter(1, id);
        System.out.println(id);
        return (Pic) query.getSingleResult();
    }

    public Pic compFindByPic(int id){
        Query query = em.createNativeQuery("SELECT * FROM pic_tb WHERE jobopen_id = ?", Pic.class);
        query.setParameter(1, id);
        System.out.println(id);
        return (Pic) query.getSingleResult();
    }

    @Transactional
    public void resumeUpdate(String title, String imgFilename, int id) {
        Query query2 = em.createNativeQuery("UPDATE pic_tb SET title = ?, img_filename = ? WHERE resume_id = ?;");
        query2.setParameter(1, title);
        query2.setParameter(2, imgFilename);
        query2.setParameter(3, id);
        query2.executeUpdate();
    }
}