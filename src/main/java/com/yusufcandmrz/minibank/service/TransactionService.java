package com.yusufcandmrz.minibank.service;

import com.yusufcandmrz.minibank.dto.request.TransferRequest;
import com.yusufcandmrz.minibank.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface TransactionService {

    Transaction transfer(TransferRequest request, String username);

    List<Transaction> history(UUID accountId, String username);
}
