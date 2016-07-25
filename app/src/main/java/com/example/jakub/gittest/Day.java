package com.example.jakub.gittest;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Jakub on 25.07.2016.
 */
public class Day {

    String app;
    String name;
    String plan;
    String topic;

    public Day(String app, String name, String plan, String topic) {
        this.app = app;
        this.name = name;
        this.plan = plan;
        this.topic = topic;
    }
    public Day(String JSON) {
        try {
            JSONObject pokemonJSON = new JSONObject(JSON);
            app = pokemonJSON.getString("app");
            name = pokemonJSON.getString("name");
            plan = pokemonJSON.getString("plan");
            topic = pokemonJSON.getString("topic");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public Day(JSONObject day) {
        try {
            app = day.getString("app");
            name = day.getString("name");
            plan = day.getString("plan");
            topic = day.getString("topic");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getApp() {
        return app;
    }

    public String getName() {
        return name;
    }

    public String getPlan() {
        return plan;
    }

    public String getTopic() {
        return topic;
    }

}
