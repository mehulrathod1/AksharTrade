package com.in.akshartrade.Model;

public class OrderModel {

    String companyName, ltpPrice, plPrice, stockCategory;

    public OrderModel(String companyName, String ltpPrice, String plPrice, String stockCategory) {
        this.companyName = companyName;
        this.ltpPrice = ltpPrice;
        this.plPrice = plPrice;
        this.stockCategory = stockCategory;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getLtpPrice() {
        return ltpPrice;
    }

    public void setLtpPrice(String ltpPrice) {
        this.ltpPrice = ltpPrice;
    }

    public String getPlPrice() {
        return plPrice;
    }

    public void setPlPrice(String plPrice) {
        this.plPrice = plPrice;
    }

    public String getStockCategory() {
        return stockCategory;
    }

    public void setStockCategory(String stockCategory) {
        this.stockCategory = stockCategory;
    }
}
