package com.agya.dhanoa.flight_track;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class AirlineAdapter extends RecyclerView.Adapter<AirlineAdapter.AirlineViewHolder> {
    private final Context mContext;
    private final ArrayList<AirlineItem> mExampleList;
private final OnAirlineListener monAirlineListener;

public AirlineAdapter(Context context, ArrayList<AirlineItem> exampleList,OnAirlineListener onAirlineListener) {
        mContext = context;
        mExampleList = exampleList;
        this.monAirlineListener = onAirlineListener;
    }

    @NonNull
    @Override
    public AirlineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.airport_item, parent, false);
        return new AirlineViewHolder(v, monAirlineListener);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(AirlineViewHolder holder, int position) {
        AirlineItem currentItem = mExampleList.get(position);

        String Airport = currentItem.getAirport();
        String Code = currentItem.getCode();
        String Title = currentItem.getTitle();

        holder.mAirport.setText("Airport:"+Airport);
        holder.mCode.setText("Code:" + Code);
        holder.mTitle.setText("Title: " + Title);



    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    public interface OnAirlineListener{
        void onNoteClick(int position);
    }


    public static class AirlineViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
         TextView mAirport,mCode,mTitle;
         ImageView Images;
        OnAirlineListener onAirlineListener;

        public AirlineViewHolder(View itemView, OnAirlineListener onAirlineListener) {
            super(itemView);


            mAirport = itemView.findViewById(R.id.Airport_Name);
            mCode = itemView.findViewById(R.id.Code);
            mTitle = itemView.findViewById(R.id.Title);
            Images = itemView.findViewById(R.id.image_view);
            this.onAirlineListener = onAirlineListener;
           itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
onAirlineListener.onNoteClick(getAdapterPosition());
        }
    }
}