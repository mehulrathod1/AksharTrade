package com.in.akshartrade.Utils;

import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.Model.SearchModel;
import com.in.akshartrade.Model.StockDetailModel;
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


    @FormUrlEncoded
    @POST("search_api")
    Call<SearchModel> getSearchData(
            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("search_keyword") String search_keyword
    );

    @FormUrlEncoded
    @POST("add_to_watchlist")
    Call<CommonModel> addToWatchlist(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("instrument_token") String instrument_token
    );

    @FormUrlEncoded
    @POST("remove_in_watchlist")
    Call<CommonModel> removeFromWatchlist(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("instrument_token") String instrument_token
    );
}
