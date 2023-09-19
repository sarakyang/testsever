package com.sparta.fishingload_backend.service;

import com.sparta.fishingload_backend.dto.*;
import com.sparta.fishingload_backend.entity.RefreshToken;
import com.sparta.fishingload_backend.entity.User;
import com.sparta.fishingload_backend.entity.UserRoleEnum;
import com.sparta.fishingload_backend.repository.RefreshTokenRepository;
import com.sparta.fishingload_backend.repository.UserRepository;
import com.sparta.fishingload_backend.security.JwtUtil;
import com.sparta.fishingload_backend.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtUtil jwtUtil;

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "A1234";

    // 회원가입
    public ResponseEntity<MessageResponseDto> signup(SignupRequestDto requestDto) {
        String userId = requestDto.getUserId();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUserId(userId);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 권한 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(userId, password, role, requestDto);
        userRepository.save(user);

        MessageResponseDto message = new MessageResponseDto("회원가입이 성공했습니다.", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    //아이디 찾기
    public FindUserResponseDto findUser(FindIdRequestDto findRequestDto) {
        String userId = UserIdFind(findRequestDto.getEmail()).getUserId();
        FindUserResponseDto findUserResponseDto = new FindUserResponseDto(userId);
        return findUserResponseDto;
    }

    //비밀번호 찾기
    public FindPasswordResponseDto findPassword(FindRequestDto findRequestDto) {
        String password = PasswordFind(findRequestDto.getUserId(), findRequestDto.getEmail()).getPassword();
        FindPasswordResponseDto findPasswordResponseDto = new FindPasswordResponseDto(password);
        return findPasswordResponseDto;
    }

    //중복확인
    public ResponseEntity<MessageResponseDto> duplicate(FindUserRequestDto findRequestDto) {
        Optional<User> user = userRepository.findByUserId(findRequestDto.getUserId());
        if(user.isEmpty()) {
            MessageResponseDto message = new MessageResponseDto("없는 userId 입니다. ", HttpStatus.OK.value());
            return ResponseEntity.status(HttpStatus.OK).body((message));
        }
        MessageResponseDto message = new MessageResponseDto("해당 userId가 이미 존재합니다. ", HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status((HttpStatus.BAD_REQUEST)).body((message));
    }

    private User UserIdFind(String email) {
        return userRepository.findByEmailAndAccountUseTrue(email).orElseThrow(
                () -> new NullPointerException("해당 유저는 존재하지 않습니다."));
    }

    private User PasswordFind (String userId, String email) {
        return userRepository.findByUserIdAndEmail(userId,email).orElseThrow(
                () -> new NullPointerException("해당 비밀번호가 존재하지 않습니다. "));
    }

    // 스웨거용 로그인
    public ResponseEntity<MessageResponseDto> signin(LoginRequestDto requestDto, HttpServletResponse res) {
        String userId = requestDto.getUserId();
        String password = requestDto.getPassword();

        User user = userRepository.findByUserId(userId).orElseThrow(() ->
                new NullPointerException("해당 유저는 없는 유저입니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("비빌먼호가 일치하지 않습니다.");
        }

        UserRoleEnum role = user.getRole();

        String accessToken = jwtUtil.createAccessToken(userId, role);
        res.addHeader("Authorization", accessToken);

        RefreshToken refreshToken = refreshTokenRepository.findByUserId(userId).orElse(null);
        String refresh = jwtUtil.createRefreshToken(userId, role);
        if (refreshToken == null) {
            refreshToken = new RefreshToken(refresh, userId);
        } else {
            refreshToken.updateToken(refresh);
        }
        refreshTokenRepository.save(refreshToken);
        res.addHeader("Authorization_Refresh", refreshToken.getToken());

        MessageResponseDto message = new MessageResponseDto("로그인 성공했습니다.", HttpStatus.OK.value());
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }
}