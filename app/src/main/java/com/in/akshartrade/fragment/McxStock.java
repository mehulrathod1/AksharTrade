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

public class McxStock extends Fragment {


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
        view =  inflater.inflate(R.layout.fragment_mcx_stock, container, false);
        init();
        nseListData();
        return view;
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



}