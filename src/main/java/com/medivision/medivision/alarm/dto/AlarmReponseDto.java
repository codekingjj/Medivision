package com.medivision.medivision.alarm.dto;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.alarm.domain.AlarmEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Getter
public class AlarmReponseDto extends ResponseDto {

    private List<AlarmEntity> alarmList;

    private int check;

    public AlarmReponseDto(List<AlarmEntity> alarmList, int check) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.alarmList = alarmList;
        this.check = check;
    }

    public static ResponseEntity<AlarmReponseDto> success(List<AlarmEntity> alarmList, int check){
        AlarmReponseDto response = new AlarmReponseDto(alarmList, check);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
