package com.in.akshartrade.Utils;

import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.Model.UserProfileModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> login(
            @Field("token") String token,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("get_user_profile")
    Call<UserProfileModel> getUserProfile(
            @Field("token") String token,
            @Field("user_id") String user_id
    );

}