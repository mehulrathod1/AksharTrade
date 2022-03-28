package com.in.akshartrade.Fragment;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.resources.TextAppearance;
import com.in.akshartrade.Model.CompanyDetailModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CompanyOverviewFragment extends Fragment {

    View view;

    TextView volume, bid, ask, openInterest, atp, lowerCircuit, open, high, low, close, upperCircuit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_company_overview, container, false);


        init();
        getCompanyDetail(token, userId, Glob.instrumentalToken);

        return view;
    }

    public void init() {


        Glob.progressDialog(getContext());
        volume = view.findViewById(R.id.volume);
        bid = view.findViewById(R.id.bid);
        ask = view.findViewById(R.id.ask);
        openInterest = view.findViewById(R.id.openInterest);
        atp = view.findViewById(R.id.atp);
        lowerCircuit = view.findViewById(R.id.lowerCircuit);
        open = view.findViewById(R.id.open);
        high = view.findViewById(R.id.high);
        low = view.findViewById(R.id.low);
        close = view.findViewById(R.id.close);
        upperCircuit = view.findViewById(R.id.upperCircuit);

    }

    public void getCompanyDetail(String token, String user_id, String instrument_token) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.getCompanyDetail(token, user_id, instrument_token).enqueue(new Callback<CompanyDetailModel>() {
            @Override
            public void onResponse(Call<CompanyDetailModel> call, Response<CompanyDetailModel> response) {

                CompanyDetailModel companyDetailModel = response.body();
                CompanyDetailModel.CompanyData companyData = companyDetailModel.getCompanyData();
                CompanyDetailModel.CompanyData.HistoricalData historicalData = companyData.getHistoricalData();


                double volumeValue = Double.parseDouble(historicalData.getVolume());
                double openValue = Double.parseDouble(historicalData.getOpen());
                double highValue = Double.parseDouble(historicalData.getHigh());
                double lowValue = Double.parseDouble(historicalData.getLow());
                double closeValue = Double.parseDouble(historicalData.getClose());
                double lowerCircuitValue = Double.parseDouble(historicalData.getLower_circuit_limit());
                double upperCircuitValue = Double.parseDouble(historicalData.getUpper_circuit_limit());

                volume.setText(new DecimalFormat("##.##").format(volumeValue));
                open.setText(new DecimalFormat("##.##").format(openValue));
                high.setText(new DecimalFormat("##.##").format(highValue));
                low.setText(new DecimalFormat("##.##").format(lowValue));
                close.setText(new DecimalFormat("##.##").format(closeValue));
                lowerCircuit.setText(new DecimalFormat("##.##").format(lowerCircuitValue));
                upperCircuit.setText(new DecimalFormat("##.##").format(upperCircuitValue));


//                volume.setText(historicalData.getVolume());
//                open.setText(historicalData.getOpen());
//                high.setText(historicalData.getHigh());
//                low.setText(historicaDlata.getLow());
//                close.setText(historicalData.getClose());


                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CompanyDetailModel> call, Throwable t) {

                dialog.dismiss();
            }
        });
    }

}