package com.in.akshartrade.model;

public class NseStockModel {


    String companyName, stockCategory, stockPrice, livePrice, livePercentage;

    public NseStockModel(String companyName, String stockCategory, String stockPrice, String livePrice, String livePercentage) {
        this.companyName = companyName;
        this.stockCategory = stockCategory;
        this.stockPrice = stockPrice;
        this.livePrice = livePrice;
        this.livePercentage = livePercentage;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }

    public String getStockPrice() {
        return stockPrice;
    }

    public void setStockPrice(String stockPrice) {
        this.stockPrice = stockPrice;
    }

    public String getLivePrice() {
        return livePrice;
    }

    public void setLivePrice(String livePrice) {
        this.livePrice = livePrice;
    }

    public String getLivePercentage() {
        return livePercentage;
    }

    public void setLivePercentage(String livePercentage) {
        this.livePercentage = livePercentage;
    }
}
