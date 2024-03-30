package com.example.jobala.jobopen;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class JobOpenDTO {
    private List<JobopenResponse.ListDTO> jobopenList;
    private boolean first;
    private boolean last;
    private int previousPage;
    private int nextPage;
}
