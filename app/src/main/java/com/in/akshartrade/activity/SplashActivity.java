package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Glob;

public class SplashActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        SharedPreferences prefs = getSharedPreferences("MyPref", MODE_PRIVATE);
        String token = prefs.getString("token", "null");
        String id = prefs.getString("id", "null");//"No name defined" is the default value.
        prefs.edit().commit();


        if (!id.equals("null")) {

            Glob.userId = id;
            Glob.token = token;
            moveNext(MainActivity.class);

            Log.e(Glob.TAG, "onCreate: " + id + "-------" + token);
        }
        else {

            moveNext(Login.class);
        }

    }


    public void moveNext(Class c) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), c);
                startActivity(i);
                finish();

            }
        }, SPLASH_SCREEN_TIME_OUT);
    }


}