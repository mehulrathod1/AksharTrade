package com.in.akshartrade.Dialog;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Activity.SearchActivity;
import com.in.akshartrade.Adapter.TabCompanyDetailAdapter;
import com.in.akshartrade.Adapter.TabStockAdapter;
import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.CompanyDetailModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CompanyDetailActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;

    ImageView dialogClose, bookMark;
    TextView sellStock, buyStock, tradingSymbol, companyName, stockPrice, exchange, percentageVal, profitAndLost;

    Handler handler = new Handler();
    Runnable runnable;
    long delay = 2000;
    String instrumentToken;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        this.setFinishOnTouchOutside(false);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        Intent intent = getIntent();
        instrumentToken = intent.getStringExtra("instrumentToken");
        Glob.instrumentalToken = instrumentToken;

        Log.e(Glob.TAG, "onCreate: "+instrumentToken );


        init();
        clickEvent();
        getCompanyDetail(token, userId, instrumentToken);
        autoUpdate();

    }

    public void init() {


        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);
        dialogClose = findViewById(R.id.dialogClose);
        buyStock = findViewById(R.id.buyStock);
        sellStock = findViewById(R.id.sellStock);
        tradingSymbol = findViewById(R.id.tradingSymbol);
        companyName = findViewById(R.id.companyName);
        stockPrice = findViewById(R.id.stockPrice);
        exchange = findViewById(R.id.exchange);
        bookMark = findViewById(R.id.bookMark);
        tabLayout.addTab(tabLayout.newTab().setText("Overview"));
        tabLayout.addTab(tabLayout.newTab().setText("Option Chain"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        percentageVal = findViewById(R.id.percentageVal);
        profitAndLost = findViewById(R.id.profitAndLost);

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

        bookMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                removeFromWatchlist(token, userId, Glob.instrumentalToken);
            }
        });
    }

    public void clickEvent() {

        dialogClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        sellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void getCompanyDetail(String token, String user_id, String instrument_token) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);


        call.getCompanyDetail(token, user_id, instrument_token).enqueue(new Callback<CompanyDetailModel>() {
            @Override
            public void onResponse(Call<CompanyDetailModel> call, Response<CompanyDetailModel> response) {

                CompanyDetailModel companyDetailModel = response.body();
                if (response.isSuccessful()) {
                    CompanyDetailModel.CompanyData companyData = companyDetailModel.getCompanyData();
                    CompanyDetailModel.CompanyData.HistoricalData historicalData = companyData.getHistoricalData();


                    double volumeValue = Double.parseDouble(historicalData.getVolume());
                    double openValue = Double.parseDouble(historicalData.getOpen());
                    double highValue = Double.parseDouble(historicalData.getHigh());
                    double lowValue = Double.parseDouble(historicalData.getLow());
                    double closeValue = Double.parseDouble(historicalData.getClose());
                    double lowerCircuitValue = Double.parseDouble(historicalData.getLower_circuit_limit());
                    double upperCircuitValue = Double.parseDouble(historicalData.getUpper_circuit_limit());
                    double lastPriceValue = Double.parseDouble(historicalData.getLast_price());


                    percentageVal.setText("("+historicalData.getPercentage_val()+")");
                    profitAndLost.setText("??? " + historicalData.getProfit_and_lost());
                    tradingSymbol.setText(companyData.getTradingsymbol());
                    companyName.setText(companyData.getName());
                    exchange.setText(companyData.getExchange());
                    stockPrice.setText("??? " + historicalData.getLast_price());

                }

            }

            @Override
            public void onFailure(Call<CompanyDetailModel> call, Throwable t) {

            }
        });
    }

    public void getLiveCompanyDetail(String token, String user_id, String instrument_token) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);


        call.getCompanyDetail(token, user_id, instrument_token).enqueue(new Callback<CompanyDetailModel>() {
            @Override
            public void onResponse(Call<CompanyDetailModel> call, Response<CompanyDetailModel> response) {

                CompanyDetailModel companyDetailModel = response.body();

                if (response.isSuccessful()) {

                    CompanyDetailModel.CompanyData companyData = companyDetailModel.getCompanyData();
                    CompanyDetailModel.CompanyData.HistoricalData historicalData = companyData.getHistoricalData();


                    percentageVal.setText("("+historicalData.getPercentage_val()+")");
                    profitAndLost.setText("??? " + historicalData.getProfit_and_lost());
                    tradingSymbol.setText(companyData.getTradingsymbol());
                    companyName.setText(companyData.getName());
                    exchange.setText(companyData.getExchange());
                    stockPrice.setText("??? " + historicalData.getLast_price());

//                volume.setText(new DecimalFormat("##.##").format(volumeValue));
//                open.setText(new DecimalFormat("##.##").format(openValue));
//                high.setText(new DecimalFormat("##.##").format(highValue));
//                low.setText(new DecimalFormat("##.##").format(lowValue));
//                close.setText(new DecimalFormat("##.##").format(closeValue));
//                lowerCircuit.setText(new DecimalFormat("##.##").format(lowerCircuitValue));
//                upperCircuit.setText(new DecimalFormat("##.##").format(upperCircuitValue));

                }
            }

            @Override
            public void onFailure(Call<CompanyDetailModel> call, Throwable t) {


            }
        });
    }


    public void removeFromWatchlist(String token, String userId, String instrumentToken) {


        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.removeFromWatchlist(token, userId, instrumentToken).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();

                Toast.makeText(CompanyDetailActivity.this, "" + commonModel.getMessage(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                Log.e("TAG", "onFailure: "+t.getMessage() );
            }

        });
    }

    public void autoUpdate() {

        handler.postDelayed(new Runnable() {
            public void run() {


                getLiveCompanyDetail(token, userId, instrumentToken);

                handler.postDelayed(this, delay);
            }
        }, delay);
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable); //stop handler when activity not visible super.onPause();
        super.onPause();
    }


}