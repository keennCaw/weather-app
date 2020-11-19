package com.keennhoward.weatherapp.data;

import com.google.gson.annotations.SerializedName;

public class Sys {

    public String getCountry() {
        return country;
    }

    @SerializedName("country")
    private String country;


}
