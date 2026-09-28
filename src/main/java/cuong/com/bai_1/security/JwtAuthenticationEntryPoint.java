package cuong.com.bai_1.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        String jwtError = (String) request.getAttribute("jwt_error");
        // Nếu không có lỗi cụ thể được set từ Filter (ví dụ: không gửi token), hiển thị lỗi mặc định
        String message = jwtError != null ? jwtError : "Unauthorized: Token không hợp lệ hoặc thiếu Token";

        Map<String, Object> body = new HashMap<>();
        body.put("code", 401);
        body.put("message", message); // Sẽ trả về chính xác message theo hình mẫu
        body.put("status", "FAIL");

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), body);
    }
}