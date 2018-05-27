package com.nomaa.quakereport;

/**
 * Created by nomaa on 6/13/2017.
 */

public class EarthquakeData {
    private String location;
    private double mag;
    private long time;
    private String url;


    public EarthquakeData(String loc, double ma, long tim, String ur){
        location = loc;
        mag = ma;
        time = tim;
        url = ur;
    }

    public String getUrl() {
        return url;
    }

    public String getLocation() {
        return location;
    }

    public double getMag() {
        return mag;
    }

    public long getTime() {
        return time;
    }
}
