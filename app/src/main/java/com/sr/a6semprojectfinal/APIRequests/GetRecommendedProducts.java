package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetRecommendedProducts {

    public interface CompletionListener{
        public void onStart();
        public void onComplete();
    }
    public static ArrayList<String> product_name = new ArrayList<>();
    public static ArrayList<String> product_price = new ArrayList<>();
    public static ArrayList<String> product_image = new ArrayList<>();


    public static void getProducts(Context context, CompletionListener listener){
        listener.onStart();
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLHolder.recommendedProducts, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int length = response.length();
                    int i;
                    for(i = 0; i < length ; i++){
                        JSONObject object = response.getJSONObject(i);
                        String name = object.getString("name");
                        String price = object.getString("price");
                        String file_name = object.getString("image");

                        product_name.add(name);
                        product_price.add(price);
                        product_image.add(URLHolder.ImageUrl+file_name);




                    }
                    listener.onComplete();
                }
                catch (Exception e){
                    Log.d("Home","Couldn't Parse");
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(request);

    }

}
