package com.example.jobala.scrap;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.reply.ReplyJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import com.example.jobala.resume.ResumeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;
    private final JobopenJPARepository jobopenJPARepository;

    public Scrap 회사가스크랩(ScrapRequest.CompScrapDTO reqDTO, User sessionUser) {
        Resume resume = resumeJPARepository.findById(reqDTO.getResumeId())
                .orElseThrow(() -> new Exception404("스크랩 하려는 이력서를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findCompScrapByResumeIdAndUserId(reqDTO.getResumeId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            scrapJPARepository.save(reqDTO.toEntity(resume, sessionUser));
            return scrap;
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    public Scrap 게스트가스크랩(ScrapRequest.GuestScrap reqDTO, User sessionUser) {
        Jobopen jobopen = jobopenJPARepository.findById(reqDTO.getJobopenId())
                .orElseThrow(() -> new Exception404("스크랩 하려는 공고를 찾을 수 없습니다."));

        Scrap scrap = scrapJPARepository.findGuestScrapByJobopenIdAndUserId(reqDTO.getJobopenId(), sessionUser.getId())
                .orElse(null);
        if (scrap == null) {
            scrapJPARepository.save(reqDTO.toEntity(jobopen, sessionUser));
            return scrap;
        } else {
            scrapJPARepository.deleteById(scrap.getId());
            return null;
        }
    }

    public List<ResumeResponse.ScrapDTO> 회사가스크랩한이력서조회(Integer id) {
        List<ResumeResponse.ScrapDTO> respDTO = resumeJPARepository.findByUserIdJoinScrap(id);
        return respDTO;
    }

    public List<JobopenResponse.ScrapDTO> 게스트가스크랩한공고조회(Integer id) {
        List<JobopenResponse.ScrapDTO> respDTO = jobopenJPARepository.findByUserIdJoinScrap(id);
        return respDTO;
    }
}
