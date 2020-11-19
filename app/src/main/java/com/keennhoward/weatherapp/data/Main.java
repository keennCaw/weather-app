package com.keennhoward.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class Main {

    public double getTemp() {
        return temp;
    }

    @SerializedName("temp")
    private double temp;

}
