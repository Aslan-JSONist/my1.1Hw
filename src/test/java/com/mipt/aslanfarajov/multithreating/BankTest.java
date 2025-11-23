package com.mipt.aslanfarajov.multithreating;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BankTest {

    @Test
    public void testSafeTransferConcurrency() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount(1, 10000);
        BankAccount acc2 = new BankAccount(2, 10000);
        int threadCount = 100;
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            executor.submit(() -> {
                try {
                    bank.sendToAccount(acc1, acc2, 1);
                    bank.sendToAccount(acc2, acc1, 1);
                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();
        executor.shutdown();

        Assertions.assertEquals(10000, acc1.getBalance());
        Assertions.assertEquals(10000, acc2.getBalance());
    }

    @Test
    public void testDeadlockDemonstration() throws InterruptedException {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount(1, 1000);
        BankAccount acc2 = new BankAccount(2, 1000);

        Thread t1 = new Thread(() -> bank.sendToAccountDeadlock(acc1, acc2, 10));
        Thread t2 = new Thread(() -> bank.sendToAccountDeadlock(acc2, acc1, 10));

        t1.start();
        t2.start();

        t1.join(2000);
        t2.join(2000);

        Assertions.assertTrue(t1.isAlive(), "Thread 1 should be stuck in deadlock");
        Assertions.assertTrue(t2.isAlive(), "Thread 2 should be stuck in deadlock");

        t1.interrupt();
        t2.interrupt();
    }

    @Test
    public void testInsufficientFunds() {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount(1, 50);
        BankAccount acc2 = new BankAccount(2, 100);

        Assertions.assertThrows(IllegalStateException.class, () -> {
            bank.sendToAccount(acc1, acc2, 100);
        });
    }

    @Test
    public void testValidation() {
        Bank bank = new Bank();
        BankAccount acc1 = new BankAccount(1, 100);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(null, acc1, 50);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(acc1, null, 50);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            bank.sendToAccount(acc1, acc1, -10);
        });
    }
}
