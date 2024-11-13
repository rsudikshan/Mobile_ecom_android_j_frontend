package com.sr.a6semprojectfinal.DataHolders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartData {
    public static ArrayList<String> productNames = new ArrayList<>();
    public static ArrayList<String> productPrice = new ArrayList<>();
    public static ArrayList<String> imageURL = new ArrayList<>();

    public static ArrayList<String> category = new ArrayList<>();


    public static int getTotalPrice(){
        int price_length = CartData.productPrice.size();
        int i;
        int total = 0;

        for(i = 0 ; i<price_length ; i++){
            total += Integer.parseInt(productPrice.get(i));
        }

        return total;
    }

    public static int getNumOfProductsInCart(){
        return productNames.size();
    }

    public static String getMostPreferredCategory(){
        return findMostRepeatedCategory(category);
    }

    public static String findMostRepeatedCategory(ArrayList<String> categories) {
        // Create a HashMap to store the frequency of each category
        HashMap<String, Integer> frequencyMap = new HashMap<>();

        // Iterate through the list and count the occurrences of each category
        for (String category : categories) {
            // Update the count of the current category
            frequencyMap.put(category, frequencyMap.getOrDefault(category, 0) + 1);
        }

        // Initialize variables to track the most repeated category
        String mostRepeatedCategory = null;
        int maxCount = 0;

        // Iterate through the map to find the category with the highest frequency
        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostRepeatedCategory = entry.getKey();
            }
        }

        return mostRepeatedCategory;
    }


}
