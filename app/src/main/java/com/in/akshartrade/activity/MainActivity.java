package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.in.akshartrade.Fragment.OrderFragment;
import com.in.akshartrade.R;
import com.in.akshartrade.Fragment.Dashboard;

public class MainActivity extends AppCompatActivity {

    LinearLayout LayoutDashboard, LayoutOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickEvent();
    }


    public void init() {

        LayoutOrder = findViewById(R.id.LayoutOrder);


        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, new Dashboard());
        transaction.addToBackStack(null);
        transaction.commit();



    }

    public void clickEvent() {

        LayoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new OrderFragment());

            }
        });
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}