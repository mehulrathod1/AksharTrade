package com.in.akshartrade.Model;

public class OptionalChainModel {

    String percentage, rupeeInLakh, rupeePercentage, ltpOne, ltpSecond, strikePrice;

    public OptionalChainModel(String percentage, String rupeeInLakh, String rupeePercentage, String ltpOne, String ltpSecond, String strikePrice) {
        this.percentage = percentage;
        this.rupeeInLakh = rupeeInLakh;
        this.rupeePercentage = rupeePercentage;
        this.ltpOne = ltpOne;
        this.ltpSecond = ltpSecond;
        this.strikePrice = strikePrice;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getRupeeInLakh() {
        return rupeeInLakh;
    }

    public void setRupeeInLakh(String rupeeInLakh) {
        this.rupeeInLakh = rupeeInLakh;
    }

    public String getRupeePercentage() {
        return rupeePercentage;
    }

    public void setRupeePercentage(String rupeePercentage) {
        this.rupeePercentage = rupeePercentage;
    }

    public String getLtpOne() {
        return ltpOne;
    }

    public void setLtpOne(String ltpOne) {
        this.ltpOne = ltpOne;
    }

    public String getLtpSecond() {
        return ltpSecond;
    }

    public void setLtpSecond(String ltpSecond) {
        this.ltpSecond = ltpSecond;
    }

    public String getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(String strikePrice) {
        this.strikePrice = strikePrice;
    }
}
