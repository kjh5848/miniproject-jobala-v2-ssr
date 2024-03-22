package com.example.jobala.scrap;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._user.User;
import com.example.jobala.reply.ReplyJPARepository;
import com.example.jobala.resume.Resume;
import com.example.jobala.resume.ResumeJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScrapService {
    private final ScrapJPARepository scrapJPARepository;
    private final ResumeJPARepository resumeJPARepository;

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
}
