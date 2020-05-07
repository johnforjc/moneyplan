package com.example.moneyplan;

public class MyObject {
    private String date;
    private String namaCatatan;
    private String jumlah;

    public MyObject(String date, String namaCatatan, String jumlah) {
        this.date = date;
        this.namaCatatan = namaCatatan;
        this.jumlah = jumlah;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNamaCatatan() {
        return namaCatatan;
    }

    public void setNamaCatatan(String namaCatatan) {
        this.namaCatatan = namaCatatan;
    }

    public String getJumlah() {
        return jumlah;
    }

    public void setJumlah(String jumlah) {
        this.jumlah = jumlah;
    }


}
