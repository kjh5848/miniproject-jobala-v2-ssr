package com.example.jobala.guest;

import com.example.jobala._user.User;
import com.example.jobala.resume.Resume;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GuestJPARepository extends JpaRepository<Resume,Integer> {

    //이력서 관리 페이징
    Page<Resume> findAll(Pageable pageable);
}