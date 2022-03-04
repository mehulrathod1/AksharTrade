package com.in.akshartrade.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.in.akshartrade.Adapter.NseStockAdapter;
import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.Model.NseStockModel;
import com.in.akshartrade.R;

import java.util.ArrayList;
import java.util.List;

public class WatchListActivity extends AppCompatActivity {

    RecyclerView stockRecycler;
    NseStockAdapter nseStockAdapter;
    List<NseStockModel> nseStockModelList = new ArrayList<>();

    ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_watch_list);

        init();
        clickEvent();
        watchListData();
    }

    public void init() {
        stockRecycler = findViewById(R.id.recyclerview);
        backIcon = findViewById(R.id.backIcon);

    }

    public void clickEvent() {
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    public void watchListData() {

        NseStockModel model = new NseStockModel("RELIANCE", "NSE EQ", "₹ 2089.05", "- 45.20", " (2.12%)");
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);

        nseStockAdapter = new NseStockAdapter(nseStockModelList, getApplicationContext(), new NseStockAdapter.Click() {
            @Override
            public void onItemClick(int position) {


                Intent intent = new Intent(getApplicationContext(), CompanyDetailActivity.class);
                startActivity(intent);

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        stockRecycler.setLayoutManager(layoutManager);
        stockRecycler.setAdapter(nseStockAdapter);
    }
}