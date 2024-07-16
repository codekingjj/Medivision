package com.medivision.medivision.log.login.domain.service;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.login.domain.LoginLogRepository;
import com.medivision.medivision.log.login.dto.LogingLogResponseDto;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService{

    private final LoginLogRepository loginLogRepository;
    private final UserRepository userRepository;
    @Override
    public void saveLogin(String userId, String ip) {
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setUserId(userId);
        LocalDateTime now = LocalDateTime.now();
        loginLogEntity.setLoginDate(now);
        loginLogEntity.setClientIp(ip);
        loginLogRepository.save(loginLogEntity);
    }

    @Override
    public ResponseEntity<? super LogingLogResponseDto> loginUserLog(String userCode) {
        int userCodeNumber = Integer.parseInt(userCode);
        List<LoginLogEntity> userLoginList = null;
        try{
            UserEntity user = userRepository.findByUserCode(userCodeNumber);
            String userId = user.getUserId();
            userLoginList = loginLogRepository.findByUserId(userId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return  LogingLogResponseDto.success(userLoginList);
    }


}
