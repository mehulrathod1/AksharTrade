package com.in.akshartrade.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.R;
import com.in.akshartrade.Adapter.NseStockAdapter;
import com.in.akshartrade.Model.NseStockModel;

import java.util.ArrayList;
import java.util.List;


public class BseStock extends Fragment {

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
        view = inflater.inflate(R.layout.fragment_bse_stock, container, false);
        init();
//        nseListData();
        return view;
    }

    public void init() {
        stockRecycler = view.findViewById(R.id.recyclerview);

    }


}
