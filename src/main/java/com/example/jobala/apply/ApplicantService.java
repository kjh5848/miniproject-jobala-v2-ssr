package com.example.jobala.apply;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ApplicantService {
    private ApplyRepository applyRepository;

    public List<ApplyRequest> getApplicantProfile(Long applicantId) {
        List<ApplyRequest> applicantProfiles = new ArrayList<>();

        Optional<Apply> applyOptional = Optional.ofNullable(applyRepository.findById(applicantId));
        applyOptional.ifPresent(apply -> {
            ApplyRequest applyRequest = mapToApplicantProfileDTO(apply);
            applicantProfiles.add(applyRequest);
        });

        return applicantProfiles;
    }

    private ApplyRequest mapToApplicantProfileDTO(Apply apply) {
        ApplyRequest profileDTO = new ApplyRequest();
//        profileDTO.setName(apply.getName());

//        // 필요한 정보를 매핑하는 로직을 추가합니다.
        return profileDTO;
    }
}
