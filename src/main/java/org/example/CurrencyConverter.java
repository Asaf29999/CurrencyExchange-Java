package org.example;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyConverter {

    private String key = "cur_live_YdskGGNzUwnLGyhdbzn5H2y2jFBEr8JLIHfga710";

    public String convert(String from, String to, String amount) {
        double convertedAmount = 0;
        try {
            String route = "https://api.currencyapi.com/v3/latest?apikey=" + key + "&base_currency=" + from + "&currencies=" + to;

            URL url = new URL(route);
            HttpURLConnection request = (HttpURLConnection) url.openConnection();
            request.connect();

            JsonParser jp = new JsonParser();
            JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
            JsonObject jsonobj = root.getAsJsonObject();

            // Extract the value from the JSON response
            JsonObject dataObject = jsonobj.getAsJsonObject("data");
            JsonObject currencyObject = dataObject.getAsJsonObject(to);
            double value = currencyObject.get("value").getAsDouble();

            // Convert the amount to double and perform the conversion
            double amountDouble = Double.parseDouble(amount);
            convertedAmount = amountDouble * value;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return String.valueOf(convertedAmount);
    }

    public static void main(String[] args) {

        CurrencyConverter converter = new CurrencyConverter();
        String result = converter.convert("USD", "AUD", "100");
        System.out.println("Converted amount: " + result);

    }
}
