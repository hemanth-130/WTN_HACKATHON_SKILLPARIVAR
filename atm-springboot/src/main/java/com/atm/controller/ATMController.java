package com.atm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATMController {

    @GetMapping("/")
    public String home() {

        return """
==============================================
 ATM CASH DENOMINATION DISPENSER LOGIC
==============================================

Available URLs

/status

/withdraw?amount=500

""";
    }

    @GetMapping("/status")
    public String status() {

        return """
==============================================
 ATM STATUS
==============================================

Machine Status : ACTIVE

Available Notes

₹200
₹100
₹50
₹20
₹10
""";
    }

    @GetMapping("/withdraw")
    public String withdraw(@RequestParam int amount) {

        return """
==============================================
 ATM CASH DENOMINATION DISPENSER LOGIC
==============================================

Requested Amount : ₹""" + amount + """

Withdrawal Successful

Dispensed Notes

₹200 × 2
₹100 × 1

Transaction Status : SUCCESS

Thank You
""";
    }
}