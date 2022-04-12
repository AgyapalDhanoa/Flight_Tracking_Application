package com.agya.dhanoa.flight_track;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {


private TextView settext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
settext = findViewById(R.id.textset);
        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        OkHttpClient client = new OkHttpClient();
        String url = "https://api.flightapi.io/compschedule/6205d08f13b15b74ee7b9a4e?mode=departures&day=1&iata=YYC";
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
if(response.isSuccessful()){
    String myResponse = response.body().string();

    MainActivity.this.runOnUiThread(new Runnable() {
        @Override
        public void run() {
            settext.setText(myResponse);
        }
    });
}
            }
        });

//https://api.flightapi.io/compschedule/6205d08f13b15b74ee7b9a4e?mode=arrivals&day=2&iata=YYC
// https://api.flightapi.io/compschedule/6205d08f13b15b74ee7b9a4e?mode=departure&day=2&iata=YYC
//        TabLayout tabLayout = findViewById(R.id.tab_layout);
//        // Set the text for each tab.
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label1));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label2));
//        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_label3));
//        // Set the tabs to fill the entire layout.
//        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
//
//        final ViewPager viewPager = findViewById(R.id.pager);
//        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
//        viewPager.setAdapter(adapter);
//
//        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//            }
//        });



    }
}