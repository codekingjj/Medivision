package com.medivision.medivision.user.dto.response;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignUpResponseDto extends ResponseDto {

    private String userId;
    public SignUpResponseDto(String userId) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        this.userId=userId;
    }

    public static ResponseEntity<SignUpResponseDto> success(String userId){
        SignUpResponseDto result = new SignUpResponseDto(userId);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
