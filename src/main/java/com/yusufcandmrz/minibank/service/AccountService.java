package com.yusufcandmrz.minibank.service;

import com.yusufcandmrz.minibank.dto.request.AccountCreateRequest;
import com.yusufcandmrz.minibank.dto.request.AccountSearchRequest;
import com.yusufcandmrz.minibank.dto.request.AccountUpdateRequest;
import com.yusufcandmrz.minibank.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public interface AccountService {

    Account create(AccountCreateRequest request, String username);

    List<Account> search(AccountSearchRequest request);

    Account readById(UUID accountId, String username);

    Account updateById(UUID accountId, AccountUpdateRequest request, String username);

    Account deleteById(UUID accountId, String username);
}
