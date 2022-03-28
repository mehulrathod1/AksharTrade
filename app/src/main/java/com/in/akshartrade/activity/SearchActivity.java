package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.progressDialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.akshartrade.Adapter.SearchAdapter;
import com.in.akshartrade.Dialog.CompanyDetailActivity;
import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.SearchModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {


    TextView edtSearch;
    ImageView clearSearch,back;
    RecyclerView searchRecycler;
    SearchAdapter searchAdapter;
    List<SearchModel.SearchData> searchList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        init();


    }

    public void init() {

        Glob.progressDialog(this);
        searchRecycler = findViewById(R.id.searchRecycler);
        edtSearch = findViewById(R.id.edtSearch);
        clearSearch = findViewById(R.id.clearSearch);
        back = findViewById(R.id.back);

        clearSearch.setVisibility(View.GONE);

        clearSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtSearch.setText("");
                clearSearch.setVisibility(View.GONE);

            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if (charSequence.length() != 0) {


                    Log.e("onTextChanged", "onTextChanged: " + edtSearch.getText().toString());
//                    getSearchData(token, userId, edtSearch.getText().toString());

                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

                getSearchData(token, userId, edtSearch.getText().toString());
                clearSearch.setVisibility(View.VISIBLE);

            }
        });


    }


    public void getSearchData(String token, String user_id, String search_keyword) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);


        call.getSearchData(token, user_id, search_keyword).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {

                SearchModel searchModel = response.body();

                searchList.clear();
                if (searchModel != null) {
                    List<SearchModel.SearchData> dataList = searchModel.getSearchDataList();
                    for (int i = 0; i < dataList.size(); i++) {

                        SearchModel.SearchData model = dataList.get(i);

                        SearchModel.SearchData data = new SearchModel.SearchData(
                                model.getInstrument_token(), model.getExchange_token(), model.getName(),
                                model.getSegment(), model.getExchange()
                        );

                        searchList.add(data);
                    }
                    searchData();
                } else {
                    Toast.makeText(SearchActivity.this, "No data found", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {
            }
        });

    }

    public void searchData() {


        searchAdapter = new SearchAdapter(searchList, getApplicationContext(), new SearchAdapter.Click() {
            @Override
            public void itemCLick(int position) {


                String instrumentToken = searchList.get(position).getInstrument_token();
                Intent intent = new Intent(getApplicationContext(), CompanyDetailActivity.class);
                intent.putExtra("instrumentToken",instrumentToken);
                startActivity(intent);
            }

            @Override
            public void addWatchListClick(int position) {

                String instrumentToken = searchList.get(position).getInstrument_token();

                addToWatchlist(token, userId, instrumentToken);

//                removeFromWatchlist(token,userId,instrumentToken);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        searchRecycler.setLayoutManager(layoutManager);
        searchAdapter.notifyDataSetChanged();
        searchRecycler.setAdapter(searchAdapter);

    }

    public void addToWatchlist(String token, String userId, String instrumentToken) {


        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.addToWatchlist(token, userId, instrumentToken).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();

                Toast.makeText(SearchActivity.this, "" + commonModel.getMessage(), Toast.LENGTH_SHORT).show();

                dialog.dismiss();

            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                dialog.dismiss();

            }
        });
    }


}