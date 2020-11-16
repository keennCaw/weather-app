package com.keennhoward.weatherapp;

import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requests {


    public Requests(OwmApi owmApi, TextView textView) {
        this.owmApi = owmApi;
        this.textView = textView;
    }

    OwmApi owmApi;
    TextView textView;
    List<Weather> weather;

    public void getWeatherWithCity(String city,String key) {

        Call<CurrentWeather> call = owmApi.getWeather(city, key);

        Log.d("URL", call.request().url().toString());

        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

                Log.d("response", response.body().toString());
                CurrentWeather currentWeather = response.body();
                weather = currentWeather.getWeather();
                textView.setText(weather.toString());
                Log.d("weather", weather.toString());
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                Log.d("response", t.getMessage());
                textView.setText(t.getMessage());
            }
        });

    }
}
