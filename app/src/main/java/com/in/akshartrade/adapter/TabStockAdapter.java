package com.in.akshartrade.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.in.akshartrade.Fragment.WatchListTwo;
import com.in.akshartrade.Fragment.WatchListFour;
import com.in.akshartrade.Fragment.WatchListThree;
import com.in.akshartrade.Fragment.WatchListOne;

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
                WatchListOne watchListOne = new WatchListOne();
                return watchListOne;

            case 1:
                WatchListTwo watchListTwo = new WatchListTwo();
                return watchListTwo;

            case 2:

                WatchListThree watchListThree = new WatchListThree();
                return watchListThree;
            case 3:

                WatchListFour watchListFour = new WatchListFour();
                return watchListFour;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
