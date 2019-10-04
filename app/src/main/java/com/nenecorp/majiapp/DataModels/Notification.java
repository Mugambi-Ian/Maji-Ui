package com.nenecorp.majiapp.DataModels;

import java.util.ArrayList;

public class Notification {
    public static final String P = "Payments", O = "Others", BnS = "Bills & Statements";
    private String type;
    private String text;
    private String date;
    private ArrayList<String> list;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Notification(String type, String text, String date, ArrayList<String> list) {
        this.type = type;
        this.text = text;
        this.date = date;
        this.list = list;
    }
}
