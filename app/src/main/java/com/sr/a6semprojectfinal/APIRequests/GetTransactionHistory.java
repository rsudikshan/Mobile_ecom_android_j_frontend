package com.sr.a6semprojectfinal.APIRequests;

import android.app.DownloadManager;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetTransactionHistory {
    public static ArrayList<String> productNames = new ArrayList<>();
    public static ArrayList<String> productPrice= new ArrayList<>();
    public static ArrayList<String> date = new ArrayList<>();
    public static ArrayList<String> transactionKey = new ArrayList<>();

    public interface CompletionListener{
        public void onCompletion();
        public void onError();
        public void onServerError();

    }
    public static void getTransactionHistory(Context context, CompletionListener completionListener){

        productPrice.clear();
        productNames.clear();
        date.clear();
        transactionKey.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLHolder.getTransaction+"?user_name="+ SessionReference.username,
                null,
                (response)->{
                    int i,length = response.length();
                    for(i = 0; i<length ; i++){
                        try{
                            JSONObject object = response.getJSONObject(i);
                            String name = object.getString("product_names");
                            String price = object.getString("total_price");
                            String date_t = object.getString("date");
                            String key = object.getString("transaction_key");

                            productNames.add(name);
                            productPrice.add(price);
                            date.add(date_t);
                            transactionKey.add(key);

                        }catch (Exception e){
                            Log.d("GTH",e.toString());
                        }

                    }
                    completionListener.onCompletion();
                    Log.d("GTH","completed");
                }
                ,
                (error -> {
                    Log.d("GTH","error"+error.toString());
                }));

        requestQueue.add(request);


    }


    public static ArrayList<String> getProductNames(){
        return productNames;
    }
    public static ArrayList<String> getProductPrice(){
        return productPrice;
    }
    public static ArrayList<String> getOrderDate(){
        return date;
    }
    public static ArrayList<String> getTransactionKey(){
        return transactionKey;
    }
}
