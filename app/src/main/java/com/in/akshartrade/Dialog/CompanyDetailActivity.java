package com.in.akshartrade.Dialog;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Adapter.TabCompanyDetailAdapter;
import com.in.akshartrade.Adapter.TabStockAdapter;
import com.in.akshartrade.R;

public class CompanyDetailActivity extends AppCompatActivity {



    TabLayout tabLayout;
    ViewPager viewPager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        this.setFinishOnTouchOutside(false);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        init();

    }


    public void init() {

        tabLayout =findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);


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

}