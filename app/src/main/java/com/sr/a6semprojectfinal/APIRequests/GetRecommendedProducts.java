package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONArray;
import org.json.JSONObject;

public class GetRecommendedProducts {
    public static void getProducts(Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLHolder.recommendedProducts, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    int length = response.length();
                    int i;
                    for(i = 0; i < length ; i++){
                        JSONObject object = response.getJSONObject(i);
                        String product_name = object.getString("name");
                        String product_price = object.getString("price");
                        String product_file_name = object.getString("image");

                    }
                }
                catch (Exception e){

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
