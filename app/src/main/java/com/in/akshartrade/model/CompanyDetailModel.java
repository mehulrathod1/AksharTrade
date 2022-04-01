package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CompanyDetailModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    CompanyData companyData;

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

    public CompanyData getCompanyData() {
        return companyData;
    }

    public void setCompanyData(CompanyData companyData) {
        this.companyData = companyData;
    }

    public static class CompanyData {


        @SerializedName("instrument_token")
        @Expose
        String  instrument_token;


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

        public String getExchange() {
            return exchange;
        }

        public void setExchange(String exchange) {
            this.exchange = exchange;
        }

        @SerializedName("HistoricalData")
        @Expose
        HistoricalData  historicalData;

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


        public HistoricalData getHistoricalData() {
            return historicalData;
        }

        public void setHistoricalData(HistoricalData historicalData) {
            this.historicalData = historicalData;
        }

        public class HistoricalData {


            @SerializedName("date")
            @Expose
            String date;


            @SerializedName("last_trade_time")
            @Expose
            String last_trade_time;

            @SerializedName("lower_circuit_limit")
            @Expose
            String lower_circuit_limit;

            @SerializedName("upper_circuit_limit")
            @Expose
            String upper_circuit_limit;


            @SerializedName("last_price")
            @Expose
            String last_price;

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

            @SerializedName("volume")
            @Expose
            String volume;

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

            public String getLast_trade_time() {
                return last_trade_time;
            }

            public void setLast_trade_time(String last_trade_time) {
                this.last_trade_time = last_trade_time;
            }

            public String getLower_circuit_limit() {
                return lower_circuit_limit;
            }

            public void setLower_circuit_limit(String lower_circuit_limit) {
                this.lower_circuit_limit = lower_circuit_limit;
            }

            public String getUpper_circuit_limit() {
                return upper_circuit_limit;
            }

            public void setUpper_circuit_limit(String upper_circuit_limit) {
                this.upper_circuit_limit = upper_circuit_limit;
            }

            public String getLast_price() {
                return last_price;
            }

            public void setLast_price(String last_price) {
                this.last_price = last_price;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
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

            public String getVolume() {
                return volume;
            }

            public void setVolume(String volume) {
                this.volume = volume;
            }
        }
    }
}
