package com.medivision.medivision.log.login.domain.service;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.login.dto.LogingLogResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LoginLogService {

    void saveLogin(String userId, String ip);
    ResponseEntity<? super LogingLogResponseDto> loginUserLog(String userCode);
}
