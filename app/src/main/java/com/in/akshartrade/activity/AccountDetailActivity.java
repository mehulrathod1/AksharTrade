package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.progressDialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.Model.UserProfileModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailActivity extends AppCompatActivity {

    ImageView backIcon;
    TextView userName;
    EditText userEmail, userMobileNumber;
    Button update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        init();
        clickEvent();
        getUserProfile(token, userId);
    }

    public void init() {

        progressDialog(this);
        backIcon = findViewById(R.id.backIcon);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userMobileNumber = findViewById(R.id.userMobileNumber);
        update = findViewById(R.id.update);
    }

    public void clickEvent() {
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                updateDetail(token,userName.getText().toString(),userMobileNumber.getText().toString(), userId
                );
            }
        });
    }

    public void getUserProfile(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.getUserProfile(token, userId).enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {

                UserProfileModel userProfileModel = response.body();
                if (response.isSuccessful()) {
                    UserProfileModel.UserProfileData model = userProfileModel.getUserData();
                    userName.setText(model.getName());
                    userEmail.setText(model.getEmail());
                    userMobileNumber.setText(model.getMobile_no());
                    dialog.dismiss();
                }
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<UserProfileModel> call, Throwable t) {
                dialog.dismiss();
            }
        });
    }

    public void updateDetail(String token, String name, String phone, String user_id) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();

        call.updateUserProfile(token, name, phone, user_id).enqueue(new Callback<CommonModel>() {
            @Override
            public void onResponse(Call<CommonModel> call, Response<CommonModel> response) {

                CommonModel commonModel = response.body();

                Toast.makeText(AccountDetailActivity.this, "" + commonModel.getMessage(), Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

            @Override
            public void onFailure(Call<CommonModel> call, Throwable t) {

                dialog.dismiss();
            }
        });

    }
}