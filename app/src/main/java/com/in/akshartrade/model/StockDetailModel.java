package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class StockDetailModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<StockDetail> stockDetailList = new ArrayList<>();

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StockDetail> getStockDetailList() {
        return stockDetailList;
    }

    public void setStockDetailList(List<StockDetail> stockDetailList) {
        this.stockDetailList = stockDetailList;
    }

    public static class StockDetail {

        @SerializedName("instrument_token")
        @Expose
        String instrument_token;

        @SerializedName("exchange_token")
        @Expose
        String exchange_token;


        @SerializedName("name")
        @Expose
        String name;


        @SerializedName("last_price")
        @Expose
        String last_price;


        @SerializedName("tick_size")
        @Expose
        String tick_size;


        @SerializedName("lot_size")
        @Expose
        String lot_size;


        @SerializedName("instrument_type")
        @Expose
        String instrument_type;


        @SerializedName("segment")
        @Expose
        String segment;

        public StockDetail(String instrument_token, String exchange_token, String name, String last_price, String tick_size, String lot_size, String instrument_type, String segment) {
            this.instrument_token = instrument_token;
            this.exchange_token = exchange_token;
            this.name = name;
            this.last_price = last_price;
            this.tick_size = tick_size;
            this.lot_size = lot_size;
            this.instrument_type = instrument_type;
            this.segment = segment;
        }

        public String getInstrument_token() {
            return instrument_token;
        }

        public void setInstrument_token(String instrument_token) {
            this.instrument_token = instrument_token;
        }

        public String getExchange_token() {
            return exchange_token;
        }

        public void setExchange_token(String exchange_token) {
            this.exchange_token = exchange_token;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLast_price() {
            return last_price;
        }

        public void setLast_price(String last_price) {
            this.last_price = last_price;
        }

        public String getTick_size() {
            return tick_size;
        }

        public void setTick_size(String tick_size) {
            this.tick_size = tick_size;
        }

        public String getLot_size() {
            return lot_size;
        }

        public void setLot_size(String lot_size) {
            this.lot_size = lot_size;
        }

        public String getInstrument_type() {
            return instrument_type;
        }

        public void setInstrument_type(String instrument_type) {
            this.instrument_type = instrument_type;
        }

        public String getSegment() {
            return segment;
        }

        public void setSegment(String segment) {
            this.segment = segment;
        }
    }
}
