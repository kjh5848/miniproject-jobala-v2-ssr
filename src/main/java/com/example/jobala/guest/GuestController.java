package com.example.jobala.guest;

import com.example.jobala._core.utill.Paging;
import com.example.jobala._user.User;
import com.example.jobala._user.UserJPARepository;
import com.example.jobala._user.UserService;
import com.example.jobala.jobopen.JobOpenDTO;
import com.example.jobala.jobopen.JobopenJPARepository;
import com.example.jobala.jobopen.JobopenResponse;
import com.example.jobala.resume.Resume;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GuestController {

    private final HttpSession session;
    private final GuestQueryRepository guestRepository;
    private final GuestService guestService;
    private final UserJPARepository userJPARepository;
    private final GuestQueryRepository guestQueryRepository;
    private final GuestJPARepository guestJPARepository;
    private final JobopenJPARepository jobopenJPARepository;

    private final UserService userService;
    private final Paging paging;

    // DEL: mainForm 삭제


    //TODO: 서비스 만들기
    //공고 검색 결과 페이징 추가 중
    @GetMapping("/guest/jobopenSearch")
    public String jobopenSearch(HttpServletRequest req, @RequestParam(value = "skills", defaultValue = "") String skills,
                                GuestResponse.SearchDTO resDTO, @RequestParam(value = "page",defaultValue = "0") int page) {
        Page<JobopenResponse.ListDTO> jobopenPage = guestService.jobOpenSearch(page,skills, resDTO);

        JobOpenDTO jobOpenDTO = new JobOpenDTO();
        jobOpenDTO.setJobopenList(jobopenPage.getContent());
        jobOpenDTO.setFirst(page == 0 ? true : false);
        jobOpenDTO.setLast(!jobopenPage.hasNext());
        jobOpenDTO.setPreviousPage(page -1);
        jobOpenDTO.setNextPage(page +1);


        req.setAttribute("jobOpenDTO", jobOpenDTO);
        return "guest/jobSearch";
    }

    //TODO: 서비스 만들기
    @GetMapping("/guest/jobSearch")
    public String jobSearch(HttpServletRequest req) {
        List<JobopenResponse.ListDTO> jobopenList = guestService.findAll();
        req.setAttribute("jobopenList", jobopenList);
        return "guest/jobSearch";
    }

    //TODO: 서비스 만들기
    //이력서 관리 페이징
    @GetMapping("/guest/mngForm")
    public String mngForm(HttpServletRequest req,@RequestParam(defaultValue = "0")int page, @RequestParam(defaultValue = "3")int size) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        Page<Resume> resumePage = guestService.resumesFindAll(page, size);
        req.setAttribute("resumeList",resumePage.getContent());
        req.setAttribute("first", page == 0 ? true:false);
        req.setAttribute("last",page < resumePage.getTotalPages() -1);
        req.setAttribute("previousPage",page -1);
        req.setAttribute("nextPage",page +1);
        return "guest/_myPage/mngForm";
    }

    //TODO: 서비스 만들기
    @GetMapping("/guest/profileForm")
    public String profileForm(HttpServletRequest req) {
        User sessionUser = (User) session.getAttribute("sessionUser");

        User guestProfile = userService.guestInfo(sessionUser.getId());
        req.setAttribute("guestProfile", guestProfile);
        return "guest/_myPage/profileForm"; // 파일 확장자를 생략한 뷰의 경로
    }

    @PostMapping("/guest/updateProfile") // 주소 수정 필요!
    public String updateProfile(@RequestParam MultipartFile imgFilename, GuestRequest.GuestProfileUpdateDTO reqDTO) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        String img = String.valueOf(imgFilename);
        guestService.guestUpdateProfile(reqDTO, sessionUser);
        return "redirect:/guest/profileForm";
    }
}