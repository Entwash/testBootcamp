package com.example.jakub.gittest;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jakub on 25.07.2016.
 */
public interface DayService {
    @GET("schedule.json")
    Call<ResponseBody> getJSON();
}
