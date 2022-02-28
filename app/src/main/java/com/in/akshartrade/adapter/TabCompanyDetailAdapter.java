package com.in.akshartrade.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.akshartrade.Fragment.BseStock;
import com.in.akshartrade.Fragment.CompanyOptionChainFragment;
import com.in.akshartrade.Fragment.CompanyOverviewFragment;
import com.in.akshartrade.Fragment.FoStock;
import com.in.akshartrade.Fragment.McxStock;
import com.in.akshartrade.Fragment.NseStock;

public class TabCompanyDetailAdapter extends FragmentPagerAdapter {


    Context context;
    int totalTabs;


    public TabCompanyDetailAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CompanyOverviewFragment companyOverviewFragment = new CompanyOverviewFragment();
                return companyOverviewFragment;

            case 1:
                CompanyOptionChainFragment companyOptionChainFragment = new CompanyOptionChainFragment();
                return companyOptionChainFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
