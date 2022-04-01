package com.in.akshartrade.Fragment;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.resources.TextAppearance;
import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.Model.AddOrderModel;
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


public class CompanyOverviewFragment extends Fragment {

    View view;

    TextView volume, bid, ask, openInterest, atp, lowerCircuit, open, high, low, close, upperCircuit,
            quantity, totalPrice, buyStock, sellStock;

    String instrumentToken, companyName, exchange;


    Handler handler = new Handler();
    Runnable runnable;
    long delay = 5000;

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
        quantity = view.findViewById(R.id.quantity);
        totalPrice = view.findViewById(R.id.totalPrice);
        buyStock = view.findViewById(R.id.buyStock);
        sellStock = view.findViewById(R.id.sellStock);
        quantity.setText("1");

        quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

//                if (charSequence.length() != 0) {
//                    String totalQuantity = quantity.getText().toString();
//                    double o = Double.parseDouble(totalQuantity);
//                    String price = totalPrice.getText().toString();
//                    double priceInDouble = Double.parseDouble(price);
//
//                    double stockWisePrice = o * priceInDouble;
//
//                    String lastPrice = String.valueOf(stockWisePrice);
//                    totalPrice.setText(lastPrice);
//
//                    Log.e("onTextChanged", "onTextChanged: " + stockWisePrice + "-------" + totalQuantity);
//                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        buyStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addShareOrder(token, userId,
                        instrumentToken,
                        "Q",
                        quantity.getText().toString(),
                        totalPrice.getText().toString(),
                        "market",
                        companyName,
                        exchange,
                        "0"
                );
            }
        });

        sellStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                sellStockOrder(token, userId,
                        instrumentToken,
                        "Q",
                        quantity.getText().toString(),
                        totalPrice.getText().toString(),
                        "market",
                        companyName,
                        exchange,
                        "0"
                );
            }
        });

    }

    public void getCompanyDetail(String token, String user_id, String instrument_token) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.getCompanyDetail(token, user_id, instrument_token).enqueue(new Callback<CompanyDetailModel>() {
            @Override
            public void onResponse(Call<CompanyDetailModel> call, Response<CompanyDetailModel> response) {

                CompanyDetailModel companyDetailModel = response.body();

                if (response.isSuccessful()) {
                    CompanyDetailModel.CompanyData companyData = companyDetailModel.getCompanyData();
                    CompanyDetailModel.CompanyData.HistoricalData historicalData = companyData.getHistoricalData();


                    volume.setText(historicalData.getVolume());
                    open.setText(historicalData.getOpen());
                    high.setText(historicalData.getHigh());
                    low.setText(historicalData.getLow());
                    close.setText(historicalData.getClose());
                    lowerCircuit.setText(historicalData.getLower_circuit_limit());
                    upperCircuit.setText(historicalData.getUpper_circuit_limit());
                    totalPrice.setText(historicalData.getLast_price());

                    instrumentToken = companyData.getInstrument_token();
                    companyName = companyData.getName();
                    exchange = companyData.getExchange();

                    dialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CompanyDetailModel> call, Throwable t) {

                dialog.dismiss();
            }
        });
    }

    public void addShareOrder(String token, String user_id, String instrument_token,
                              String stake, String quantity, String price, String order_type,
                              String name, String exchange,
                              String lot_size) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.addShareOrder(token, user_id, instrument_token, stake, quantity, price, order_type,
                name, exchange, lot_size).enqueue(new Callback<AddOrderModel>() {
            @Override
            public void onResponse(Call<AddOrderModel> call, Response<AddOrderModel> response) {

                AddOrderModel commonModel = response.body();

                Toast.makeText(getContext(), "" + commonModel.getMessage(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<AddOrderModel> call, Throwable t) {

                dialog.dismiss();
            }
        });
    }

    public void sellStockOrder(String token, String user_id, String instrument_token,
                               String stake, String quantity, String price, String order_type,
                               String name, String exchange,
                               String lot_size) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.sellShare(token, user_id, instrument_token, stake, quantity, price, order_type,
                name, exchange, lot_size).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();

                Toast.makeText(getContext(), "" + commonModel.getMessage(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {
                dialog.dismiss();

            }
        });

    }

    public void scheduleSendLocation() {

        handler.postDelayed(new Runnable() {
            public void run() {

                if (getContext().equals(null)) {

                } else {

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