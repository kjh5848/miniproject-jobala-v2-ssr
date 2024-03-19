package com.example.jobala.jobopen;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JobopenService {
    private final JobopenJPARepository jobopenJPARepository;
}

