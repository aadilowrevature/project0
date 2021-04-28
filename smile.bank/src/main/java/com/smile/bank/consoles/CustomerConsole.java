package com.smile.bank.consoles;

import com.smile.bank.exception.SmileException;
import com.smile.bank.functions.Functions;
import com.smile.bank.functions.dao.QuickFindDAO;
import com.smile.bank.functions.dao.impl.QuickFindDAOImpl;
import com.smile.bank.log.SmileLog;

import java.util.Scanner;

public class CustomerConsole {
    QuickFindDAO find = new QuickFindDAOImpl();

    public void customerMainConsole(String email) {
        Scanner scanner = new Scanner(System.in);
        SmileLog smile = new SmileLog();
        Functions run = new Functions();
        int ID = 0;
        try {
            ID = find.findID(email);
            smile.message("ID is  " + ID);
        } catch (SmileException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        int ch = 0;
        do {
            try {
                smile.message("User: " + email);
                smile.customerConsole();
                ch = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
            }
            switch (ch) {

                case 1:
                    // Open checking or savings account
                    run.OpenAccountMethod(email);
                    ch = 0;
                    break;
                case 2:
                    // Withdraw
                    run.WithdrawMethod(ID);
                    ch = 0;
                    break;
                case 3:
                    // Deposit
                    run.DepositMethod(ID);
                    ch = 0;
                    break;
                case 4:
                    // Send Money (to another account)
                    run.SendMoneyMethod(ID);
                    smile.message("WIP");
                    ch = 0;
                    break;
                case 5:
                    //view balance
                    run.ViewAccountCustomer(ID);
                    ch = 0;
                    break;
                case 6:
                    // Logout
                    smile.message("Logging out...");
                    smile.message("");
                    break;
                default:
                    smile.error();
                    break;
            }
        } while (ch != 6);
    }
}
