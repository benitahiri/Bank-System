package com.bankSystem.entity;

public class DepositTransaction extends Transaction {
    public DepositTransaction(double amount, Account originatingAccount, String transactionReason) {
        // Since there's no resulting account in a deposit, you can pass `null` or modify the design.
        super(amount, originatingAccount, null, transactionReason);
    }

    @Override
    public void execute() {
        originatingAccount.deposit(amount);
        System.out.println("Deposit executed for reason: " + transactionReason);
    }
}
