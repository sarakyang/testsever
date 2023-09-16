package com.sparta.fishingload_backend.security;

import com.sparta.fishingload_backend.entity.User;
import com.sparta.fishingload_backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserIdAndAccountUseTrue(userId).orElseThrow(() ->
                new UsernameNotFoundException("해당 유저를 찾을 수 없습니다. :" + userId)
        );

        return new UserDetailsImpl(user);
    }
}