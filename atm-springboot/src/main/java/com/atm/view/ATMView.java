package com.atm.view;

import com.atm.model.ATMModel;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ATMView {

    private final Scanner scanner;

    public ATMView() {
        scanner = new Scanner(System.in);
    }

    public void showMenu() {

        System.out.println("\n=================================");
        System.out.println("        ATM MAIN MENU");
        System.out.println("=================================");
        System.out.println("1. Withdraw Cash");
        System.out.println("2. View ATM Status");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
    }

    public int getChoice() {

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public int getWithdrawAmount() {

        System.out.print("\nEnter amount to withdraw : Rs.");

        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            return -1;
        }
    }

    public void showATMStatus(ATMModel model) {

        System.out.println("\n========== ATM STATUS ==========");

        int[] denominations = model.getDenominations();
        int[] cassette = model.getCassette();

        for (int i = 0; i < denominations.length; i++) {

            System.out.printf("Rs.%-4d : %3d note(s)%n",
                    denominations[i],
                    cassette[i]);
        }

        System.out.println("--------------------------------");
        System.out.println("Total Cash : Rs." + model.getTotalCash());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void closeScanner() {
        scanner.close();
    }
}
