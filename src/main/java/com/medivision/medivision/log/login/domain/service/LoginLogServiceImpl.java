package com.medivision.medivision.log.login.domain.service;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import com.medivision.medivision.log.login.domain.LoginLogRepository;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class LoginLogServiceImpl implements LoginLogService{

    private final LoginLogRepository loginLogRepository;
    private final UserRepository userRepository;
    @Override
    public void saveLogin(String userId) {
        LoginLogEntity loginLogEntity = new LoginLogEntity();
        loginLogEntity.setUserId(userId);
        InetAddress localhost=null;
        try {
        localhost = InetAddress.getLocalHost();

        }catch (Exception e){
            e.printStackTrace();
            return;
        }
        LocalDateTime now = LocalDateTime.now();
        loginLogEntity.setLoginDate(now);
        loginLogEntity.setClientIp(localhost.getHostAddress());
        loginLogRepository.save(loginLogEntity);
    }
}
