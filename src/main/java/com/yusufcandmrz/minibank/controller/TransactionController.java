package com.yusufcandmrz.minibank.controller;

import com.yusufcandmrz.minibank.dto.request.TransferRequest;
import com.yusufcandmrz.minibank.entity.Transaction;
import com.yusufcandmrz.minibank.service.TransactionService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Transaction> transfer(@RequestBody TransferRequest request) {
        Transaction transaction = transactionService.transfer(request);
        return ResponseEntity.ok(transaction);
    }

    @GetMapping(name = "/account/{accountId}")
    public ResponseEntity<List<Transaction>> history(@PathVariable UUID accountId) {
        List<Transaction> transactionList = transactionService.history(accountId);
        return ResponseEntity.ok(transactionList);
    }
}
