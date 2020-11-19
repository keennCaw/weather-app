package com.keennhoward.weatherapp.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;


public class CurrentWeather {

    @SerializedName("weather")
    private List<Weather> weather;

    @SerializedName("main")
    private Main main;

    @SerializedName("sys")
    private Sys sys;



    @SerializedName("name")
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }
    public Main getMain() {
        return main;
    }

    public Sys getSys() {
        return sys;
    }

    public String getName() {
        return name;
    }

}
