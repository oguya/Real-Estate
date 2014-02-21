package com.droid.keja.model;

/**
 * Created by james on 20/02/14.
 */
public class HomeSale {
    private String name;
    private String price;
    private String location;
    private String bedrooms;
    private String baths;
    private String type;
    private String desc;
    private String thumbnail;
    private String lat;
    private String lng;

    public HomeSale(){}

    public HomeSale(String name, String rent, String location, String bedrooms,
                    String baths, String type, String desc, String thumbnail){
        this.name = name;
        this.price = rent;
        this.location = location;
        this.bedrooms = bedrooms;
        this.baths = baths;
        this.type = type;
        this.desc = desc;
        this.thumbnail = thumbnail;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPrice(){
        return this.price;
    }

    public void setPrice(String price){
        this.price = price;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getBedrooms(){
        return this.bedrooms;
    }

    public void setBedrooms(String bedrooms){
        this.bedrooms = bedrooms;
    }

    public String getBaths(){
        return this.baths;
    }

    public void setBaths(String baths){
        this.baths = baths;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public String getThumbnail(){
        return this.thumbnail;
    }

    public void setThumbnail(String thumbnail){
        this.thumbnail = thumbnail;
    }

    public void setLat(String lat){
        this.lat = lat;
    }

    public String getLat(){
        return this.lat;
    }

    public void setLng(String lng){
        this.lng = lng;
    }

    public String getLng(){
        return this.lng;
    }
}
