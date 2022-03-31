package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.progressDialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.in.akshartrade.Adapter.TabStockAdapter;
import com.in.akshartrade.Model.SenSexDataModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.gesture.ContainerScrollType;
import lecho.lib.hellocharts.gesture.ZoomType;
import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.ChartData;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.view.Chart;
import lecho.lib.hellocharts.view.LineChartView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    TextView txt_Search, senSexPrice, niftyPrice;
    ImageView profile, watchList;
    BottomNavigationView bottomNavigationView;
    LineChartView lineChart ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        clickEvent();
        getSenSexData(token, userId);
        getNiftyData(token, userId);

//        lineChart = findViewById(R.id.lineChartll);
//        lineChart = new LineChartView(getApplicationContext());

//        setLineChart();

    }


    public void init() {

//        progressDialog(this);

        tabLayout = findViewById(R.id.tab);
        viewPager = findViewById(R.id.pager);
        profile = findViewById(R.id.profile);
        watchList = findViewById(R.id.watchList);
        txt_Search = findViewById(R.id.txt_Search);
        senSexPrice = findViewById(R.id.senSexPrice);
        niftyPrice = findViewById(R.id.niftyPrice);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);



        tabLayout.addTab(tabLayout.newTab().setText("WatchList 1"));
        tabLayout.addTab(tabLayout.newTab().setText("WatchList 2"));
        tabLayout.addTab(tabLayout.newTab().setText("WatchList 3"));
        tabLayout.addTab(tabLayout.newTab().setText("WatchList 4"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        TabStockAdapter tabStockAdapter = new TabStockAdapter(getSupportFragmentManager(), getApplicationContext(), tabLayout.getTabCount());
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
        bottomNavigationView.setSelectedItemId(R.id.dashboard);
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

        txt_Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);

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

        watchList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WatchListActivity.class);
                startActivity(intent);
            }
        });
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
                    senSexPrice.setText(model.getLast_price());

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
                    niftyPrice.setText(model.getLast_price());

//                dialog.dismiss();

                }
            }

            @Override
            public void onFailure(Call<SenSexDataModel> call, Throwable t) {

//                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

//    public void setLineChart(){
//
//
//        try {
//
//
//            lineChart.setInteractive(true);
//            lineChart.setZoomType(ZoomType.HORIZONTAL);
//
//
//            List<PointValue> values = new ArrayList<PointValue>();
//            values.add(new PointValue(19, 25));
//            values.add(new PointValue(12, 44));
//            values.add(new PointValue(20, 35));
//            values.add(new PointValue(30, 44));
//
//            Line line = new Line(values).setColor(Color.parseColor("#00000"));
//            line.setStrokeWidth(2);
//            line.setFilled(true);
//            List<Line> lines = new ArrayList<Line>();
//            lines.add(line);
//            LineChartData data = new LineChartData();
//            data.setLines(lines);
//
//
//            lineChart.setLineChartData(data);
//            Log.d("Exception", "setLineChart: "+data.getLines().size());
//
//        }
//        catch (Exception e){
//
//            Log.e("Exception", "setLineChart: "+e.getMessage());
//        }
//
//    }

}