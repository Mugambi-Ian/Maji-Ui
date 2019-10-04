package com.nenecorp.majiapp.DataModels;

public class Account {
    String accountNumber;
    String waterProvider;

    public Account(String accountNumber, String waterProvider) {
        this.accountNumber = accountNumber;
        this.waterProvider = waterProvider;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getWaterProvider() {
        return waterProvider;
    }

    public void setWaterProvider(String waterProvider) {
        this.waterProvider = waterProvider;
    }
}


