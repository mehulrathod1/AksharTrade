package com.in.akshartrade.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.switchmaterial.SwitchMaterial;
import com.in.akshartrade.Adapter.OptionalChainAdapter;
import com.in.akshartrade.Adapter.OptionalChainReverse;
import com.in.akshartrade.Adapter.OrderAdapter;
import com.in.akshartrade.Model.OptionalChainModel;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.R;

import java.util.ArrayList;
import java.util.List;

public class CompanyOptionChainFragment extends Fragment {


    View view;
    RecyclerView optionalRecycler;
    OptionalChainAdapter optionalChainAdapter;
    List<OptionalChainModel> optionalChainList = new ArrayList<>();
    List<OptionalChainModel> optionalChainReverseList = new ArrayList<>();
    OptionalChainReverse optionalChainReverseAdapter;
    SwitchMaterial viewSwitch;
    LinearLayout layoutView, layoutViewReverse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_company_option_chain, container, false);

        init();
        optionalData();

        return view;
    }

    public void init() {

        optionalRecycler = view.findViewById(R.id.optionalRecycler);
        viewSwitch = view.findViewById(R.id.view_switch);
        layoutView = view.findViewById(R.id.layoutView);
        layoutViewReverse = view.findViewById(R.id.layoutViewReverse);

        viewSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (b) {
//                    Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
                    optionalReverseData();
                    layoutView.setVisibility(View.GONE);
                    layoutViewReverse.setVisibility(View.VISIBLE);
                } else {
//                    Toast.makeText(getContext(), "unchecked", Toast.LENGTH_SHORT).show();
                    optionalData();


                    layoutView.setVisibility(View.VISIBLE);
                    layoutViewReverse.setVisibility(View.GONE);

                }

            }
        });
    }

    public void optionalData() {

        OptionalChainModel model = new OptionalChainModel("22.61", "1.14", "-9.20%",
                "50.29", "-31.54%", "202200");

        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);
        optionalChainList.add(model);


        optionalChainAdapter = new OptionalChainAdapter(optionalChainList, getContext(), new OptionalChainAdapter.Click() {
            @Override
            public void onItmClick(int position) {

            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        optionalRecycler.setLayoutManager(layoutManager);
        optionalChainAdapter.notifyDataSetChanged();
        optionalRecycler.setAdapter(optionalChainAdapter);
    }

    public void optionalReverseData() {

        OptionalChainModel model = new OptionalChainModel("22.61", "1.14", "-9.20%",
                "50.29", "-31.54%", "202200");

        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);
        optionalChainReverseList.add(model);


        optionalChainReverseAdapter = new OptionalChainReverse(optionalChainReverseList, getContext(), new OptionalChainAdapter.Click() {
            @Override
            public void onItmClick(int position) {


            }

        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        optionalRecycler.setLayoutManager(layoutManager);
        optionalChainReverseAdapter.notifyDataSetChanged();
        optionalRecycler.setAdapter(optionalChainReverseAdapter);
    }

}