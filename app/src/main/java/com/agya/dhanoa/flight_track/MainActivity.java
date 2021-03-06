package com.agya.dhanoa.flight_track;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AirlineAdapter.OnAirlineListener{
    private String Airport;
    private String code;
    private String Title;
    private RecyclerView mRecyclerView;
    public ImageView imageView;
    private AirlineAdapter mExampleAdapter;
    private ArrayList<AirlineItem> mExampleList;


    private RequestQueue mRequestQueue;
private String Java = "Canada";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        imageView = findViewById(R.id.image_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mExampleList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(this);
        parseJSON();

//https://api.flightapi.io/compschedule/6205d08f13b15b74ee7b9a4e?mode=arrivals&day=2&iata=YYC

    }


    private void parseJSON() {


        String url = "https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q="+Java+"&image_type=photo&pretty=true";
        mRequestQueue = Volley.newRequestQueue(this);
        Log.i("Current Value", Java);
//String url= "https://api.flightapi.io/nearby/62583ac48f9b0d2fcef79dca?country="+Java+"&token=dHA9MCNsb2M9MzUxNTQyMTkjbG5nPTMzI3BsPTIjhfjdsfk5MzQxI2xicz0xNDoxNjcwNTczMQ==";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {

                        JSONArray jsonArray = response.getJSONArray("hits");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject hit = jsonArray.getJSONObject(i);


                             Airport = hit.getString("tags");
                             code = hit.getString("type");
                             Title = hit.getString("downloads");

                            mExampleList.add(new AirlineItem(code, Airport, Title));

                        }

                        if(mExampleList.size()==0){
                            Toast.makeText(this, "No Items to display", Toast.LENGTH_SHORT).show();
                        }
                        mExampleAdapter = new AirlineAdapter(MainActivity.this, mExampleList,this);
                        mRecyclerView.setAdapter(mExampleAdapter);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);

        mRequestQueue.add(request);
    }

    public void delete(){
        mExampleList.clear();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu, menu);

        MenuItem item = menu.findItem(R.id.search);
        androidx.appcompat.widget.SearchView search = (androidx.appcompat.widget.SearchView) item.getActionView();
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Java = query;
                delete();
              parseJSON();


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }
//
//
    int Pos;
    @Override
    public void onNoteClick(int position) {
//        mExampleList.get(position);
Pos =position;
        Intent  intent = new Intent(MainActivity.this,Display_Data.class );
        intent.putExtra("Search",mExampleList.get(position).getCode());
        startActivity(intent);
  }

    public void BackLogout(MenuItem item) {
        Intent intent = new Intent(this, LoginAndSignUp.class);
        startActivity(intent);
    }
}