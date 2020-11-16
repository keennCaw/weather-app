package com.keennhoward.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;

    OwmApi owmApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        owmApi = retrofit.create(OwmApi.class);

        //getWeatherWithCity("Manila&appid=9e966537e45e8f205eea944edbef0264");
        //getWeatherWithCity("Manila", "9e966537e45e8f205eea944edbef0264");
        Requests requests = new Requests(owmApi);
        requests.getWeatherWithCity("Manila", "9e966537e45e8f205eea944edbef0264");
        //txtResult.setText(weathers.toString());

    }

/*
    public void getWeatherWithCity(String city,String key){

       Call<CurrentWeather> call = owmApi.getWeather(city, key);

       Log.d("URL", call.request().url().toString());

       call.enqueue(new Callback<CurrentWeather>() {
           @Override
           public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {

              Log.d("response", response.body().toString());
              CurrentWeather currentWeather = response.body();
               List<Weather> weather = currentWeather.getWeather();
               txtResult.setText(weather.toString());
           }

           @Override
           public void onFailure(Call<CurrentWeather> call, Throwable t) {
               Log.d("response", t.getMessage());
                txtResult.setText(t.getMessage());
           }
       });
    } */
}
