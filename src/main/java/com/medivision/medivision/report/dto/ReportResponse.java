package com.medivision.medivision.report.dto;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseMessage;
import com.medivision.common.ResponseDto;
import com.medivision.medivision.report.domain.service.ReportEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class ReportResponse extends ResponseDto {
    private List<ReportResponseDto> result;

    public ReportResponse(List<ReportResponseDto> result) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.result = result;
    }

    public static ResponseEntity<ReportResponse> getListSuccess(List<ReportResponseDto> list){
        ReportResponse result = new ReportResponse(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> getListFail(){
        ResponseDto result = new ResponseDto(ResponseCode.DATABASE_ERROR, ResponseMessage.DATABASE_ERROR);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> getReportSuccess(List<ReportResponseDto> list){
        ReportResponse result = new ReportResponse(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

}
