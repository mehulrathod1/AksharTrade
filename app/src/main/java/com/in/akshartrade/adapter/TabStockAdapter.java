package com.in.akshartrade.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.akshartrade.Fragment.BseStock;
import com.in.akshartrade.Fragment.FoStock;
import com.in.akshartrade.Fragment.McxStock;
import com.in.akshartrade.Fragment.NseStock;

public class TabStockAdapter extends FragmentPagerAdapter {


    Context context;
    int totalTabs;


    public TabStockAdapter(@NonNull FragmentManager fm, Context context, int totalTabs) {
        super(fm);
        this.context = context;
        this.totalTabs = totalTabs;
    }



    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                NseStock nseStock = new NseStock();
                return nseStock;

            case 1:
                BseStock bseStock = new BseStock();
                return bseStock;

            case 2:

                McxStock mcxStock = new McxStock();
                return mcxStock;
            case 3:

                FoStock foStock = new FoStock();
                return foStock;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
