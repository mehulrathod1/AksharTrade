package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WatchListModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    List<WatchListData> watchListDataList = new ArrayList<>();

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

    public List<WatchListData> getWatchListDataList() {
        return watchListDataList;
    }

    public void setWatchListDataList(List<WatchListData> watchListDataList) {
        this.watchListDataList = watchListDataList;
    }

    public static class WatchListData {

        @SerializedName("instrument_token")
        @Expose
        String instrument_token;

        @SerializedName("exchange_token")
        @Expose
        String exchange_token;


        @SerializedName("tradingsymbol")
        @Expose
        String tradingsymbol;


        @SerializedName("name")
        @Expose
        String name;


        @SerializedName("exchange")
        @Expose
        String exchange;




        @SerializedName("chart_data")
        @Expose
        WatchListChartData chart_data;

        public WatchListData(String instrument_token, String exchange_token, String tradingsymbol, String name, String exchange, WatchListChartData chart_data) {
            this.instrument_token = instrument_token;
            this.exchange_token = exchange_token;
            this.tradingsymbol = tradingsymbol;
            this.name = name;
            this.exchange = exchange;
            this.chart_data = chart_data;
        }

        public WatchListChartData getChart_data() {
            return chart_data;
        }

        public void setChart_data(WatchListChartData chart_data) {
            this.chart_data = chart_data;
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

        public String getTradingsymbol() {
            return tradingsymbol;
        }

        public void setTradingsymbol(String tradingsymbol) {
            this.tradingsymbol = tradingsymbol;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        public static class WatchListChartData {

            @SerializedName("date")
            @Expose
            String date;



            @SerializedName("last_trade_time")
            @Expose
            String last_trade_time;



            @SerializedName("last_price")
            @Expose
            String last_price;



            @SerializedName("volume")
            @Expose
            String volume;



            @SerializedName("open")
            @Expose
            String open;


            @SerializedName("high")
            @Expose
            String high;


            @SerializedName("low")
            @Expose
            String low;


            @SerializedName("close")
            @Expose
            String close;


            @SerializedName("profit_and_lost")
            @Expose
            String profit_and_lost;


            @SerializedName("percentage_val")
            @Expose
            String percentage_val;

            public String getProfit_and_lost() {
                return profit_and_lost;
            }

            public void setProfit_and_lost(String profit_and_lost) {
                this.profit_and_lost = profit_and_lost;
            }

            public String getPercentage_val() {
                return percentage_val;
            }

            public void setPercentage_val(String percentage_val) {
                this.percentage_val = percentage_val;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getLast_trade_time() {
                return last_trade_time;
            }

            public void setLast_trade_time(String last_trade_time) {
                this.last_trade_time = last_trade_time;
            }

            public String getLast_price() {
                return last_price;
            }

            public void setLast_price(String last_price) {
                this.last_price = last_price;
            }

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }

            public String getOpen() {
                return open;
            }

            public void setOpen(String open) {
                this.open = open;
            }

            public String getHigh() {
                return high;
            }

            public void setHigh(String high) {
                this.high = high;
            }

            public String getLow() {
                return low;
            }

            public void setLow(String low) {
                this.low = low;
            }

            public String getClose() {
                return close;
            }

            public void setClose(String close) {
                this.close = close;
            }
        }
    }
}
