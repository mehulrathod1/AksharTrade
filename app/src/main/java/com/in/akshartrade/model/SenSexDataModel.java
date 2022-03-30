package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SenSexDataModel {
    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    SenSEexData senSEexData ;

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

    public SenSEexData getSenSEexData() {
        return senSEexData;
    }

    public void setSenSEexData(SenSEexData senSEexData) {
        this.senSEexData = senSEexData;
    }

    public class SenSEexData {

        @SerializedName("date")
        @Expose
        String date;

        @SerializedName("last_price")
        @Expose
        String last_price;

        @SerializedName("net_change")
        @Expose
        String net_change;

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

        @SerializedName("PL_sign")
        @Expose
        String PL_sign;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLast_price() {
            return last_price;
        }

        public void setLast_price(String last_price) {
            this.last_price = last_price;
        }

        public String getNet_change() {
            return net_change;
        }

        public void setNet_change(String net_change) {
            this.net_change = net_change;
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

        public String getProfit_and_lost() {
            return profit_and_lost;
        }

        public void setProfit_and_lost(String profit_and_lost) {
            this.profit_and_lost = profit_and_lost;
        }

        public String getPL_sign() {
            return PL_sign;
        }

        public void setPL_sign(String PL_sign) {
            this.PL_sign = PL_sign;
        }
    }
}
