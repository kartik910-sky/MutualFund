package com.kartik.MutualFund;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FundProfitCalculator {

    private static final String API_URL = "https://api.mfapi.in/mf/";

    public static double[] getNavForDates(String schemeCode, String startDate, String endDate) throws Exception {
        String apiUrl = API_URL + schemeCode;
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        // Parse the JSON response to get NAV on both start and end dates
        // Implement the JSON parsing logic here

        // For demonstration purposes, let's assume the NAV values are extracted as doubles
        return new double[]{3682.28990, 3737.30240}; // Replace with actual NAV values
    }

    public static double calculateProfit(String schemeCode, String startDate, String endDate, double capital) throws Exception {
        double[] navValues = getNavForDates(schemeCode, startDate, endDate);
        double navOnPurchaseDate = navValues[0];
        double navOnRedemptionDate = navValues[1];

        // Calculate the number of units allotted on the purchase date
        double unitsAllotted = capital / navOnPurchaseDate;

        // Calculate the value of the units on the redemption date
        double valueOnRedemptionDate = unitsAllotted * navOnRedemptionDate;

        // Calculate the net profit
        return valueOnRedemptionDate - capital;
    }

    public static void main(String[] args) {
        try {
            String schemeCode = "101206";
            String startDate = "26-07-2023";
            String endDate = "18-10-2023";
            double capital = 1000000.0;

            double profit = calculateProfit(schemeCode, startDate, endDate, capital);
            System.out.println("Net Profit: " + profit);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}