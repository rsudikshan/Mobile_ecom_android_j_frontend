package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONObject;

public class LoginCheck {
    public static String TAG = "LOGIN_CHECK";

    public static void check(Context context){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.GET, URLHolder.sessionCheck, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    String result = object.getString("status");
                    if(result.contains("logged")){
                        SessionReference.isLoggedIn = true;

                    }

                }
                catch (Exception e){
                    Log.d(TAG,"ParsingError : "+e);
                }


            }
        }
                , new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG,"Response Error");
            }
        }
        );


        requestQueue.add(request);


    }
}
