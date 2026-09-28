package cuong.com.bai_1.dto;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeCreateDTO {
    private String fullName;
    private String email;
    private String department;
    private MultipartFile avatarFile; // Nhận file ảnh từ client

    // Getters and Setters
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }
    public MultipartFile getAvatarFile() { return avatarFile; }
    public void setAvatarFile(MultipartFile avatarFile) { this.avatarFile = avatarFile; }
}