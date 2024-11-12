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

import java.util.HashMap;
import java.util.Map;

public class RegisterUser {

    public interface RegisterCompletionListener{
        public void onCompletion();
        public void onRegistrationError();
        public void onError();

    }
    public static void register(String email, String username, String password, Context context, RegisterCompletionListener listener){

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, URLHolder.RegisterURL,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        Log.d("Registration","Successful1");
                        String trimmedResponse = response.trim();
                        if(trimmedResponse.contains("successful")){
                            Log.d("Registration","Successful");
                            listener.onCompletion();
                        }
                        else{
                            Log.d("Registration","UnSuccessful");
                            listener.onRegistrationError();
                        }
                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Registration", "Error :" + error.toString());
                        listener.onError();

                    }
                }){
            @Override
            public Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("Email",email);
                params.put("Username",username);
                params.put("Password",password);
                return params;

            }
        };

        requestQueue.add(request);


    }
}

//TODO async ui update