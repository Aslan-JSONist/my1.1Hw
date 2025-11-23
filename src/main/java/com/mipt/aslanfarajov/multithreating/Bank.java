package com.mipt.aslanfarajov.multithreating;

public class Bank {

    public void sendToAccountDeadlock(BankAccount from, BankAccount to, int amount) {
        synchronized (from) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            synchronized (to) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
        }
    }

    public void sendToAccount(BankAccount from, BankAccount to, int amount) {
        if (from == null || to == null) {
            throw new IllegalArgumentException("Accounts cannot be null");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        BankAccount firstLock = from.getId() < to.getId() ? from : to;
        BankAccount secondLock = from.getId() < to.getId() ? to : from;

        synchronized (firstLock) {
            synchronized (secondLock) {
                if (from.getBalance() >= amount) {
                    from.withdraw(amount);
                    to.deposit(amount);
                } else {
                    throw new IllegalStateException("Insufficient funds");
                }
            }
        }
    }
}
