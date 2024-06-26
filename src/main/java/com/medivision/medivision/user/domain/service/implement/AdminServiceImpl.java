package com.medivision.medivision.user.domain.service.implement;

import com.medivision.common.ResponseDto;
import com.medivision.medivision.user.domain.entity.AdminEntity;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.AdminRepository;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.medivision.user.domain.service.AdminService;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.request.SignUpRequestDto;
import com.medivision.medivision.user.dto.response.SignUpResponseDto;
import com.medivision.medivision.user.dto.response.UserListReponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // 회원가입
    @Override
    public ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto) {
        System.out.println(dto.getUserCode());
        AdminEntity result = adminRepository.findById(dto.getUserCode()).orElseThrow();
        if(result == null) return SignUpResponseDto.databaseError();
        result.setSignup(true);


        boolean dupulicateId = true;
        String id = "";
        while (dupulicateId){
            id = generateRandomString();
            dupulicateId = userRepository.existsByUserId(id);
        }
        //패스워드 암호화
        String encodePassword = passwordEncoder.encode(result.getUserJuminSC());

        UserEntity user = new UserEntity(dto.getUserCode(), id, encodePassword);

        System.out.println("code: "+dto.getUserCode());
        System.out.println("id: "+id);
        System.out.println("password(before): " + result.getUserJuminSC());
        System.out.println("password(after): "+passwordEncoder.toString());

        userRepository.save(user);
        return SignUpResponseDto.success();
    }

    @Override
    public Page<AdminEntity> userLIst(int pageNum, int pageSize) {
        Page<AdminEntity> userList = adminRepository.findAll(PageRequest.of(pageNum - 1, pageSize));
        if(userList == null) return null;
        return  userList;
    }
    public long getTotalCount(){
        return adminRepository.count();
    }

    @Override
    public boolean adminSignIn(SignInRequestDto requestBody) {
        String userId = requestBody.getUserId();
        String userPassword = requestBody.getUserPassword();
        if(userId.equals("admin") && userPassword.equals("admin")){
            return true;
        }
        return false;
    }


    // 아이디 자동 생성
    private static String generateRandomString() {
        // 랜덤 객체 생성
        Random random = new Random();

        // 5자리 영어 알파벳 생성
        StringBuilder alphaPart = new StringBuilder(5);
        for (int i = 0; i < 5; i++) {
            // 'A'에서 'Z' 사이의 랜덤한 문자 선택
            char randomChar = (char) ('a' + random.nextInt(26));
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
