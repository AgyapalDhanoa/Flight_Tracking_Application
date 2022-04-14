package com.agya.dhanoa.flight_track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AirlineAdapter mExampleAdapter;
    private ArrayList<AirlineItem> mExampleList;
    private RequestQueue mRequestQueue;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

//https://api.flightapi.io/compschedule/6205d08f13b15b74ee7b9a4e?mode=arrivals&day=2&iata=YYC



    }

    private void parseJSON() {
      //  String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=Airline&image_type=photo&pretty=true";
String url= "https://api.flightapi.io/nearby/62583ac48f9b0d2fcef79dca?country=Canada&token=dHA9MCNsb2M9MzUxNTQyMTkjbG5nPTMzI3BsPTIjhfjdsfk5MzQxI2xicz0xNDoxNjcwNTczMQ==";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Nearby Airports");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String Airport = hit.getString("Airport");
                                String code = hit.getString("Code");
                                String Title = hit.getString("Title");

                                mExampleList.add(new AirlineItem(code, Airport, Title));
                            }

                            mExampleAdapter = new AirlineAdapter(MainActivity.this, mExampleList);
                            mRecyclerView.setAdapter(mExampleAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }




}