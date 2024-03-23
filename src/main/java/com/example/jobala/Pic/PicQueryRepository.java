package com.example.jobala.Pic;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class PicQueryRepository {
    private final EntityManager em;

    public Pic resumeFindByPic(int id) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE resume_id = ?", Pic.class);
        query.setParameter(1, id);
        System.out.println(id);
        return (Pic) query.getSingleResult();
    }

    @Transactional
    public void resumeUpload(String title, String imgFilename) {
        Query query = em.createNativeQuery("select max(id+1) from resume_tb");
        Integer resumeId = (Integer) query.getSingleResult();

        Query query2 = em.createNativeQuery("insert into user_tb(img_filename, resume_id) values(?,?)");
        query2.setParameter(1, imgFilename);
        query2.setParameter(2, resumeId);
        query2.executeUpdate();
    }

    @Transactional
    public void resumeUpdate(String title, String imgFilename, int id) {
        Query query2 = em.createNativeQuery("UPDATE user_tb SET img_filename = ? WHERE resume_id = ?;");
        query2.setParameter(1, imgFilename);
        query2.setParameter(2, id);
        query2.executeUpdate();
    }

    public Pic jobopenFindByPic(int id) {
        Query query = em.createNativeQuery("SELECT * FROM user_tb WHERE jobopen_id = ?", Pic.class);
        query.setParameter(1, id);
        System.out.println(id);
        return (Pic) query.getSingleResult();
    }

    @Transactional
    public void jobopenUpload(String title, String imgFilename) {
        Query query = em.createNativeQuery("select max(id+1) from jobopen_tb");
        Integer jobopenId = (Integer) query.getSingleResult();

        Query query2 = em.createNativeQuery("insert into user_tb(img_filename, jobopen_id) values(?,?,?)");
        query2.setParameter(1, title);
        query2.setParameter(2, imgFilename);
        query2.setParameter(3, jobopenId);
        query2.executeUpdate();
    }

    @Transactional
    public void jobopenUpdate(String title, String imgFilename, Integer id) {
        Query query2 = em.createNativeQuery("UPDATE pic_tb SET img_filename = ? WHERE jobopen_id = ?;");
        query2.setParameter(1, imgFilename);
        query2.setParameter(2, id);
        query2.executeUpdate();
    }
}