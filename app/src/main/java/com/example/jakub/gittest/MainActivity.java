package com.example.jakub.gittest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    TextView resultView;
    List<Day> dayList;

    ListView listView;
    ArrayAdapter<Day> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultView = (TextView) findViewById(R.id.result_textView);
        findViewById(R.id.getJSON_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData();
            }
        });


    }

    public void getData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://bootcampagenda.firebaseio.com/")
                .build();
        DayService services = retrofit.create(DayService.class);

        services.getJSON().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    resultView.setText(String.valueOf(response.body().string()));
                    addData(String.valueOf(response.body().string()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Check your internet connection !", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void addData(String JSON) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(JSON);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray days = null;
        try {
            days = jsonObject.getJSONArray("days");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < days.length(); i++) {
            try {
                Day d = new Day((JSONObject) days.get(i));
                dayList.add(d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}

