package com.in.akshartrade.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {

    RecyclerView orderRecycler;
    OrderAdapter orderAdapter;
    List<OrderModel> orderList = new ArrayList<>();

    ImageView profile;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        init();
        clickEvent();
        orderData();
    }


    public void init() {
        orderRecycler = findViewById(R.id.orderRecycler);
        profile = findViewById(R.id.profile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomNavigationView.setSelectedItemId(R.id.order);
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

    }
    public void orderData() {

        OrderModel model = new OrderModel("RELIANCE", "₹ 1027.65", "₹ 2027.65", "NSE QTY: 30");
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);
        orderList.add(model);

        orderAdapter = new OrderAdapter(orderList, getApplicationContext(), new OrderAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        orderRecycler.setLayoutManager(layoutManager);
        orderAdapter.notifyDataSetChanged();
        orderRecycler.setAdapter(orderAdapter);

    }

}