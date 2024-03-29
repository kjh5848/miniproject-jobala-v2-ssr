package com.example.jobala.jobopen;

import com.example.jobala._user.User;
import com.example.jobala._core.errors.exception.Exception403;
import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.scrap.Scrap;
import com.example.jobala.scrap.ScrapJPARepository;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobopenService {

    private final JobopenJPARepository jobopenJPARepository;
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    // 공고목록보기
    public List<Jobopen> jobopenFindAll() {
        return  jobopenJPARepository.findAll();
    }

    // 공고등록
    @Transactional
    public Jobopen jobopenSave(JobopenRequest.SaveDTO reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.save(reqDTO.toEntity(sessionUser));
        return jobopen;
    }

    // 공고삭제
    @Transactional
    public Jobopen jobopenDelete(Integer jobopenId, Integer sessionUserId) {
        Jobopen jobopen = jobopenJPARepository.findById(jobopenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));

        if (sessionUserId != jobopen.getUser().getId()) {
            throw new Exception403("공고를 삭제할 권한이 없습니다.");
        }
        jobopenJPARepository.deleteById(jobopenId);
        return jobopen;
    }

    // 공고수정하기
    @Transactional
    public Jobopen jobopenUpdate(int jobOpenId, int sessionUser, JobopenRequest.UpdateDTO reqDTO) {
        //1.조회 및 예외 처리
        Jobopen jobopen = jobopenJPARepository.findById(jobOpenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다."));
        //2.권한 처리
        if (sessionUser != jobopen.getUser().getId()) {
            throw new Exception403("공고를 수정할 권한이 없습니다.");
        }
        //3.공고 수정
        jobopen.setJobopenUpdate(reqDTO);
        return jobopen;
    }

    // 공고보기
    public JobopenResponse.DetailDTO findJobopenById(Integer jobopenId, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findByJobopenIdWithUser(jobopenId)
                .orElseThrow(() -> new Exception404("공고를 찾을 수 없습니다"));

        List<String> skills = Arrays.stream(jobopen.getSkills().replaceAll("[\\[\\]\"]", "").split(",")).toList();
        String skillsString = String.join(", ", skills);

        // isScrap
        if (sessionUser != null){
            // 모달 공고 목록 조회
            List<Resume> applyResumeList = resumeJPARepository.findByUserId(sessionUser.getId());
            // 스크랩 했는지 안했는지 조회
            Optional<Scrap> scrap = scrapJPARepository.findGuestScrapByJobopenIdAndUserId(jobopenId, sessionUser.getId());

            JobopenResponse.DetailDTO respDTO = new JobopenResponse.DetailDTO(jobopen, sessionUser,applyResumeList);
            respDTO.setScrap(scrap.isPresent());
            respDTO.setSkills(skillsString);
            return respDTO;
        }
        JobopenResponse.DetailDTO respDTO = new JobopenResponse.DetailDTO(jobopen, sessionUser);
        respDTO.setSkills(skillsString);
        return respDTO;
    }
}
