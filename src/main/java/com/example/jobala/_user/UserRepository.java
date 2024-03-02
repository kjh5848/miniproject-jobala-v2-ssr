package com.example.jobala._user;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class UserRepository {
    private final EntityManager entityManager;

    public void findAll() {
        return;
    }

    public void findById() {
        return;
    }

    @Transactional
    public void userSave(UserRequst.joinDTO reqDTO) {
        Query query = entityManager.createNativeQuery("insert into  user_tb(name, username, password, address, phone, role, created_at) values(?, ?, ?, ?, ?, ?, now()) ");
        query.setParameter(1,reqDTO.getName());
        query.setParameter(2,reqDTO.getUsername());
        query.setParameter(3,reqDTO.getPassword());
        query.setParameter(4,reqDTO.getAddress());
        query.setParameter(5,reqDTO.getPhone());
        query.setParameter(6,0);
        query.executeUpdate();
    }

    @Transactional
    public void compSave(UserRequst.joinDTO reqDTO) {
        Query query = entityManager.createNativeQuery("insert into  user_tb(comp_num, ceo, compname, address, username, password, name, phone, role, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, now()) ");
        query.setParameter(1,reqDTO.getCompNum());
        query.setParameter(2,reqDTO.getCeo());
        query.setParameter(3,reqDTO.getCompname());
        query.setParameter(4,reqDTO.getAddress());
        query.setParameter(5,reqDTO.getUsername());
        query.setParameter(6,reqDTO.getPassword());
        query.setParameter(7,reqDTO.getName());
        query.setParameter(8,reqDTO.getPhone());
        query.setParameter(9,1);
        query.executeUpdate();
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
