package com.medivision.medivision.search.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchResponseDto {

    private int StudyKey;
    private String pid;
    private String pname;
    private String modality;
    private String studydesc;
    private String studydate;
    private int reportstatus;
    private int seriescnt;
    private int imagecnt;
    private int examstatus;

}
