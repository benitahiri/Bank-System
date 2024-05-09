package com.bankSystem.controllers;

import com.bankSystem.entity.Account;
import com.bankSystem.exception.AccountNotFoundException;
import com.bankSystem.exception.InsufficientFundsException;
import com.bankSystem.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountService.createAccount(account);
        return ResponseEntity.ok(createdAccount);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountById(@PathVariable int id) throws AccountNotFoundException {
        Account account = accountService.getAccountById(id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("/{id}/deposit")
    public ResponseEntity<String> deposit(@PathVariable int id, @RequestBody double amount) throws AccountNotFoundException {
        accountService.deposit(id, amount);
        return ResponseEntity.ok("Deposit successful");
    }

    @PostMapping("/{id}/withdraw")
    public ResponseEntity<String> withdraw(@PathVariable int id, @RequestBody double amount) throws AccountNotFoundException, InsufficientFundsException {
        accountService.withdraw(id, amount);
        return ResponseEntity.ok("Withdrawal successful");
    }
}
