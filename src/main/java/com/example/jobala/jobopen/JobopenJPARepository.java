package com.example.jobala.jobopen;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface JobopenJPARepository extends JpaRepository<Jobopen, Integer> {

    @Query("select j from Jobopen j where j.id = :id")
    Optional<Jobopen> findByIdWithUser(@Param("id") int id);


}
