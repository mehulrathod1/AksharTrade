package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.in.akshartrade.R;

public class SignIn extends AppCompatActivity {

    Button btnSignIn;
    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        this.setFinishOnTouchOutside(false);

        init();
        clickEvent();
    }

    public void init() {
        btnSignIn = findViewById(R.id.btnSignIn);
        backIcon = findViewById(R.id.backIcon);
    }

    public void clickEvent() {

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}