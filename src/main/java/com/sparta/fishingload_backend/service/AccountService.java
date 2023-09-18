package com.sparta.fishingload_backend.service;

import com.sparta.fishingload_backend.dto.AccountResponseDto;
import com.sparta.fishingload_backend.dto.MyPageResonseDto;
import com.sparta.fishingload_backend.dto.SignupRequestDto;
import com.sparta.fishingload_backend.entity.User;
import com.sparta.fishingload_backend.entity.UserRoleEnum;
import com.sparta.fishingload_backend.repository.UserRepository;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //개인 정보
    public AccountResponseDto MyInfo(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        AccountResponseDto accountResponseDto = new AccountResponseDto(user);
        return accountResponseDto;
    }

    //개인정보 수정
    public AccountResponseDto UserUpdate(Long id, SignupRequestDto signupRequestDto, UserDetailsImpl userDetails) {
        if (!(id.equals(userDetails.getUser().getId()))&& userDetails.getUser().getRole() == UserRoleEnum.ADMIN ) {
            throw new IllegalArgumentException("해당 유저만 수정할 수 있습니다.");
        }
        String password = passwordEncoder.encode(signupRequestDto.getPassword());
        User user = userDetails.getUser();
        user.update(password, signupRequestDto);
        userRepository.save(user);
        return new AccountResponseDto(user);
    }

    //마이페이지
    public MyPageResonseDto mypage(UserDetailsImpl userDetails) {
        User user = userDetails.getUser();
        MyPageResonseDto myPageResonseDto = new MyPageResonseDto(user);
        return myPageResonseDto;
    }
}
