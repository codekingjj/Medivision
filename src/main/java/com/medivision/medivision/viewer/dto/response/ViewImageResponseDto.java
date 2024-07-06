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

    public ViewImageResponseDto(VImageEntity vImageEntity) {
        this.studyKey = vImageEntity.getStudyKey();
        this.seriesKey = vImageEntity.getSeriesKey();
        this.imageKey = vImageEntity.getImageKey();
        this.path = vImageEntity.getPath();
        this.fname = vImageEntity.getFname();
    }
}
