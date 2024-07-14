package com.medivision.medivision.viewer.dto.response;


import com.medivision.pacs.entity.VImageEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ViewImageResponseDto {
    private int studyKey;
    private int seriesKey;
    private int imageKey;
    private String path;
    private String fname;
    private String totalPath;
}
