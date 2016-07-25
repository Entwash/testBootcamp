package com.example.jakub.gittest;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {

    List<Day> dayList;
    DayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

//        list = (RecyclerView) findViewById(R.id.listView);
//        adapter = new DayAdapter(this, getListFromJSON(readFile()));
//        layoutManager = new LinearLayoutManager(this);
//        list.setAdapter(adapter);
//        list.setLayoutManager(layoutManager);
//        list.setItemAnimator(new DefaultItemAnimator());
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
//        itemTouchHelper.attachToRecyclerView(list);
    }

//    ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//        @Override
//        public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder viewHolder1) {
//            return false;
//        }
//
//        @Override
//        public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
//            final int position = viewHolder.getAdapterPosition();
//            try {
//                JSONArray array = new JSONArray(readFile());
//                array.remove(position);
//                saveData(array);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            petsList.remove(position);
//            adapter.notifyDataSetChanged();
//        }
//    };
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

    public class DayAdapter extends BaseAdapter
    {

        LayoutInflater inflater;
        ArrayList<Day> data;

        public DayAdapter(Activity context, ArrayList<Day> data) {
            super();
            this.data = data;
            this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub

            Day item = data.get(position);

            View view=convertView;

            if(convertView==null)
                view = inflater.inflate(R.layout.item_day), null);
            TextView app = (TextView) view.findViewById(R.id.app_textView);
            TextView day = (TextView) view.findViewById(R.id.day_textView);
            TextView plan = (TextView) view.findViewById(R.id.plan_textView);
            TextView topic = (TextView) view.findViewById(R.id.topic_textView);

            app.setText(String.valueOf(item.getApp()));
            day.setText(String.valueOf(item.getName()));
            plan.setText(String.valueOf(item.getPlan()));
            topic.setText(String.valueOf(item.getTopic()));

            return view;
        }
    }

}
