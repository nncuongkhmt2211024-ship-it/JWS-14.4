package cuong.com.bai_1.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Map;
import cuong.com.bai_1.dto.EmployeeCreateDTO;
import cuong.com.bai_1.service.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @GetMapping
    public ResponseEntity<?> getAllEmployees() {
        // Mock dữ liệu trả về
        List<Map<String, Object>> employees = List.of(
                Map.of("id", 1, "fullName", "Nguyễn Công Hưởng", "salary", 5000.0),
                Map.of("id", 2, "fullName", "Phạm Tuấn Bình", "salary", 5000.0),
                Map.of("id", 3, "fullName", "Nhân viên số 3", "salary", 5000.0)
        );
        return ResponseEntity.ok(employees);
    }

    @Autowired
    private CloudinaryService cloudinaryService;

    @PostMapping
    public ResponseEntity<?> addEmployee(@ModelAttribute EmployeeCreateDTO dto) {
        try {
            // 1. Upload ảnh lên Cloudinary
            String avatarUrl = null;
            if (dto.getAvatarFile() != null && !dto.getAvatarFile().isEmpty()) {
                avatarUrl = cloudinaryService.uploadImage(dto.getAvatarFile());
            }

            // 2. Lưu vào Database (Ở đây anh viết mock Map để minh họa trả về giống hình, em thay bằng repository.save(entity) nhé)
            Map<String, Object> savedEmployee = new HashMap<>();
            savedEmployee.put("id", 1);
            savedEmployee.put("fullName", dto.getFullName());
            savedEmployee.put("email", dto.getEmail());
            savedEmployee.put("department", dto.getDepartment());
            savedEmployee.put("avatarUrl", avatarUrl);
            savedEmployee.put("salary", 0.0);

            // 3. Trả về Status 201 Created
            return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi upload ảnh");
        }
    }
}