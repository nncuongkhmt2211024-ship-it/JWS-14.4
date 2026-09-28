package cuong.com.bai_1.config;

import com.cloudinary.Cloudinary;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {

    // Lấy URL cấu hình Cloudinary từ file application.properties
    @Value("${cloudinary.url}")
    private String cloudinaryUrl;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(cloudinaryUrl);
    }
}

//eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJodW9uZ2Nhb2hhIiwicm9sZSI6IlJPTEVfVVNFUixGQUNUT1JfUEFTU1dPUkQiLCJpYXQiOjE3OTA1NjE3NDgsImV4cCI6MTc5MDY0ODE0OH0.g9DrUg8l1PLFPZ3PAIgIZPdhgKk3pFWgKbuwn1TKeiIkPZ_VM_WjX5kVTxQlkF9sK27rA9VzklynsfTLrAcvHA