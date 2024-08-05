package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Input {
    public String fromCurrency;
    public String toCurrency;
    public String amount;
    public String result;

    public Scanner scanner;


    public Input() {
    }

    public void start() throws IOException {
        scanner = new Scanner(System.in);
        System.out.print("From currency: ");
        fromCurrency = scanner.nextLine();
        System.out.print("To currency: ");
        toCurrency = scanner.nextLine();
        System.out.print("How much?: ");
        amount = scanner.nextLine();
        scanner.close();

        CurrencyConverter currencyConverter = new CurrencyConverter();

        result = currencyConverter.convert(fromCurrency, toCurrency, amount);
        System.out.println("result: " + result);
    }

}


