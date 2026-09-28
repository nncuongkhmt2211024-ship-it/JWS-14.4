package cuong.com.bai_1.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JwtResponse {

    @JsonProperty("access_token")
    private String accessToken;

    private String type = "Bearer";
    private String username;

    public JwtResponse(String accessToken, String username) {
        this.accessToken = accessToken;
        this.username = username;
    }

    // Getters and Setters
    public String getAccessToken() { return accessToken; }
    public void setAccessToken(String accessToken) { this.accessToken = accessToken; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}