package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.progressDialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.Model.TotalBalanceModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HistoryActivity extends AppCompatActivity {

    RecyclerView historyRecycler;
    OrderAdapter historyAdapter;
    List<OrderModel.OrderData> historyList = new ArrayList<>();

    ImageView profile;
    BottomNavigationView bottomNavigationView;
    BottomSheetDialog bottomSheetDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
        clickEvent();
        getHistory(token, userId);


    }

    public void init() {

        progressDialog(this);
        historyRecycler = findViewById(R.id.orderRecycler);
        profile = findViewById(R.id.profile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);


        bottomSheetDialog = new BottomSheetDialog(HistoryActivity.this);
        bottomSheetDialog.setContentView(R.layout.history_dialog_layout);
        dialog.show();

        bottomNavigationView.setSelectedItemId(R.id.history);
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

    public void getHistory(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.getHistory(token, userId).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {

                OrderModel orderModel = response.body();

                if (response.isSuccessful()) {
                    List<OrderModel.OrderData> dataList = orderModel.getOrderData();

                    for (int i = 0; i < dataList.size(); i++) {

                        OrderModel.OrderData model = dataList.get(i);

                        OrderModel.OrderData data = new OrderModel.OrderData(
                                model.getInstrument_token(),
                                model.getExchange_token(),
                                model.getTradingsymbol(),
                                model.getName(),
                                model.getLTP(),
                                model.getPL_sign(),
                                model.getpAndL(),
                                model.getQTY(),
                                model.getExchange(),
                                model.getOrder_type()
                        );
                        historyList.add(data);

                        Log.d("orderList", "onResponse: " + model.getpAndL());

                    }
                    orderData();
                    dialog.dismiss();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {

            }
        });


    }

    public void orderData() {


        historyAdapter = new OrderAdapter(historyList, getApplicationContext(), new OrderAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        historyRecycler.setLayoutManager(layoutManager);
        historyAdapter.notifyDataSetChanged();
        historyRecycler.setAdapter(historyAdapter);


    }

    public void getTotalBalance(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.getTotalBalance(token, userId).enqueue(new Callback<TotalBalanceModel>() {
            @Override
            public void onResponse(Call<TotalBalanceModel> call, Response<TotalBalanceModel> response) {

                TotalBalanceModel totalBalanceModel = response.body();

                if (response.isSuccessful()) {

                    TotalBalanceModel.TotalBalance model = totalBalanceModel.getTotalBalance();
                    dialog.dismiss();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<TotalBalanceModel> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {

        Intent intent;
        intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
        overridePendingTransition(0, 0);
        super.onBackPressed();

    }
}