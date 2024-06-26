package com.medivision.medivision.user.dto.response;

import com.medivision.common.ResponseCode;
import com.medivision.common.ResponseDto;
import com.medivision.common.ResponseMessage;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Getter
public class UserListReponseDto extends ResponseDto {

    private Page<AdminEntity> userLIst = null;

    public UserListReponseDto(Page<AdminEntity> list) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCES);
        userLIst = list;
    }
    public Page<AdminEntity> success(Page<AdminEntity> list){
        UserListReponseDto result = new UserListReponseDto(list);
        return result.getUserLIst();
    }
}
