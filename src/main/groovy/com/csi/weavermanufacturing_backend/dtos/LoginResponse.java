package com.csi.weavermanufacturing_backend.dtos;

import lombok.Data;

@Data
public class LoginResponse {
    private String message;
    private Boolean success;
    private Boolean isAdmin;

    public LoginResponse(String message, Boolean success, Boolean isAdmin) {
        this.message = message;
        this.success = success;
        this.isAdmin = isAdmin;
    }
}
