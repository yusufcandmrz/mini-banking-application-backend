package com.yusufcandmrz.minibank.service.imp;

import com.yusufcandmrz.minibank.dto.request.AccountCreateRequest;
import com.yusufcandmrz.minibank.dto.request.AccountSearchRequest;
import com.yusufcandmrz.minibank.dto.request.AccountUpdateRequest;
import com.yusufcandmrz.minibank.entity.Account;
import com.yusufcandmrz.minibank.entity.User;
import com.yusufcandmrz.minibank.repository.AccountRepository;
import com.yusufcandmrz.minibank.repository.UserRepository;
import com.yusufcandmrz.minibank.service.AccountService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class AccountServiceImp implements AccountService {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    AccountServiceImp(AccountRepository accountRepository, UserRepository userRepository) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Account create(AccountCreateRequest request) {
        User user = userRepository.findByUsername(request.getUserId().toString())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account();
        account.setNumber(request.getNumber());
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        account.setUser(user);

        return accountRepository.save(account);
    }

    @Override
    public List<Account> search(AccountSearchRequest request) {
        return null;
    }

    @Override
    public Account readById(UUID accountId, String username) {
        Account account = checkAccountById(accountId);

        if (!account.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You are not the owner of this account");
        }

        return account;
    }

    @Override
    public Account updateById(UUID accountId, AccountUpdateRequest request) {
        Account account = checkAccountById(accountId);
        account.setNumber(request.getNumber());
        account.setName(request.getName());
        account.setBalance(request.getBalance());
        return accountRepository.save(account);
    }

    @Override
    public Account deleteById(UUID accountId) {
        Account account = checkAccountById(accountId);
        accountRepository.delete(account);
        return account;
    }

    private Account checkAccountById(UUID accountId) {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
    }
}
