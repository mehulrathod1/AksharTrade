package com.in.akshartrade.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.in.akshartrade.R;
import com.in.akshartrade.fragment.Dashboard;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }


    public void init() {


        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, new Dashboard());
        transaction.addToBackStack(null);
        transaction.commit();



    }
}