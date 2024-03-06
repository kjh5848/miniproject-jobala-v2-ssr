package com.example.jobala.Pic;

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
    public void PicUpload(String title, String imgFilename) {
        Query query = em.createNativeQuery("insert into pic_tb(title, img_filename) values(?,?)");
        query.setParameter(1, title);
        query.setParameter(2, imgFilename);

        query.executeUpdate();
    }

    public Pic findById(int id) {
        Query query = em.createNativeQuery("select * from pic_tb where id = ?", Pic.class);
        query.setParameter(1, id);

        return (Pic) query.getSingleResult();
    }
}
