package com.in.akshartrade.Dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Adapter.TabCompanyDetailAdapter;
import com.in.akshartrade.Adapter.TabStockAdapter;
import com.in.akshartrade.R;

public class CompanyDetailActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView dialogClose;
    TextView sellStock,buyStock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        this.setFinishOnTouchOutside(false);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        init();
        clickEvent();

    }

    public void init() {

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);
        dialogClose = findViewById(R.id.dialogClose);
        buyStock = findViewById(R.id.buyStock);
        sellStock = findViewById(R.id.sellStock);

        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Option Chain"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabCompanyDetailAdapter tabStockAdapter = new TabCompanyDetailAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());

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
    }

    public void clickEvent() {

        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

}