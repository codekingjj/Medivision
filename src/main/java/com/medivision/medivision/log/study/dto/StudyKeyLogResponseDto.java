package com.medivision.medivision.log.study.dto;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.log.study.domain.StudyLogEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class StudyKeyLogResponseDto extends ResponseDto {

    private List<StudyLogEntity> list;

    public StudyKeyLogResponseDto(List<StudyLogEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.list=list;
    }

    public static ResponseEntity<StudyKeyLogResponseDto> success(List<StudyLogEntity> list){
        StudyKeyLogResponseDto response = new StudyKeyLogResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
