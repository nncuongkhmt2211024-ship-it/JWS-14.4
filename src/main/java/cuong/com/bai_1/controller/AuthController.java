package cuong.com.bai_1.controller;

import cuong.com.bai_1.dto.JwtResponse;
import cuong.com.bai_1.dto.LoginRequest;
import cuong.com.bai_1.security.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        // 1. Gửi username/password cho AuthenticationManager xác thực
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        // 2. Nếu thành công, set thông tin vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 3. Tạo chuỗi JWT
        String jwt = jwtProvider.generateToken(authentication);

        // 4. Trả về cho Client
        return ResponseEntity.ok(new JwtResponse(jwt, authentication.getName()));
    }
}