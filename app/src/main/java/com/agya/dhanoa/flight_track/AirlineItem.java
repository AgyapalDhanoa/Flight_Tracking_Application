package com.agya.dhanoa.flight_track;

public class AirlineItem {
    private String mAirport,mCode,mTitle;


    public AirlineItem(String Airport, String Code, String Title) {
        mAirport = Airport;
        mCode = Code;
        mTitle = Title;
    }

    public String getmAirport() {
        return mAirport;
    }

    public String getmCode() {
        return mCode;
    }


    public String getmTitle() {
        return mTitle;
    }
}