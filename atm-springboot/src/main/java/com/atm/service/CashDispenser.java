package com.atm.service;

import com.atm.model.ATMModel;
import org.springframework.stereotype.Service;

@Service
public class CashDispenser {

    private final ATMModel model;

    public CashDispenser(ATMModel model) {
        this.model = model;
    }

    public boolean dispenseCash(int amount) {

        if (amount <= 0) {
            System.out.println("\n[ERROR] Amount must be greater than zero.");
            return false;
        }

        int smallest = model.getSmallestAvailableDenomination();

        if (smallest == -1) {
            System.out.println("\n[ERROR] ATM is out of cash.");
            return false;
        }

        if (amount % smallest != 0) {
            System.out.println("\n[ERROR] Amount should be multiple of Rs." + smallest);
            return false;
        }

        int[] denominations = model.getDenominations();
        int[] cassette = model.getCassette();

        // Rollback copy
        int[] tempCassette = cassette.clone();

        int[] notes = new int[denominations.length];

        int remaining = amount;

        // Greedy Algorithm
        for (int i = 0; i < denominations.length; i++) {

            int required = remaining / denominations[i];

            int take = Math.min(required, tempCassette[i]);

            if (take > 0) {

                notes[i] = take;

                tempCassette[i] -= take;

                remaining -= take * denominations[i];
            }
        }

        // Cannot dispense exact amount
        if (remaining != 0) {

            System.out.println("\n[ERROR] Unable to dispense Rs." + amount);
            System.out.println("Insufficient denomination combination.");

            return false;
        }

        // Commit changes
        model.setCassette(tempCassette);

        System.out.println("\n========== CASH DISPENSED ==========");

        int totalNotes = 0;

        for (int i = 0; i < denominations.length; i++) {

            if (notes[i] > 0) {

                System.out.println(
                        "Rs." + denominations[i] +
                                " x " + notes[i]);

                totalNotes += notes[i];
            }
        }

        System.out.println("-----------------------------------");
        System.out.println("Total Notes : " + totalNotes);
        System.out.println("Amount      : Rs." + amount);

        return true;
    }
}
