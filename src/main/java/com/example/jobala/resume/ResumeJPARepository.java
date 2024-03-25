package com.example.jobala.resume;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ResumeJPARepository extends JpaRepository<Resume, Integer> {

    // 회사가 스크랩한 이력서 조회
    @Query("SELECT r FROM Resume r JOIN fetch r.scraps s WHERE s.user.id = :userId")
    List<ResumeResponse.ScrapDTO> findByUserIdJoinScrap(@Param("userId") int userId);

    // 이력서 상세보기를 위한 한방 join쿼리
    @Query("SELECT r FROM Resume r JOIN FETCH r.user WHERE r.id = :resumeId")
    Optional<Resume> findByIdWithUser(@Param("resumeId") Integer resumeId);

}
