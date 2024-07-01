package com.medivision.medivision.log.login.dto;

import com.medivision.medivision.log.login.domain.LoginLogEntity;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginHistroyDto {
    private int logIndex;
    private String userId;
    private String clientIp;
    private LocalDateTime loginDate;

    public static LoginHistroyDto histroyDto(LoginLogEntity entity){
        return LoginHistroyDto.builder()
                .logIndex(entity.getLogIndex())
                .userId(entity.getUserId())
                .clientIp(entity.getClientIp())
                .loginDate(entity.getLoginDate())
                .build();
    }
}
