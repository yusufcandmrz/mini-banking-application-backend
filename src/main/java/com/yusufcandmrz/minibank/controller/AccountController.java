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
    public ResponseEntity<Account> create(@RequestBody AccountCreateRequest request) {
        Account createdAccount = accountService.create(request);
        return ResponseEntity.ok(createdAccount);
    }

    @PostMapping("/search")
    public ResponseEntity<?> search(@RequestBody AccountSearchRequest request) {
        List<Account> accountList = accountService.search(request);
        return ResponseEntity.ok(accountList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> readById(@PathVariable UUID accountId, Authentication authentication) {
        Account readAccount = accountService.readById(accountId, authentication.getName());
        return ResponseEntity.ok(readAccount);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> updateById(
            @PathVariable UUID accountId, @RequestBody AccountUpdateRequest request) {
        Account updatedAccount = accountService.updateById(accountId, request);
        return ResponseEntity.ok(updatedAccount);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable UUID accountId) {
        Account deletedAccount = accountService.deleteById(accountId);
        return ResponseEntity.ok(deletedAccount);
    }
}
