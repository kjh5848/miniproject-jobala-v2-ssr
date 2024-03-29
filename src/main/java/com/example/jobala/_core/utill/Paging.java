package com.example.jobala._core.utill;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;

@Controller
public class Paging {

    public Pageable guestPaging(int page) {
        return PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
    }

    public  Pageable compPaging(int page) {
        return PageRequest.of(page, 3, Sort.by(Sort.Direction.DESC, "id"));
    }

    public  Pageable boardPaging(int page) {
        return PageRequest.of(page, 5, Sort.by(Sort.Direction.DESC, "id"));
    }

    public  Pageable mainPaging(int page) {
        return PageRequest.of(page, 9, Sort.by(Sort.Direction.DESC, "id"));
    }
}
