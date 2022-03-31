package com.in.akshartrade.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TotalBalanceModel {

    @SerializedName("status")
    @Expose
    Boolean status;


    @SerializedName("message")
    @Expose
    String message;

    @SerializedName("data")
    @Expose
    TotalBalance totalBalance;

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

    public TotalBalance getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(TotalBalance totalBalance) {
        this.totalBalance = totalBalance;
    }

    public class TotalBalance {

        @SerializedName("current_value")
        @Expose
        String current_value;

        @SerializedName("total_invest")
        @Expose
        String total_invest;

        @SerializedName("profit_and_lost")
        @Expose
        String profit_and_lost;

        public String getCurrent_value() {
            return current_value;
        }

        public void setCurrent_value(String current_value) {
            this.current_value = current_value;
        }

        public String getTotal_invest() {
            return total_invest;
        }

        public void setTotal_invest(String total_invest) {
            this.total_invest = total_invest;
        }

        public String getProfit_and_lost() {
            return profit_and_lost;
        }

        public void setProfit_and_lost(String profit_and_lost) {
            this.profit_and_lost = profit_and_lost;
        }
    }
}
