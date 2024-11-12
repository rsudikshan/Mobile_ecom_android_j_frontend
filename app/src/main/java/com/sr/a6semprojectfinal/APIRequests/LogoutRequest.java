package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONObject;

import java.net.CookieManager;
import java.net.CookiePolicy;

public class LogoutRequest {
    public interface OnCompleteListener{
        void onCompletion();
        void onError();

    }
    public static String TAG = "LOGOUT_STATUS";

    public static void logout(Context context, OnCompleteListener listener){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, URLHolder.Logout, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        JSONObject object = new JSONObject(response);
                        String okStatus = object.getString("status");
                        if(okStatus.contains("successful")){
                            Log.d(TAG,"parsed and logged out");
                            listener.onCompletion();
                            CookieManager cookieManager = new CookieManager();
                            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
                            cookieManager.getCookieStore().removeAll();


                        }
                        else{
                            Log.d(TAG,response.toString());

                        }



                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG,"error :" + error.toString());


                    }
                });

        requestQueue.add(request);



    }
}
