package com.yusufcandmrz.minibank.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AccountUpdateRequest {
    private String number;
    private String name;
    private BigDecimal balance;
}
