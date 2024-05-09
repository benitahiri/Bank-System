package com.bankSystem.entity;

import com.bankSystem.exception.InsufficientFundsException;

public class WithdrawalTransaction extends Transaction {
    public WithdrawalTransaction(double amount, Account originatingAccount, String transactionReason) {
        super(amount, originatingAccount, null, transactionReason); // Set resultingAccount to null since it's not needed
    }

    @Override
    public void execute() throws InsufficientFundsException {
        if (originatingAccount.getBalance() >= amount) {
            originatingAccount.withdraw(amount);
            System.out.println("Withdrawal successful for reason: " + transactionReason);
        } else {
            throw new InsufficientFundsException("Transaction failed: insufficient funds");
        }
    }
}
