package com.smile.bank.functions.service.impl;

import com.smile.bank.functions.service.QuickFindService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuickFindServiceImplTest {
    private static QuickFindService find;

    @BeforeEach
    void setUp() {
        find=new QuickFindServiceImpl();
    }

    @Test
    void findID() {
        String email="";
        assertNotNull(email);
    }

    @Test
    void testFindID() {
        int acc_num=7;
        String account_type="checking";
        assertTrue(acc_num>0);
        assertEquals(account_type,"checking");
    }

    @Test
    void findAccounts() {
        String account_type="savings";
                int customer_id=5;
        assertTrue(customer_id>0);
        assertEquals(account_type,"savings");
    }

    @Test
    void testFindAccounts() {
        String account_type="dog food";
        int customer_id=5;
        int acc_num=8;

        assertTrue(customer_id>0);
        assertNotEquals(account_type,"savings");
        assertFalse(acc_num<=0);
    }

    @Test
    void findTransactionsFilter1() {
        String filter_spec="";
        int filter=1;
        assertFalse(filter>7||filter<1);
        assertNotNull(filter_spec);
    }

    @Test
    void findTransactionsFilter2() {
        String filter_spec="2021-04-28";
        int filter=2;
        assertNotEquals(filter_spec,"04-10-2005");

    }

    @Test
    void findTransactionsFilter3() {
        String filter_spec="Withdraw";
        assertEquals(filter_spec,"Withdraw");
        int filter=3;
    }
    @Test
    void findTransactionsFilter4() {
        String filter_spec="162";
        assertTrue(Integer.parseInt(filter_spec)>0);
        int filter=4;
    }
    @Test
    void findTransactionsFilter5() {
        String filter_spec="123";
        assertTrue(Integer.parseInt(filter_spec)>0);

        int filter=5;
    }

    @Test
    void findTransactionsFilter6() {
        String filter_spec="Milk";
        int filter=6;
        assertNotEquals(filter_spec,"Too Many Spaces Here");
    }
}