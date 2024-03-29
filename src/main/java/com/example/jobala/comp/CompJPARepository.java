package com.example.jobala.comp;

import com.example.jobala.jobopen.Jobopen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompJPARepository extends JpaRepository<Jobopen,Integer> {

    //공고 관리 페이징
    Page<Jobopen> findAll(Pageable pageable);

    //자기가 올린 공고 가져오기
    @Query("SELECT j FROM Jobopen j WHERE j.user.id = :userId ORDER BY j.id DESC")
    List<Jobopen> findByUserIdOrderByDesc(@Param("userId") Integer userId);

}
