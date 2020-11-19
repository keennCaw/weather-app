package com.keennhoward.weatherapp;

import com.keennhoward.weatherapp.data.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OwmApi {

/*
    @GET("/data/2.5/weather?q={cityName}&appid=9e966537e45e8f205eea944edbef0264")
    Call<CurrentWeather> getWeather(@Path("cityName") String city);*/

    @GET("/data/2.5/weather")
    Call<CurrentWeather> getWeather(@Query(value = "q", encoded = true) String city, @Query(value = "appid",encoded = true) String key);

}
