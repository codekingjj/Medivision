package com.medivision.medivision.log.report.dto;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.log.report.domain.ReportLogEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class ReportLogResponseDto extends ResponseDto {

    private List<ReportLogEntity> list;

    public ReportLogResponseDto(List<ReportLogEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.list = list;
    }

    public static ResponseEntity<? super ReportLogResponseDto> success(List<ReportLogEntity> list){
        ReportLogResponseDto response = new ReportLogResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
