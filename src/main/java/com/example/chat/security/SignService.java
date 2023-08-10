package com.example.chat.security;

import com.example.chat.security.dto.SignRequest;
import com.example.chat.security.dto.SignResponse;
import com.example.chat.user.Authority;
import com.example.chat.user.User;
import com.example.chat.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class SignService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    public SignResponse login(SignRequest request) throws Exception {

        User user = userRepository.findByAccount(request.getAccount()).orElseThrow(() ->
                new BadCredentialsException("계정정보가 존재하지 않습니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("계정정보가 존재하지 않습니다.");
        }

        return SignResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .name(user.getName())
                .email(user.getEmail())
                .roles(user.getRoles())
                .token(jwtProvider.createToken(user.getAccount(), user.getRoles()))
                .build();
    }

    public boolean register(SignRequest request) throws Exception {
        try {
            User user = User.builder()
                    .account(request.getAccount())
                    .password(passwordEncoder.encode(request.getPassword()))
                    .name(request.getName())
                    .email(request.getEmail())
                    .build();

            user.setRoles(Collections.singletonList(Authority.builder().name("ROLE_USER").build()));
            userRepository.save(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new Exception("잘못된 요청입니다.");
        }

        return true;
    }

    public SignResponse getUser(String account) throws Exception {
        User user = userRepository.findByAccount(account).orElseThrow(() ->
                new Exception("계정을 찾을 수 없습니다."));

        return new SignResponse(user);
    }
}
