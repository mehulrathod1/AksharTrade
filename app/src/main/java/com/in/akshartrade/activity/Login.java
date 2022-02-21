package com.in.akshartrade.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.in.akshartrade.R;

public class Login extends AppCompatActivity {

    Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        init();
        clickEvent();
    }

    public void init() {
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void clickEvent() {

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
            }
        });
    }
}