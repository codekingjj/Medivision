package com.medivision.medivision.log.login.domain;

import com.medivision.util.Timestamps;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "log_login")
@Entity
public class LoginLogEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int logIndex;
    private String userId;
    private String clientIp;
    private LocalDateTime loginDate;

}
