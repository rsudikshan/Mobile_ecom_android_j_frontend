package com.sr.a6semprojectfinal.DataHolders;



public class URLHolder {


    public static String MainURL = "https://3516-110-44-119-26.ngrok-free.app";
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
