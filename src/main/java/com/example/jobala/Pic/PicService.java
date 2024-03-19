package com.example.jobala.Pic;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PicService {
    private final PicJPARepository picJPARepository;
}
