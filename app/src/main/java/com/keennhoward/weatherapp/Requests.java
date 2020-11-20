package com.keennhoward.weatherapp;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.keennhoward.weatherapp.data.CurrentWeather;
import com.keennhoward.weatherapp.data.Weather;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Requests {


    public Requests(OwmApi owmApi, TextView mainTextView, TextView cityTextView, TextView tempTextView, TextView descTextView, TextView countryTextView, ImageView iconImageView, CardView cardView, TextView txtResult) {
        this.owmApi = owmApi;
        this.mainTextView = mainTextView;
        this.cityTextView = cityTextView;
        this.tempTextView = tempTextView;
        this.descTextView = descTextView;
        this.countryTextView = countryTextView;
        this.iconImageView = iconImageView;
        this.cardView = cardView;
        this.txtResult = txtResult;
    }

    OwmApi owmApi;
    TextView mainTextView, cityTextView, tempTextView, descTextView, countryTextView, txtResult;
    CardView cardView;
    ImageView iconImageView;
    List<Weather> weather;

    public void getWeatherWithCity(String city,String key) {

        Call<CurrentWeather> call = owmApi.getWeather(city, key);

        Log.d("URL", call.request().url().toString());

        call.enqueue(new Callback<CurrentWeather>() {
            @Override
            public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                if(response.isSuccessful()) {
                    double celsius;
                    Log.d("response", response.body().toString());
                    CurrentWeather currentWeather = response.body();
                    weather = currentWeather.getWeather();
                    mainTextView.setText(weather.get(0).getMain());
                    descTextView.setText(weather.get(0).getDescription());
                    Picasso.get().load("https://openweathermap.org/img/wn/" + weather.get(0).getIcon() + ".png").into(iconImageView);

                    celsius = (currentWeather.getMain().getTemp() - 273.15);

                    tempTextView.setText(String.format("%.0f", celsius) + "\u2103");
                    countryTextView.setText(currentWeather.getSys().getCountry());
                    cityTextView.setText(currentWeather.getName());
                    Log.d("weather", weather.toString() + celsius);
                    cardView.setVisibility(View.VISIBLE);
                }else{
                    try {

                        cardView.setVisibility(View.INVISIBLE);
                        txtResult.setText(response.message());
                    }catch(Exception e){
                        cardView.setVisibility(View.INVISIBLE);
                        txtResult.setText(response.errorBody().toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<CurrentWeather> call, Throwable t) {
                txtResult.setText("Network Error!");
                Log.d("response", t.getMessage());
            }
        });

    }
}
