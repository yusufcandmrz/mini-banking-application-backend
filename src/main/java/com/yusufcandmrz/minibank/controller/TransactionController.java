package com.yusufcandmrz.minibank.controller;

import com.yusufcandmrz.minibank.dto.request.TransferRequest;
import com.yusufcandmrz.minibank.entity.Transaction;
import com.yusufcandmrz.minibank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(name = "transaction")
public class TransactionController {

    TransactionService transactionService;

    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping(name = "/transfer")
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request, Authentication authentication) {
        Transaction transaction = transactionService.transfer(request, authentication.getName());
        return ResponseEntity.ok(transaction);
    }

    @GetMapping(name = "/account/{id}")
    public ResponseEntity<List<Transaction>> history(@PathVariable UUID id, Authentication authentication) {
        List<Transaction> transactionList = transactionService.history(id, authentication.getName());
        return ResponseEntity.ok(transactionList);
    }
}
