package org.example;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Input {
    private String key = "cur_live_YdskGGNzUwnLGyhdbzn5H2y2jFBEr8JLIHfga710";
    public String fromCurrency;
    public String toCurrency;
    public String amount;
    public String result;

    public Scanner scanner;


    public Input() {
    }

    public void start() {
        scanner = new Scanner(System.in);
        System.out.print("From currency: ");
        fromCurrency = scanner.nextLine();
        System.out.print("To currency: ");
        toCurrency = scanner.nextLine();
        System.out.print("How much?: ");
        amount = scanner.nextLine();
        scanner.close();

        try {
            result =  this.convert(fromCurrency,toCurrency,amount);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("result: " + result);
    }

    private String convert(String from, String to, String amount) throws IOException {

        String route = "https://api.currencyapi.com/v3/latest?apikey=" + key + "&base_currency=" + from + "&currencies=" + to;


        URL url = new URL(route);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();
        JsonParser jp = new JsonParser();
        JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();
        jsonobj.get("data").getAsJsonObject().get(toCurrency).get("value").getAsInt("value");
        String req_result = jsonobj.get("result").getAsString();

        return req_result;
    }
}


