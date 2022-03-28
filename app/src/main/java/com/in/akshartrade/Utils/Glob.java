package com.in.akshartrade.Utils;

import android.app.ProgressDialog;
import android.content.Context;

public class Glob {

    public static String baseUrl = "https://akshartrading.notionprojects.tech/public/api/";
    public static ProgressDialog dialog;
    public static String userId;
    public static String token = "123456789";
    public static String TAG = "MYAPP";
    public static  String instrumentalToken;

    public static void progressDialog(Context context) {
        dialog = new ProgressDialog(context);
        dialog.setCancelable(false); // set cancelable to false
        dialog.setMessage("Please Wait"); // set message

    }

}
