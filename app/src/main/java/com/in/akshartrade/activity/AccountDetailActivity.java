package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.progressDialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.Model.UserProfileModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URISyntaxException;

import io.socket.client.IO;
import io.socket.emitter.Emitter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountDetailActivity extends AppCompatActivity {

    ImageView backIcon;
    TextView userName;
    EditText userEmail, userMobileNumber;
    Button update;
    ProgressBar progressBar;

    private io.socket.client.Socket mSocket;

    {
        try {
            mSocket = IO.socket("https://akshartrading.notionprojects.tech/public/api/get_user_profile");


        } catch (URISyntaxException e) {
            Log.d("myTag", e.getMessage());
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_detail);

        init();
        clickEvent();
        getUserProfile(token, userId);

        mSocket.connect();

        setListening();
    }

    public void init() {

        progressDialog(this);
        backIcon = findViewById(R.id.backIcon);
        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userMobileNumber = findViewById(R.id.userMobileNumber);
        update = findViewById(R.id.update);
        progressBar = findViewById(R.id.progressBar);

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

                updateDetail(token, userName.getText().toString(), userMobileNumber.getText().toString(), userId
                );
            }
        });
    }

    public void getUserProfile(String token, String userId) {

        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
//        dialog.show();
        progressBar.setVisibility(View.VISIBLE);


        call.getUserProfile(token, userId).enqueue(new Callback<UserProfileModel>() {
            @Override
            public void onResponse(Call<UserProfileModel> call, Response<UserProfileModel> response) {

                UserProfileModel userProfileModel = response.body();
                if (response.isSuccessful()) {
                    UserProfileModel.UserProfileData model = userProfileModel.getUserData();
                    userName.setText(model.getName());
                    userEmail.setText(model.getEmail());
                    userMobileNumber.setText(model.getMobile_no());
//                    dialog.dismiss();
                    progressBar.setVisibility(View.GONE);

                }
                progressBar.setVisibility(View.GONE);
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


    private void setListening() {
        mSocket.on("CHAT", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                try {
                    JSONObject messageJson = new JSONObject("data");
                    String message = messageJson.getString("name");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
//                            text.setText(message);

                            Log.e("text", "run: "+ message);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("text", "run: "+ "e.getMessage()");

                }
            }
        });
    }
}