package com.sr.a6semprojectfinal.DataHolders;

import com.sr.a6semprojectfinal.Main;

public class URLHolder {


    public static String MainURL = "https://69b1-2400-1a00-b060-452f-84b2-1346-34d5-fcc2.ngrok-free.app";
    public static String APIUrl = MainURL+"/endpoints/APIendpoints/";
    public static String ImageUrl = MainURL+"/endpoints/ImageStore/";


    public static String RegisterURL = APIUrl + "registerUser.php";
    public static String LoginUrl = APIUrl+"loginValidate.php";
    public static String sessionCheck = APIUrl+"userSessionCheck.php";
    public static String AddToCart = APIUrl+"addToCart.php";
    public static String Logout = APIUrl+"logout.php";
    public static String recommendedProducts = APIUrl+"recommendedProducts.php";
    public static String searchURL = APIUrl+"searchProducts.php";
    public static String setTransaction = APIUrl+"setTransactionHistory.php";
    public static String getTransaction = APIUrl+"getTransactionHistory.php";




    // https://b15d-2400-1a00-b060-d8b0-88b7-690e-16ad-f1b6.ngrok-free.app
    //   android:scaleType="centerCrop" https://fbb9-2400-1a00-b060-94b8-dde5-95fb-88d5-f869.ngrok-free.app
}
