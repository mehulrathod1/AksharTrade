package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.in.akshartrade.R;

public class AccountDetailActivity extends AppCompatActivity {

    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        init();
        clickEvent();
    }

    public void init() {
        backIcon = findViewById(R.id.backIcon);
    }

    public void clickEvent() {
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}