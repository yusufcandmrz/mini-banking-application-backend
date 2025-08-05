package com.yusufcandmrz.minibank.service;

import com.yusufcandmrz.minibank.dto.request.AccountCreateRequest;
import com.yusufcandmrz.minibank.dto.request.AccountUpdateRequest;
import com.yusufcandmrz.minibank.entity.Account;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface AccountService {

    Account create(AccountCreateRequest request, String username);

    // Account search();

    Account readById(UUID accountId, String username);

    Account updateById(UUID accountId, AccountUpdateRequest request, String username);

    void deleteById(UUID accountId, String username);
}
