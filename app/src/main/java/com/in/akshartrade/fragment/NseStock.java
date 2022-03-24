package com.in.akshartrade.Fragment;

import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.Model.StockDetailModel;
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

public class NseStock extends Fragment {

    View view;
    RecyclerView stockRecycler;
    NseStockAdapter nseStockAdapter;
    List<NseStockModel> nseStockModelList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_nse_stock, container, false);

        init();
        nseListData();
        getNseData(token,userId);

        return view;

//        Dialog dialog = new Dialog(context, android.R.style.Theme_Light);
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//        dialog.setContentView(R.layout.MyCustomDialogLayout);
//        dialog.show();

    }

    public void init() {
        stockRecycler = view.findViewById(R.id.recyclerview);
    }

    public void nseListData() {


        NseStockModel model = new NseStockModel("RELIANCE", "NSE EQ", "₹ 2089.05", "- 45.20", " (2.12%)");
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);
        nseStockModelList.add(model);

        nseStockAdapter = new NseStockAdapter(nseStockModelList, getContext(), new NseStockAdapter.Click() {
            @Override
            public void onItemClick(int position) {

                Intent intent = new Intent(getContext(), CompanyDetailActivity.class);
                startActivity(intent);

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        stockRecycler.setLayoutManager(layoutManager);
        stockRecycler.setAdapter(nseStockAdapter);
    }

    public void getNseData(String token, String user_id) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);


        call.getNseData(token, user_id).enqueue(new Callback<StockDetailModel>() {
            @Override
            public void onResponse(Call<StockDetailModel> call, Response<StockDetailModel> response) {

                StockDetailModel stockDetailModel = response.body();

                List<StockDetailModel.StockDetail> dataList = stockDetailModel.getStockDetailList();

                for (int i = 0; i < dataList.size(); i++) {

                    StockDetailModel.StockDetail model = dataList.get(i);

                    StockDetailModel.StockDetail data = new StockDetailModel.StockDetail(

                            model.getInstrument_token(),
                            model.getExchange_token(),
                            model.getName(),
                            model.getLast_price(),
                            model.getTick_size(),
                            model.getLot_size(),
                            model.getInstrument_type(),
                            model.getSegment()
                    );

                    Log.e("data", "onResponse: "+model.getExchange_token() );
                }
            }

            @Override
            public void onFailure(Call<StockDetailModel> call, Throwable t) {

            }
        });
    }
}