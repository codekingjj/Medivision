package com.medivision.medivision.user.domain;

import com.medivision.medivision.user.dto.SignUpRequestDto;
import com.medivision.medivision.user.dto.SignUpResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    
    
    // 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {

        Optional<AdminEntity> admin = adminRepository.findById(dto.getUserCode());

        if(admin.isEmpty()) return SignUpResponseDto.databaseError();

        String id = generateRandomString();
        AdminEntity checkId =

        UserEntity user = new UserEntity(dto.getUserCode(), )

        return SignUpResponseDto.success();
    }
    
    
    // 아이디 자동 생성
    private static String generateRandomString() {
        // 랜덤 객체 생성
        Random random = new Random();

        // 5자리 영어 알파벳 생성
        StringBuilder alphaPart = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            // 'A'에서 'Z' 사이의 랜덤한 문자 선택
            char randomChar = (char) ('A' + random.nextInt(26));
            alphaPart.append(randomChar);
        }

        // 3자리 숫자 생성
        StringBuilder numericPart = new StringBuilder(3);
        for (int i = 0; i < 3; i++) {
            int randomDigit = random.nextInt(10); // 0에서 9까지의 랜덤 숫자
            numericPart.append(randomDigit);
        }

        // 두 부분 결합하여 최종 문자열 반환
        return alphaPart.toString() + numericPart.toString();
    }
}
