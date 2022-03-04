package com.in.akshartrade.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Activity.AccountDetailActivity;
import com.in.akshartrade.Activity.WatchListActivity;
import com.in.akshartrade.R;
import com.in.akshartrade.Adapter.TabStockAdapter;


public class Dashboard extends Fragment {

    View view;
    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView profile, watchList;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        init();
        clickEvent();
        return view;

    }

    public void init() {

        tabLayout = view.findViewById(R.id.tab);
        viewPager = view.findViewById(R.id.pager);
        profile = view.findViewById(R.id.profile);
        watchList = view.findViewById(R.id.watchList);

        tabLayout.addTab(tabLayout.newTab().setText("NSE"));
        tabLayout.addTab(tabLayout.newTab().setText("BSE"));
        tabLayout.addTab(tabLayout.newTab().setText("MCX"));
        tabLayout.addTab(tabLayout.newTab().setText("F&O"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabStockAdapter tabStockAdapter = new TabStockAdapter(getChildFragmentManager(), getActivity(), tabLayout.getTabCount());

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

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
                startActivity(intent);
            }
        });

        watchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), WatchListActivity.class);
                startActivity(intent);
            }
        });
    }
}