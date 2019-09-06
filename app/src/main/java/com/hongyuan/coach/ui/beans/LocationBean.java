package com.hongyuan.coach.ui.beans;

public class LocationBean {

    private static LocationBean locationBean = null;

    private LocationBean(){

    }

    public static LocationBean getInstance(){
        if(locationBean == null){
            locationBean = new LocationBean();
        }
        return locationBean;
    }

    private String lat;
    private String lng;
    private int os_id;//距离最近的门店id

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public int getOs_id() {
        return os_id;
    }

    public void setOs_id(int os_id) {
        this.os_id = os_id;
    }
}
