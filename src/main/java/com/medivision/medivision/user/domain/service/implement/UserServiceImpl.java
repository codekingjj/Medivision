package com.medivision.medivision.user.domain.service.implement;

import com.medivision.common.ResponseDto;
import com.medivision.medivision.jwt.JwtProvider;
import com.medivision.medivision.user.domain.entity.UserEntity;
import com.medivision.medivision.user.domain.repository.UserRepository;
import com.medivision.medivision.user.domain.service.UserService;
import com.medivision.medivision.user.dto.request.SignInRequestDto;
import com.medivision.medivision.user.dto.response.SignInResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    @Override
    public ResponseEntity<? super SignInResponseDto> signin(SignInRequestDto dto) {

        String token = null;

        try {

            UserEntity user = userRepository.findByUserId(dto.getUserId());
            if(user == null) return SignInResponseDto.signInFail();

            String password = dto.getUserPassword();
            String encodedPassword = user.getUserPassword();
            boolean isMatched =passwordEncoder.matches(password,encodedPassword);
            if(!isMatched) return  SignInResponseDto.signInFail();
            System.out.println("로그인 완료");
            int userCode = user.getUserCode();
            token = jwtProvider.create(userCode);

        }catch (Exception e){
            e.printStackTrace();
            return ResponseDto.databaseError();
        }

        return SignInResponseDto.success(token);
    }
}
