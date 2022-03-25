package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class SearchModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<SearchData> searchDataList = new ArrayList<>();

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

    public List<SearchData> getSearchDataList() {
        return searchDataList;
    }

    public void setSearchDataList(List<SearchData> searchDataList) {
        this.searchDataList = searchDataList;
    }

    public static class SearchData {

        @SerializedName("instrument_token")
        @Expose
        String instrument_token;


        @SerializedName("exchange_token")
        @Expose
        String exchange_token;


        @SerializedName("name")
        @Expose
        String name;


        @SerializedName("segment")
        @Expose
        String segment;


        @SerializedName("exchange")
        @Expose
        String exchange;

        public SearchData(String instrument_token, String exchange_token, String name, String segment, String exchange) {
            this.instrument_token = instrument_token;
            this.exchange_token = exchange_token;
            this.name = name;
            this.segment = segment;
            this.exchange = exchange;
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

        public String getSegment() {
            return segment;
        }

        public void setSegment(String segment) {
            this.segment = segment;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }
    }
}
