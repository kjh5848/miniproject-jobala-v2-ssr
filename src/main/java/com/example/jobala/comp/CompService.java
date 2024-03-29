package com.example.jobala.comp;

import com.example.jobala._core.errors.exception.Exception404;
import com.example.jobala._core.utill.Paging;
import com.example.jobala._user.User;
import com.example.jobala.apply.ApplyJPARepository;
import com.example.jobala.jobopen.Jobopen;
import com.example.jobala.jobopen.JobopenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompService {
    private final CompJPARepository compJPARepository;
    private final ApplyJPARepository applyJPARepository;
    private final Paging paging;


    // 프로필업데이트
    @Transactional
    public User compUpdateProfile(CompRequest.CompProfileUpdateDTO reqDTO, User sessionUser) {
        User user = compJPARepository.findById(sessionUser.getId())
                .orElseThrow(() -> new Exception404("수정할 프로필이 없습니다.")).getUser();

        MultipartFile imgFilename = reqDTO.getImgFilename();

        // 이미지 파일의 저장 경로 설정
        String GuestImgFilename = UUID.randomUUID() + "_" + imgFilename.getOriginalFilename();
        Path imgPath = Paths.get("./image/" + GuestImgFilename);

        try {
            Files.write(imgPath, imgFilename.getBytes());
            String webImgPath = imgPath.toString().replace("\\", "/");
            webImgPath = webImgPath.substring(webImgPath.lastIndexOf("/") + 1);

            user.setCompProfileUpdateDTO(reqDTO,webImgPath);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return user;
    }


    //공고 관리 페이징
    public Page<JobopenResponse.DTO> jobopensFindAll(int page, Integer sessionUserId){
        Pageable pageable = paging.compPaging(page);

        List<Jobopen> temp = compJPARepository.findByUserIdOrderByDesc(sessionUserId);

        List<JobopenResponse.DTO> jobopenList = temp.stream().map(jobopen -> {
            JobopenResponse.DTO dto = new JobopenResponse.DTO(jobopen);
            int count = applyJPARepository.countJobopenApplyById(dto.getId());
            dto.setCount(count);
            return dto;


        }).toList();

        return new PageImpl<>(jobopenList,pageable,jobopenList.size());
    }
}
