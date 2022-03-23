package com.in.akshartrade.Activity;

import static com.in.akshartrade.Utils.Glob.dialog;
import static com.in.akshartrade.Utils.Glob.token;
import static com.in.akshartrade.Utils.Glob.userId;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.R;
import com.in.akshartrade.Utils.Api;
import com.in.akshartrade.Utils.Glob;
import com.in.akshartrade.Utils.RetrofitClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SignIn extends AppCompatActivity {

    Button btnSignIn;
    ImageView backIcon;
    TextView edtPassword, edtEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
//        this.setFinishOnTouchOutside(false);

        init();
        clickEvent();
    }

    public void init() {
        Glob.progressDialog(this);
        btnSignIn = findViewById(R.id.btnSignIn);
        backIcon = findViewById(R.id.backIcon);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void clickEvent() {

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (edtEmail.getText().toString().equals("")) {
                    edtEmail.setError("Please Enter Email");
                } else if (edtPassword.getText().toString().equals("")) {
                    edtPassword.setError("Please Enter UserId");
                } else {

                    Log.e("TAG", "onClick: "+edtEmail.getText().toString()+"----"+edtPassword.getText().toString());
                    login(token, edtEmail.getText().toString(), edtPassword.getText().toString());
                }

            }
        });

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void login(String token, String email, String password) {


        Api call = RetrofitClient.getClient(Glob.baseUrl).create(Api.class);
        dialog.show();


        call.login(token, email, password).enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                LoginModel loginModel = response.body();


                LoginModel.LoginData model = loginModel.getLoginData();
                Toast.makeText(getApplicationContext(), "" + loginModel.getMessage(), Toast.LENGTH_SHORT).show();

                userId = model.getUser_id();
                dialog.dismiss();

                SharedPreferences.Editor editor = getSharedPreferences("MyPref", MODE_PRIVATE).edit();
                editor.putString("token", "123456789");
                editor.putString("id", userId);
                editor.apply();
                editor.commit();


                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {

                dialog.dismiss();

                Toast.makeText(getApplicationContext(), "" + "Please Enter Valid Detail", Toast.LENGTH_SHORT).show();

            }
        });

    }
}