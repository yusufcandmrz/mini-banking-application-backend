package com.yusufcandmrz.minibank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountSearchRequest {
    private String number;
    private String name;
}
