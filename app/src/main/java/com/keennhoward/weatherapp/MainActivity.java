package com.keennhoward.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView txtResult;
    EditText cityEditText;
    OwmApi owmApi;

    private static final String API_KEY = "9e966537e45e8f205eea944edbef0264";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResult = findViewById(R.id.txtResult);
        cityEditText = findViewById(R.id.cityEditText);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        owmApi = retrofit.create(OwmApi.class);

        cityEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH){
                    Log.d("search", cityEditText.getText().toString());
                    Requests requests = new Requests(owmApi, txtResult);
                    requests.getWeatherWithCity(cityEditText.getText().toString(), API_KEY);
                    return true;
                }
                return false;
            }
        });


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
