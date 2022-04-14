package com.agya.dhanoa.flight_track;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;



public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.AirlineViewHolder> {
    private Context mContext;
    private ArrayList<AirlineItem> mExampleList;

    public AirlineAdapter(Context context, ArrayList<AirlineItem> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }

    @Override
    public AirlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.airport_item, parent, false);
        return new AirlineViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AirlineViewHolder holder, int position) {
        AirlineItem currentItem = mExampleList.get(position);

        String Airport = currentItem.getmAirport();
        String Code = currentItem.getmCode();
        String Title = currentItem.getmTitle();

        holder.mAirport.setText("Airport:"+Airport);
        holder.mCode.setText("Code:" + Code);
        holder.mTitle.setText("Title: " + Title);
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public class AirlineViewHolder extends RecyclerView.ViewHolder {
         TextView mAirport,mCode,mTitle;


        public AirlineViewHolder(View itemView) {
            super(itemView);

            mAirport = itemView.findViewById(R.id.Airport_Name);
            mCode = itemView.findViewById(R.id.Code);
            mTitle = itemView.findViewById(R.id.Title);
        }
    }
}