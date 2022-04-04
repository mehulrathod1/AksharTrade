package com.in.akshartrade.Fragment;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.Model.StockDetailModel;
import com.in.akshartrade.Model.WatchListModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Adapter.NseStockAdapter;
import com.in.akshartrade.Model.NseStockModel;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchListThree extends Fragment {


    View view;
    RecyclerView stockRecycler;
    NseStockAdapter nseStockAdapter;
    List<WatchListModel.WatchListData> nseStockModelList = new ArrayList<>();


    Handler handler = new Handler();
    Runnable runnable;
    long delay = 5000;

    ProgressBar progressBar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_mcx_stock, container, false);

        init();
        getWatchList(token, userId, "3");
        autoUpdate();


        return view;
    }

    public void init() {
        stockRecycler = view.findViewById(R.id.recyclerview);
        progressBar = view.findViewById(R.id.progressBar);
    }

    public void getWatchList(String token, String userId, String currentPage) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        progressBar.setVisibility(View.VISIBLE);


        call.getWatchList(token, userId, currentPage).enqueue(new Callback<WatchListModel>() {
            @Override
            public void onResponse(Call<WatchListModel> call, Response<WatchListModel> response) {

                nseStockModelList.clear();
                WatchListModel watchListModel = response.body();

                if (response.isSuccessful()) {
                    List<WatchListModel.WatchListData> dataList = watchListModel.getWatchListDataList();

                    for (int i = 0; i < dataList.size(); i++) {

                        WatchListModel.WatchListData model = dataList.get(i);

                        WatchListModel.WatchListData data = new WatchListModel.WatchListData(
                                model.getInstrument_token(),
                                model.getExchange_token(),
                                model.getTradingsymbol(),
                                model.getName(),
                                model.getExchange(), model.getChart_data()
                        );
                        nseStockModelList.add(data);

                    }
                    nseListData();
                    progressBar.setVisibility(View.GONE);

//                    dialog.dismiss();
                } else {
                    progressBar.setVisibility(View.VISIBLE);

                }
//                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<WatchListModel> call, Throwable t) {

//                dialog.dismiss();
                progressBar.setVisibility(View.VISIBLE);

            }
        });
    }

    public void getLiveWatchList(String token, String userId, String currentPage) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);


        call.getWatchList(token, userId, currentPage).enqueue(new Callback<WatchListModel>() {
            @Override
            public void onResponse(Call<WatchListModel> call, Response<WatchListModel> response) {

                nseStockModelList.clear();
                WatchListModel watchListModel = response.body();

                if (response.isSuccessful()) {
                    List<WatchListModel.WatchListData> dataList = watchListModel.getWatchListDataList();

                    for (int i = 0; i < dataList.size(); i++) {

                        WatchListModel.WatchListData model = dataList.get(i);

                        WatchListModel.WatchListData data = new WatchListModel.WatchListData(
                                model.getInstrument_token(),
                                model.getExchange_token(),
                                model.getTradingsymbol(),
                                model.getName(),
                                model.getExchange(), model.getChart_data()
                        );
                        nseStockModelList.add(data);

                    }
                    if (nseStockAdapter != null) {
                        nseStockAdapter.notifyDataSetChanged();
                        progressBar.setVisibility(View.GONE);

                    } else {
                        nseListData();
                        progressBar.setVisibility(View.GONE);

                    }
                }
            }

            @Override
            public void onFailure(Call<WatchListModel> call, Throwable t) {

            }
        });
    }


    public void nseListData() {


        nseStockAdapter = new NseStockAdapter(nseStockModelList, getContext(), new NseStockAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }

            @Override
            public void onBuyClick(int position) {
                String instrumentToken = nseStockModelList.get(position).getInstrument_token();
                Intent intent = new Intent(getContext(), CompanyDetailActivity.class);
                intent.putExtra("instrumentToken", instrumentToken);
                startActivity(intent);
            }

            @Override
            public void onSellClick(int position) {
                String instrumentToken = nseStockModelList.get(position).getInstrument_token();
                Intent intent = new Intent(getContext(), CompanyDetailActivity.class);
                intent.putExtra("instrumentToken", instrumentToken);
                startActivity(intent);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stockRecycler.setLayoutManager(layoutManager);
        stockRecycler.setAdapter(nseStockAdapter);
    }

    public void autoUpdate() {

        handler.postDelayed(new Runnable() {
            public void run() {

                if (getActivity() != null) {

                    getLiveWatchList(token, userId, "3");
                }
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