package com.yusufcandmrz.minibank.controller;

import com.yusufcandmrz.minibank.dto.request.AccountCreateRequest;
import com.yusufcandmrz.minibank.dto.request.AccountSearchRequest;
import com.yusufcandmrz.minibank.dto.request.AccountUpdateRequest;
import com.yusufcandmrz.minibank.entity.Account;
import com.yusufcandmrz.minibank.entity.User;
import com.yusufcandmrz.minibank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/account")
public class AccountController {

    AccountService accountService;

    @Autowired
    AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping()
    public ResponseEntity<Account> create(@RequestBody AccountCreateRequest request, Authentication authentication) {
        Account createdAccount = accountService.create(request, authentication.getName());
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody AccountSearchRequest request, Authentication authentication) {
        List<Account> accountList = accountService.search(request, authentication.getName());
        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> readById(@PathVariable UUID accountId, Authentication authentication) {
        Account readAccount = accountService.readById(accountId, authentication.getName());
        return ResponseEntity.ok(readAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateById(
            @PathVariable UUID accountId, @RequestBody AccountUpdateRequest request, Authentication authentication) {
        Account updatedAccount = accountService.updateById(accountId, request, authentication.getName());
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID accountId, Authentication authentication) {
        Account deletedAccount = accountService.deleteById(accountId, authentication.getName());
        return ResponseEntity.ok(deletedAccount);
    }
}
