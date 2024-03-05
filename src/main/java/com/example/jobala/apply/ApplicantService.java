package com.example.jobala.apply;
package com.example.jobala.apply;

import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.resume.Resume;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApplicantService {

    private final EntityManager entityManager; // EntityManager 주입
    private final ApplyRepository applyRepository;

    public List<ApplyRequest> getApplicantProfile(Integer applicantId) {
        List<ApplyRequest> applicantProfiles = new ArrayList<>();
        applyRepository.findById(applicantId)
                .ifPresent(apply -> {
                    ApplyRequest applyRequest = mapToApplicantProfileDTO(apply);
                    applicantProfiles.add(applyRequest);
                });
        return applicantProfiles;
    }

    private ApplyRequest mapToApplicantProfileDTO(Apply apply) {
        ApplyRequest applyRequest = new ApplyRequest();

        // Apply 객체에서 외래키에 해당하는 Resume 및 Jobopen 객체를 가져옵니다.
        Resume resume = getResume(apply.getResumeId());
        Jobopen jobopen = getJobopen(apply.getJobopenId());

        ApplyRequest.ProfileDTO profileDTO = new ApplyRequest.ProfileDTO();
        if (resume != null) {
            profileDTO.setName(resume.getName());
            profileDTO.setResumeTitle(resume.getResumeTitle());
            profileDTO.setEducation(resume.getEdu());
        }
        if (jobopen != null) {
            profileDTO.setJobTitle(jobopen.getJobopenTitle());
        }
        // 다른 필드들도 가져와서 profileDTO에 설정합니다.

        // ApplyRequest 객체에 ProfileDTO를 설정합니다.
        applyRequest.setProfileDTO(profileDTO);

        return applyRequest;
    }

    private Resume getResume(Integer resumeId) {
        // resumeId에 해당하는 Resume 객체를 데이터베이스에서 조회하여 반환합니다.
        Optional<Resume> resumeOptional = resumeRepository.findById(resumeId);
        return resumeOptional.orElse(null);
    }


    private Jobopen getJobopen(Integer jobopenId) {
        private Jobopen getJobopen(Integer jobopenId) {
            // 외래키에 해당하는 Jobopen 객체를 데이터베이스에서 조회하여 반환합니다.
            Optional<Jobopen> jobopenOptional = jobopenRepository.findById(jobopenId);
            return jobopenOptional.orElse(null);
        }
    }
}
