package com.csi.weavermanufacturing_backend.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private Boolean success;
    private Boolean isAdmin;
    private String token;

    public LoginResponse(String message, Boolean success, Boolean isAdmin, String token) {
        this.message = message;
        this.success = success;
        this.isAdmin = isAdmin;
        this.token = token;
    }
}

