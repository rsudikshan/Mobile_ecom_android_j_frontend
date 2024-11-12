package com.sr.a6semprojectfinal.APIRequests;



import android.content.Context;
import android.util.Log;
import android.widget.Toast;

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

public class GetExploreData {
    public static ArrayList<String> productNames = new ArrayList<>();
    public static ArrayList<String> productPrice = new ArrayList<>();
    public static ArrayList<String> imageURL = new ArrayList<>();
    public static ArrayList<String> category = new ArrayList<>();

    public static ArrayList<String> categorizedProductNames = new ArrayList<>();
    public static ArrayList<String> categorizedProductPrice = new ArrayList<>();
    public static ArrayList<String> categorizedImageURL = new ArrayList<>();
    public static String categorizedURL = URLHolder.APIUrl+"getProductsCategorically.php?category=";

    public interface onCategoryLoadingComplete{
        public void onCategoryResults();
        public void onCategoryError();

    }

    public interface OnLoadingComplete{
        public void onResults();
        public void onCategoryError();
        public void onServerError();


    }

    public static void getItems(Context context,OnLoadingComplete complete){
        try{
            productNames.clear();
            productPrice.clear();
            imageURL.clear();
            category.clear();
            Log.d("Volley","RequestStarted");
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLHolder.APIUrl+"getProducts.php", null,
                    new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Volley","RequestSuccessful");
                        int length = response.length();
                        int i;

                        for(i = 0 ; i<length ; i++){
                            try {
                                JSONObject object = response.getJSONObject(i);
                                productNames.add(object.getString("name"));
                                productPrice.add(object.getString("price"));
                                imageURL.add(URLHolder.ImageUrl+object.getString("image"));
                                category.add(object.getString("category"));
                                complete.onResults();
                            }
                            catch (Exception e){
                                Toast.makeText(context,"Invalid JSON",Toast.LENGTH_SHORT).show();
                                Log.d("Volley","Couldn't Parse");
                                Log.d("Volley", e.toString());
                                complete.onCategoryError();
                            }

                        }

                    }
            },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Volley","RequestError : "+error.toString());
                            Toast.makeText(context,"Invalid JSON",Toast.LENGTH_SHORT).show();
                            complete.onServerError();

                        }
                    });

            requestQueue.add(request);




        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void getCategorizedData(Context context,String category,onCategoryLoadingComplete result) {
        categorizedProductNames.clear();
        categorizedProductPrice.clear();
        categorizedImageURL.clear();
        String finalURL = categorizedURL+category;
        Log.d("Volley",finalURL);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, finalURL,null, new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {
                        int length = response.length();
                        int i;
                    try {
                        for(i=0;i<length;i++){

                                JSONObject object = response.getJSONObject(i);
                                categorizedProductNames.add(object.getString("name"));
                                categorizedProductPrice.add(object.getString("price"));
                                categorizedImageURL.add(URLHolder.ImageUrl+object.getString("image"));

                            }
                        Log.d("Volley","GOT THE CATEGORIZED DATA");

                        result.onCategoryResults();



                        }
                    catch (Exception e){

                        Log.d("Volley",e.toString());
                        Log.d("Volley","COULDNT PARSE");
                    }

                    }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        result.onCategoryError();
                        Log.d("Volley","FAILIURE TO GET THE CATEGORIZED DATA");
                        Log.d("Volley",error.toString());
                    }
        });

        requestQueue.add(request);
    }



    public static ArrayList<String> getProductNames(){
        return productNames;
    }

    public static ArrayList<String> getProductPrice(){
        return productPrice;
    }

    public static ArrayList<String> getImageURL(){
        return imageURL;
    }
    public static ArrayList<String> getCategory(){
        return category;
    }


    public static ArrayList<String> getCategorizedProductNames(){
        return categorizedProductNames;
    }

    public static ArrayList<String> getCategorizedProductPrice(){
        return categorizedProductPrice;
    }

    public static ArrayList<String> getCategorizedImageURL(){
        return categorizedImageURL;
    }

}

/* improvements notes
for custom item not setting the size of parent to wrap content it became buggy
for fragment controller overlapping some components of recycler setting the recyclers bottom constraint worked
*/