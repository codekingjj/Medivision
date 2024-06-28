package com.medivision.medivision.search.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class SearchRequestDto {

    private int StudyKey;
    private String pid;
    private String pname;
    private String modality;
    private String studydesc;
    private String startDate;
    private String endDate;
    private int reportstatus;
    private int examstatus;
}
