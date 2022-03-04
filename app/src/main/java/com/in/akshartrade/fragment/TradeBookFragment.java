package com.in.akshartrade.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.in.akshartrade.Activity.AccountDetailActivity;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.ArrayList;
import java.util.List;

public class TradeBookFragment extends Fragment {

    View view;
    RecyclerView tradeRecycler;
    OrderAdapter orderAdapter;
    List<OrderModel> orderList = new ArrayList<>();

    ImageView profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_trade_book, container, false);
        init();
        clickEvent();
        orderData();
        return view;
    }

    public void init() {

        tradeRecycler = view.findViewById(R.id.tradeRecycler);
            profile = view.findViewById(R.id.profile);
    }

    public void clickEvent() {

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), AccountDetailActivity.class);
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

        orderAdapter = new OrderAdapter(orderList, getActivity(), new OrderAdapter.Click() {
            @Override
            public void onItemClick(int position) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        tradeRecycler.setLayoutManager(layoutManager);
        orderAdapter.notifyDataSetChanged();
        tradeRecycler.setAdapter(orderAdapter);

    }
}