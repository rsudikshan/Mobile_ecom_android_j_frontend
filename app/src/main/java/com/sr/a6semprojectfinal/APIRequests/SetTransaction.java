package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.CartData;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SetTransaction {
    public interface CompletionListener{
        public void onComplete();
        public void onError();


    }
    public static void setTransaction(Context context, CompletionListener listener){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, URLHolder.setTransaction,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                        Log.d("Transaction", response);
                        listener.onComplete();
                    }
                }
                ,
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        ){
            @Override
            public Map<String,String> getParams(){
                HashMap<String, String> params = new HashMap<>();



                String totalPrice = String.valueOf(CartData.getTotalPrice());
                String numOfProducts = String.valueOf(CartData.getNumOfProductsInCart());
                String dominantCategory = CartData.getMostPreferredCategory();


                JSONArray array = new JSONArray(CartData.productNames);
                JSONArray priceArray = new JSONArray(CartData.productPrice);
                JSONArray productCount = new JSONArray(CartData.sameProductCount);

                params.put("product_names",String.valueOf(array));
                params.put("product_details_price",String.valueOf(priceArray));
                params.put("product_individual_count",String.valueOf(productCount));

                params.put("user_name", SessionReference.username);

                params.put("product_price",totalPrice);
                params.put("product_number",numOfProducts);
                params.put("product_category",dominantCategory);
                Log.d("Transaction",String.valueOf(array));

                return params;



            }

        };


        requestQueue.add(request);


    }
}
