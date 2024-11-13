package com.sr.a6semprojectfinal.APIRequests;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.sr.a6semprojectfinal.DataHolders.SessionReference;
import com.sr.a6semprojectfinal.DataHolders.URLHolder;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.HashMap;
import java.util.Map;

public class UserLogin {
    public static String LOGIN_TAG = "LOGIN";

    public interface onLoggedIn{
        public void onLogin();
        public void onCredentialError();
        public void onError();
    }

    public static void Login(String username, String password, Context context, onLoggedIn result){
        Log.d(LOGIN_TAG,"called");
        Log.d(LOGIN_TAG,username+password);

        //should have used jwt
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest request = new StringRequest(Request.Method.POST, URLHolder.LoginUrl,new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    Log.d(LOGIN_TAG,"Started");
                    Log.d(LOGIN_TAG, response.toString());

                    try {

                        JSONObject object = new JSONObject(response);
                        String responseString = object.getString("status");

                        if (responseString.equals("successful")) {
                            String email = object.getString("email");
                            SessionReference.username = username;
                            SessionReference.isLoggedIn = true;
                            SessionReference.sessionTracker.put("Username", username);
                            SessionReference.sessionTracker.put("Email",email);
                            Log.d(LOGIN_TAG, "Successful");
                            Log.d(LOGIN_TAG, username);
                            result.onLogin();
                        } else {
                            result.onCredentialError();
                            SessionReference.isLoggedIn = false;
                            SessionReference.sessionTracker.clear();
                            Toast.makeText(context,"Couldn't Login",Toast.LENGTH_SHORT).show();
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Log.d(LOGIN_TAG,"Failed to Parse");
                    }
                }
                },
                new Response.ErrorListener(){
                @Override
                    public void onErrorResponse(VolleyError error){
                    result.onError();
                    SessionReference.isLoggedIn = false;
                    SessionReference.sessionTracker.clear();
                    Log.d(LOGIN_TAG,"Failed");
                    Log.d(LOGIN_TAG, error.toString());
                    Toast.makeText(context,"Server Error",Toast.LENGTH_SHORT).show();
                }
                }){
            @Override
            public Map<String,String> getParams(){
                Map<String,String> params = new HashMap<>();
                params.put("username",username);
                params.put("password",password);
                return params;
            }
        };
        requestQueue.add(request);
    }
}
