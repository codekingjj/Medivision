package com.medivision.medivision.log.login.dto;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.log.login.domain.LoginLogEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class LogingLogResponseDto extends ResponseDto {
    private List<LoginLogEntity> list;
    private LogingLogResponseDto(List<LoginLogEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.list=list;
    }

    public static  ResponseEntity<LogingLogResponseDto> success(List<LoginLogEntity> list){
        LogingLogResponseDto response = new LogingLogResponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
