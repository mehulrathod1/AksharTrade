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
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.Model.SenSexDataModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TradeBookActivity extends AppCompatActivity {

    RecyclerView tradeRecycler;
    OrderAdapter tradeAdapter;
    List<OrderModel.OrderData> tradeBookList = new ArrayList<>();
    BottomNavigationView bottomNavigationView;

    ImageView profile;
    TextView senSexPrice, niftyPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_book);

        init();
        clickEvent();
        orderData();
        getTrade(token,userId);
        getSenSexData(token, userId);
        getNiftyData(token, userId);

    }

    public void init() {


        progressDialog(this);
        tradeRecycler = findViewById(R.id.tradeRecycler);
        profile = findViewById(R.id.profile);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        senSexPrice = findViewById(R.id.senSexPrice);
        niftyPrice = findViewById(R.id.niftyPrice);



        bottomNavigationView.setSelectedItemId(R.id.tradeBook);
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

    public void getTrade(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.getTrade(token, userId).enqueue(new Callback<OrderModel>() {
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
                        tradeBookList.add(data);

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


        tradeAdapter = new OrderAdapter(tradeBookList, getApplicationContext(), new OrderAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        tradeRecycler.setLayoutManager(layoutManager);
        tradeAdapter.notifyDataSetChanged();
        tradeRecycler.setAdapter(tradeAdapter);


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
    public void getSenSexData(String token, String userId) {


        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();


        call.getSenSexData(token, userId).enqueue(new Callback<SenSexDataModel>() {
            @Override
            public void onResponse(Call<SenSexDataModel> call, Response<SenSexDataModel> response) {

                SenSexDataModel senSexDataModel = response.body();

                if (response.isSuccessful()) {
                    SenSexDataModel.SenSEexData model = senSexDataModel.getSenSEexData();
                    senSexPrice.setText("₹ "+ model.getLast_price());

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
                    niftyPrice.setText("₹ "+ model.getLast_price());

//                dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SenSexDataModel> call, Throwable t) {

//                dialog.dismiss();
            }
        });
    }

}