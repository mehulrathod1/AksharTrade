package com.in.akshartrade.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    View view;
    RecyclerView orderRecycler;
    OrderAdapter orderAdapter;
    List<OrderModel> orderList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_history, container, false);
        init();
        orderData();
        return view;
    }

    public void init() {
        orderRecycler = view.findViewById(R.id.orderRecycler);
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
        orderRecycler.setLayoutManager(layoutManager);
        orderAdapter.notifyDataSetChanged();
        orderRecycler.setAdapter(orderAdapter);

    }
}