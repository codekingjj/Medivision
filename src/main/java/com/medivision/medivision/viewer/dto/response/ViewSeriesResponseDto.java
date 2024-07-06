package com.medivision.medivision.viewer.dto.response;


import com.medivision.pacs.entity.VImageEntity;
import com.medivision.pacs.entity.VSeriesEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ViewSeriesResponseDto {
    private int studyKey;
    private int seriesKey;
    private String seriesDesc;
    private int imageCnt;
    private String path;
    private String fName;
    private List<ViewImageResponseDto> imageList;

    public ViewSeriesResponseDto(VSeriesEntity vSeriesEntity) {
        this.studyKey = vSeriesEntity.getStudyKey();
        this.seriesKey = vSeriesEntity.getSeriesKey();
        this.seriesDesc = vSeriesEntity.getSeriesDesc();
        this.imageCnt = vSeriesEntity.getImageCnt();
        this.path = vSeriesEntity.getPath();
        this.fName = vSeriesEntity.getFName();
    }
}
