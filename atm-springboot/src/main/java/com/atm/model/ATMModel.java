package com.atm.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ATMModel {

    private final int[] denominations = {200, 100, 50, 20, 10};

    private int[] cassette;

    /**
     * Spring creates this bean using the no-arg constructor and injects
     * the initial stock from application.properties (atm.initial-stock).
     * Falls back to 100 notes of each denomination if not configured.
     */
    public ATMModel(@Value("${atm.initial-stock:100,100,100,100,100}") int[] initialStock) {
        this.cassette = initialStock.clone();
    }

    public int[] getDenominations() {
        return denominations;
    }

    public int[] getCassette() {
        return cassette;
    }

    public void setCassette(int[] cassette) {
        this.cassette = cassette.clone();
    }

    public int getSmallestAvailableDenomination() {

        int smallest = -1;

        for (int i = 0; i < denominations.length; i++) {
            if (cassette[i] > 0) {
                smallest = denominations[i];
            }
        }

        return smallest;
    }

    public int getTotalCash() {

        int total = 0;

        for (int i = 0; i < denominations.length; i++) {
            total += denominations[i] * cassette[i];
        }

        return total;
    }
}
