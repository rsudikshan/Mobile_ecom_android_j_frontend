package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONObject;

import java.util.ArrayList;

public class SearchRequest {

    public static ArrayList<String> product_name = new ArrayList<>();
    public static ArrayList<String> product_price= new ArrayList<>();
    public static ArrayList<String> image_url = new ArrayList<>();

    public interface SearchCompletionListener{
        public void onComplete();
        public void onError();
    }
    public static void search(String value, Context context, SearchCompletionListener listener){

        product_name.clear();
        product_price.clear();
        image_url.clear();

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLHolder.searchURL+"?value="+value,null,
                (response)->{
                    Log.d("Search","Start");

                    int length = response.length();
                    int i;
                    Log.d("Search",response.toString());
                    for(i=0;i<length;i++){
                        try {
                            JSONObject object = response.getJSONObject(i);
                            String result_product_name = object.getString("name");
                            String result_product_price = object.getString("price");
                            String result_product_image = object.getString("image");

                            product_name.add(result_product_name);
                            product_price.add(result_product_price);
                            image_url.add(URLHolder.ImageUrl+result_product_image);

                            Log.d("Search","Parsed");

                        }

                        catch (Exception e){
                           Log.d("Search","Parsing Error");
                        }
                    }
                    listener.onComplete();


        }
        ,
                (error) -> {
                        Log.d("Search","Error");
                        listener.onError();
                });


        requestQueue.add(request);


    }

    public static ArrayList<String> getProduct_name(){
        return product_name;
    }
    public static ArrayList<String> getProduct_price(){
        return product_price;
    }
    public static ArrayList<String> getImage_url(){
        return image_url;
    }

}
