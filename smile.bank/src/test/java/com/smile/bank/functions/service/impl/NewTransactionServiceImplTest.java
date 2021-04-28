package com.smile.bank.functions.service.impl;

import com.smile.bank.functions.service.NewTransactionService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;



class NewTransactionServiceImplTest {
    private static NewTransactionService NTS;

    @BeforeAll
    static void setUpNTS() {
    NTS= new NewTransactionServiceImpl();
    }



    @Test
    void withdrawAcc() {
        double balance=100;
        double amount=10;
        assertTrue(balance>=amount);
        assertTrue(amount>0);
    }

    @Test
    void withdrawAccLowBalance() {
        double balance=10;
        double amount=100;
        assertFalse(balance>=amount);
        assertTrue(amount>0);
    }

    @Test
    void withdrawAccBadAmount() {
        double balance=100;
        double amount=-10.00;
        assertTrue(balance>=amount);
        assertFalse(amount>0);
    }

    @Test
    void depositAcc() {
        double amount=10000.00;
        assertTrue(amount>0);
    }
    @Test
    void depositAccBadAmount() {
        double amount=-2000;
        assertFalse(amount>0);
    }

    @Test
    void sendMoney() {
        int target_customer_id=6;
        int target_acc_num=7;
        int acc_num=6;
        double balance=1000;
        double amount=100;
        assertTrue(target_customer_id>0);
        assertTrue(target_acc_num>0);
        assertTrue(balance>=amount);
        assertTrue(amount>0);
    }

    @Test
    void sendMoneyBadTargetCustomer() {
        int target_customer_id=-7;
        int target_acc_num=7;
        int acc_num=6;
        double balance=1000;
        double amount=100;
        assertFalse(target_customer_id>0);
        assertTrue(target_acc_num>0);
        assertTrue(balance>=amount);
        assertTrue(amount>0);
    }
@Test
    void sendMoneyBadTargetAccount() {
        int target_customer_id=6;
        int target_acc_num=-7;
        int acc_num=6;
        double balance=1000;
        double amount=100;
        assertTrue(target_customer_id>0);
        assertFalse(target_acc_num>0);
        assertTrue(balance>=amount);
        assertTrue(amount>0);
    }

    @Test
    void sendMoneyLowBalance() {
        int target_customer_id=6;
        int target_acc_num=7;
        int acc_num=6;
        double balance=10;
        double amount=100;
        assertTrue(target_customer_id>0);
        assertTrue(target_acc_num>0);
        assertFalse(balance>=amount);
        assertTrue(amount>0);
    }

    @Test
    void sendMoneyBadAmount() {
        int target_customer_id=6;
        int target_acc_num=7;
        int acc_num=6;
        double balance=1000;
        double amount=-100;
        assertTrue(target_customer_id>0);
        assertTrue(target_acc_num>0);
        assertTrue(balance>=amount);
        assertFalse(amount>0);
    }
}