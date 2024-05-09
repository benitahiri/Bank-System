package com.bankSystem.services;

import com.bankSystem.entity.Account;
import com.bankSystem.exception.AccountNotFoundException;
import com.bankSystem.exception.InsufficientFundsException;
import com.bankSystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Transactional
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }

    public Account getAccountById(int accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found with ID: " + accountId));
    }

    @Transactional
    public void deposit(int accountId, double amount) throws AccountNotFoundException {
        Account account = getAccountById(accountId);
        account.deposit(amount);
        accountRepository.save(account);
    }

    @Transactional
    public void withdraw(int accountId, double amount) throws AccountNotFoundException, InsufficientFundsException {
        Account account = getAccountById(accountId);
        account.withdraw(amount);
        accountRepository.save(account);
    }
}
