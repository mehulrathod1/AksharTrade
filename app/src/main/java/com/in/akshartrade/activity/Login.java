package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Glob;

public class Login extends AppCompatActivity {

    Button btnLogin, signUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
        clickEvent();
    }

    public void init() {
        btnLogin = findViewById(R.id.btnLogin);
        signUp = findViewById(R.id.signUp);
    }

    public void clickEvent() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
//                finish();
            }
        });
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignUpActivity.class);
                startActivity(intent);
//                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}