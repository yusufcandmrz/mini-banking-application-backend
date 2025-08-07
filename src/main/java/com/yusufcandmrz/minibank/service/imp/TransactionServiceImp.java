package com.yusufcandmrz.minibank.service.imp;

import com.yusufcandmrz.minibank.dto.request.TransferRequest;
import com.yusufcandmrz.minibank.entity.Account;
import com.yusufcandmrz.minibank.entity.Transaction;
import com.yusufcandmrz.minibank.entity.TransactionStatus;
import com.yusufcandmrz.minibank.repository.AccountRepository;
import com.yusufcandmrz.minibank.repository.TransactionRepository;
import com.yusufcandmrz.minibank.service.TransactionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImp implements TransactionService {

    TransactionRepository transactionRepository;
    AccountRepository accountRepository;

    TransactionServiceImp(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional
    public Transaction transfer(TransferRequest request) {
        Account fromAccount = checkAccountById(request.getFromAccountId());
        Account toAccount = checkAccountById(request.getToAccountId());

        Transaction transaction = new Transaction();
        transaction.setFrom(fromAccount);
        transaction.setTo(toAccount);
        transaction.setAmount(request.getAmount());

        try {

            if (fromAccount.getBalance().compareTo(request.getAmount()) < 0) {
                throw new RuntimeException("Insufficient balance");
            }

            fromAccount.setBalance(fromAccount.getBalance().subtract(request.getAmount()));
            toAccount.setBalance(toAccount.getBalance().add(request.getAmount()));

            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
            transaction.setStatus(TransactionStatus.SUCCESS);

        } catch (Exception e) {
            transaction.setStatus(TransactionStatus.FAILED);
            throw e;
        } finally {
            transactionRepository.save(transaction);
        }
        return transaction;
    }

    @Override
    public List<Transaction> history(UUID accountId) {
        return transactionRepository.findByAccountId(accountId);
    }

    private Account checkAccountById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
