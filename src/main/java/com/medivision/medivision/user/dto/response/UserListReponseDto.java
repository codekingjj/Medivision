package com.medivision.medivision.user.dto.response;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserListReponseDto extends ResponseDto {

    private List<AdminEntity> userLIst = null;

    public UserListReponseDto(List<AdminEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        userLIst = list;
    }
    public static ResponseEntity<UserListReponseDto> success(List<AdminEntity> list){
        UserListReponseDto result = new UserListReponseDto(list);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
}
