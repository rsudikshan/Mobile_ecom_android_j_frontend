package com.sr.a6semprojectfinal.DataHolders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartData {
    public static ArrayList<String> productNames = new ArrayList<>();
    public static ArrayList<String> productPrice = new ArrayList<>();
    public static ArrayList<String> imageURL = new ArrayList<>();

    public static ArrayList<String> category = new ArrayList<>();
    public static ArrayList<Integer> sameProductCount = new ArrayList<>();
    public static ArrayList<String> defaultPriceHolder = new ArrayList<>();


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

        HashMap<String, Integer> frequencyMap = new HashMap<>();


        for (String category : categories) {

            frequencyMap.put(category, frequencyMap.getOrDefault(category, 0) + 1);
        }


        String mostRepeatedCategory = null;
        int maxCount = 0;


        for (Map.Entry<String, Integer> entry : frequencyMap.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                mostRepeatedCategory = entry.getKey();
            }
        }

        return mostRepeatedCategory;
    }

    public static void setDefaultProductCount(){
        int i,size = productNames.size();
        for(i = 0 ; i<size ; i++ ){

            sameProductCount.add(1);
        }
    }

    public static void multiplyDuplicateProductPrice(){
        defaultPriceHolder.addAll(productPrice);
        int size = productNames.size();
        int i,j;

        for( i = 0 ; i<size ; i++){
            if(sameProductCount.get(i)>1){
                int productCount = sameProductCount.get(i);
                int repeatedProductPrice = Integer.parseInt(defaultPriceHolder.get(i));
                int finalPrice = productCount*repeatedProductPrice;

                productPrice.set(i,String.valueOf(finalPrice));


            }
        }
    }

}
