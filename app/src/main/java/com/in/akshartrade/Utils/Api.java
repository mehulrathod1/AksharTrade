package com.in.akshartrade.Utils;

import com.in.akshartrade.Model.AddOrderModel;
import com.in.akshartrade.Model.CommonModel;
import com.in.akshartrade.Model.CompanyDetailModel;
import com.in.akshartrade.Model.LoginModel;
import com.in.akshartrade.Model.OrderModel;
import com.in.akshartrade.Model.SearchModel;
import com.in.akshartrade.Model.SenSexDataModel;
import com.in.akshartrade.Model.StockDetailModel;
import com.in.akshartrade.Model.TotalBalanceModel;
import com.in.akshartrade.Model.UserProfileModel;
import com.in.akshartrade.Model.WatchListModel;

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


    @FormUrlEncoded
    @POST("get_share_details")
    Call<CompanyDetailModel> getCompanyDetail(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("instrument_token") String instrument_token
    );

    @FormUrlEncoded
    @POST("get_watch_list")
    Call<WatchListModel> getWatchList(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("currentpage") String currentPage
    );


    @FormUrlEncoded
    @POST("add_share_order")
    Call<AddOrderModel> addShareOrder(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("instrument_token") String instrument_token,
            @Field("stake") String stake,
            @Field("quantity") String quantity,
            @Field("price") String price,
            @Field("orders") String order_type,
            @Field("name") String name,
            @Field("exchange") String exchange,
            @Field("lot_size") String lot_size
    );


    @FormUrlEncoded
    @POST("get_order_list")
    Call<OrderModel> getOrder(

            @Field("token") String token,
            @Field("user_id") String user_id
    );

    @FormUrlEncoded
    @POST("get_trade_list")
    Call<OrderModel> getTrade(

            @Field("token") String token,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("get_history_list")
    Call<OrderModel> getHistory(

            @Field("token") String token,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("get_sensex_data")
    Call<SenSexDataModel> getSenSexData(

            @Field("token") String token,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("get_nifty_data")
    Call<SenSexDataModel> getNiftyData(

            @Field("token") String token,
            @Field("user_id") String user_id

    );

    @FormUrlEncoded
    @POST("sell_share_order")
    Call<CommonModel> sellShare(

            @Field("token") String token,
            @Field("user_id") String user_id,
            @Field("instrument_token") String instrument_token,
            @Field("stake") String stake,
            @Field("quantity") String quantity,
            @Field("price") String price,
            @Field("orders") String order_type,
            @Field("name") String name,
            @Field("exchange") String exchange,
            @Field("lot_size") String lot_size

    );


    @FormUrlEncoded
    @POST("get_total_balance")
    Call<TotalBalanceModel> getTotalBalance(

            @Field("token") String token,
            @Field("user_id") String user_id

    );

}
