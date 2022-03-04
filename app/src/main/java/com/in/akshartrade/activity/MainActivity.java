package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.in.akshartrade.Fragment.HistoryFragment;
import com.in.akshartrade.Fragment.OrderFragment;
import com.in.akshartrade.Fragment.TradeBookFragment;
import com.in.akshartrade.R;
import com.in.akshartrade.Fragment.Dashboard;

public class MainActivity extends AppCompatActivity {

    LinearLayout layoutDashboard, layoutOrder, layoutHistory, LayoutTradeBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickEvent();
    }


    public void init() {

        layoutOrder = findViewById(R.id.LayoutOrder);
        layoutDashboard = findViewById(R.id.LayoutDashboard);
        layoutHistory = findViewById(R.id.LayoutHistory);
        LayoutTradeBook = findViewById(R.id.LayoutTradeBook);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame, new Dashboard());
        transaction.addToBackStack(null);
        transaction.commit();


    }

    public void clickEvent() {

        layoutDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new Dashboard());

            }
        });

        layoutOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new OrderFragment());

            }
        });

        layoutHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new HistoryFragment());

            }
        });


        LayoutTradeBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loadFragment(new TradeBookFragment());

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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}