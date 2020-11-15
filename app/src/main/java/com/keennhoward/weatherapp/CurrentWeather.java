package com.keennhoward.weatherapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CurrentWeather {


    public List<Weather> getWeather() {
        return weather;
    }

    @SerializedName("weather")
    private List<Weather> weather;


}
