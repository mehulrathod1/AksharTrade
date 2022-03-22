package com.in.akshartrade.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Adapter.TabStockAdapter;
import com.in.akshartrade.R;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView profile, watchList;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickEvent();

    }


    public void init() {

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);
        profile = findViewById(R.id.profile);
        watchList = findViewById(R.id.watchList);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        tabLayout.addTab(tabLayout.newTab().setText("NSE"));
        tabLayout.addTab(tabLayout.newTab().setText("BSE"));
        tabLayout.addTab(tabLayout.newTab().setText("MCX"));
        tabLayout.addTab(tabLayout.newTab().setText("F&O"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabStockAdapter tabStockAdapter = new TabStockAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());

        viewPager.setAdapter(tabStockAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.dashboard);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {



            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent;

                switch (item.getItemId()) {
                    case R.id.dashboard:

                        intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);
                        break;
                    case R.id.order:

                        intent = new Intent(getApplicationContext(), OrderActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);

                        break;
                    case R.id.tradeBook:

                        intent = new Intent(getApplicationContext(), TradeBookActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);

                        break;

                    case R.id.history:


                        intent = new Intent(getApplicationContext(), HistoryActivity.class);
                        startActivity(intent);
                        finish();
                        overridePendingTransition(0, 0);

                        break;
                }
                return true;
            }
        });

    }

    public void clickEvent() {

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), AccountDetailActivity.class);
                startActivity(intent);
            }
        });

        watchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WatchListActivity.class);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


}