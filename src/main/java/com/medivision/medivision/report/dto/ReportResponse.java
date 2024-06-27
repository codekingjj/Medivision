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
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_STUDY, ResponseMessage.NOT_EXISTED_STUDY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> getReportSuccess(List<ReportResponseDto> list){
        ReportResponse result = new ReportResponse(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> getReportFail(){
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXISTED_REPORT, ResponseMessage.NOT_EXISTED_REPORT);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> createReportSuccess(){
        ReportResponse result = new ReportResponse(null);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    public static ResponseEntity<ResponseDto> createSpareReportFail(){
        ResponseDto result = new ResponseDto(ResponseCode.SPARE_REPORT_ALREADY_EXIST, ResponseMessage.SPARE_REPORT_ALREADY_EXIST);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

    public static ResponseEntity<ResponseDto> createReportFail(){
        ResponseDto result = new ResponseDto(ResponseCode.REPORT_ALREADY_FULL, ResponseMessage.REPORT_ALREADY_FULL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
    }

}
