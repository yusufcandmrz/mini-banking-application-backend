package com.yusufcandmrz.minibank.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginResponse {
    private String token;
    private long expiresIn;
}
