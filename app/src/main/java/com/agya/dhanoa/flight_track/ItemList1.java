package com.agya.dhanoa.flight_track;

import java.util.Objects;

public class ItemList1 {
    private final String iata;

    public ItemList1(String iata){
        this.iata = iata;
    }
    public String getIata(){
        return iata;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof ItemList1){
            ItemList1 item = (ItemList1) obj;
            return this.iata.equals(item.iata);
        }
        return false;
    }



}
