package com.bankSystem;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.bankSystem.entity.Account;
import com.bankSystem.exception.AccountNotFoundException;
import com.bankSystem.repository.AccountRepository;
import com.bankSystem.services.AccountService;

@SpringBootTest
public class AccountServiceTests {

    @MockBean
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @Test
    public void testCreateAccount() {
        Account account = new Account(1, "User", 1000.0);
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        Account createdAccount = accountService.createAccount(new Account(1, "User", 1000.0));

        assertNotNull(createdAccount);
        assertEquals(account.getAccountId(), createdAccount.getAccountId());
        assertEquals(1000.0, createdAccount.getBalance());
    }

    @Test
    public void testDeposit() throws AccountNotFoundException {
        Account account = new Account(1, "User", 1000.0);
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        accountService.deposit(1, 500.0);

        verify(accountRepository).save(account);
        assertEquals(1500.0, account.getBalance());
    }
}
