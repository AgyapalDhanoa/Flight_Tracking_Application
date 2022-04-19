package com.agya.dhanoa.flight_track;

public class AirlineItem {
    private final String mAirport;
    private final String mCode;
    private final String mTitle;


    public AirlineItem(String Airport, String Code, String Title) {
        mAirport = Airport;
        mCode = Code;
        mTitle = Title;
    }

    public String getAirport() {
        return mAirport;
    }

    public String getCode() {
        return mCode;
    }


    public String getTitle() {
        return mTitle;
    }
}