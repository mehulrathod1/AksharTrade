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
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.Model.SenSexDataModel;
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
    Button showDetail;
    TextView currentValue, totalInvestment, todayProfitLoss, profitAndLoss;
    TextView senSexPrice, niftyPrice;

    Handler handler = new Handler();
    Runnable runnable;
    long delay = 2000;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        init();
        clickEvent();
        getHistory(token, userId);
        getTotalBalance(token, userId);
        getSenSexData(token, userId);
        getNiftyData(token, userId);

        autoUpdate();

    }

    public void init() {

        progressDialog(this);
        historyRecycler = findViewById(R.id.orderRecycler);
        profile = findViewById(R.id.profile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        showDetail = findViewById(R.id.showDetail);
        senSexPrice = findViewById(R.id.senSexPrice);
        niftyPrice = findViewById(R.id.niftyPrice);
        progressBar = findViewById(R.id.progressBar);


        bottomSheetDialog = new BottomSheetDialog(HistoryActivity.this);
        bottomSheetDialog.setContentView(R.layout.history_dialog_layout);

        currentValue = bottomSheetDialog.findViewById(R.id.currentValue);
        totalInvestment = bottomSheetDialog.findViewById(R.id.totalInvestment);
        todayProfitLoss = bottomSheetDialog.findViewById(R.id.todayProfitLoss);
        profitAndLoss = bottomSheetDialog.findViewById(R.id.profitAndLoss);

        showDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bottomSheetDialog.show();
            }
        });

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
//        dialog.show();
        progressBar.setVisibility(View.VISIBLE);


        call.getHistory(token, userId).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {
                historyList.clear();
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
//                    dialog.dismiss();
                    progressBar.setVisibility(View.GONE);

                }
                else {
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<OrderModel> call, Throwable t) {
                progressBar.setVisibility(View.VISIBLE);

            }
        });


    }

    public void getLiveHistory(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getHistory(token, userId).enqueue(new Callback<OrderModel>() {
            @Override
            public void onResponse(Call<OrderModel> call, Response<OrderModel> response) {

                historyList.clear();
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
                    if (historyAdapter != null) {

                        historyAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

//                        dialog.dismiss();
                    } else {
                        orderData();
                        progressBar.setVisibility(View.GONE);

                    }
                }
//                dialog.dismiss();
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

    public void getSenSexData(String token, String userId) {


        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getSenSexData(token, userId).enqueue(new Callback<SenSexDataModel>() {
            @Override
            public void onResponse(Call<SenSexDataModel> call, Response<SenSexDataModel> response) {

                SenSexDataModel senSexDataModel = response.body();

                if (response.isSuccessful()) {
                    SenSexDataModel.SenSEexData model = senSexDataModel.getSenSEexData();
                    senSexPrice.setText("₹ " + model.getLast_price());

//                dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SenSexDataModel> call, Throwable t) {

//                dialog.dismiss();
            }
        });
    }

    public void getNiftyData(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getNiftyData(token, userId).enqueue(new Callback<SenSexDataModel>() {
            @Override
            public void onResponse(Call<SenSexDataModel> call, Response<SenSexDataModel> response) {

                SenSexDataModel senSexDataModel = response.body();

                if (response.isSuccessful()) {
                    SenSexDataModel.SenSEexData model = senSexDataModel.getSenSEexData();
                    niftyPrice.setText("₹ " + model.getLast_price());

//                dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SenSexDataModel> call, Throwable t) {

//                dialog.dismiss();
            }
        });
    }

    public void getTotalBalance(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getTotalBalance(token, userId).enqueue(new Callback<TotalBalanceModel>() {
            @Override
            public void onResponse(Call<TotalBalanceModel> call, Response<TotalBalanceModel> response) {

                TotalBalanceModel totalBalanceModel = response.body();

                if (response.isSuccessful()) {

                    TotalBalanceModel.TotalBalance model = totalBalanceModel.getTotalBalance();

                    currentValue.setText("₹ " + model.getCurrent_value());
                    totalInvestment.setText("₹ " + model.getTotal_invest());
                    profitAndLoss.setText("₹ " + model.getProfit_and_lost());
                    todayProfitLoss.setText("₹ " + model.getProfit_and_lost());
                    dialog.dismiss();
                }
//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<TotalBalanceModel> call, Throwable t) {

            }
        });
    }

    public void getLiveTotalBalance(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getTotalBalance(token, userId).enqueue(new Callback<TotalBalanceModel>() {
            @Override
            public void onResponse(Call<TotalBalanceModel> call, Response<TotalBalanceModel> response) {

                TotalBalanceModel totalBalanceModel = response.body();

                if (response.isSuccessful()) {

                    TotalBalanceModel.TotalBalance model = totalBalanceModel.getTotalBalance();

                    currentValue.setText("₹ " + model.getCurrent_value());
                    totalInvestment.setText("₹ " + model.getTotal_invest());
                    profitAndLoss.setText("₹ " + model.getProfit_and_lost());
                    todayProfitLoss.setText("₹ " + model.getProfit_and_lost());
//                    dialog.dismiss();
                }
//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<TotalBalanceModel> call, Throwable t) {

            }
        });
    }

    public void autoUpdate() {

        handler.postDelayed(new Runnable() {
            public void run() {


                getSenSexData(token, userId);
                getNiftyData(token, userId);
                getLiveHistory(token, userId);
                getLiveTotalBalance(token, userId);
                handler.postDelayed(this, delay);


            }
        }, delay);
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
        super.onPause();
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