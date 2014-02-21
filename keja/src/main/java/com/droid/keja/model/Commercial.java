package com.droid.keja.model;

/**
 * Created by james on 20/02/14.
 */
public class Commercial {
    private String name;
    private String rent;
    private String location;
    private String size;
    private String type;
    private String desc;
    private String thumbnail;
    private String lat;
    private String lng;

    public Commercial(){}

    public Commercial(String name, String rent, String location, String size,
                     String type, String desc, String thumbnail, String lat, String lng){
        this.name = name;
        this.rent = rent;
        this.location = location;
        this.size = size;
        this.type = type;
        this.desc = desc;
        this.thumbnail = thumbnail;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getRent(){
        return this.rent;
    }

    public void setRent(String rent){
        this.rent = rent;
    }

    public String getLocation(){
        return this.location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public String getSize(){
        return this.size;
    }

    public void setSize(String size){
        this.size = size;
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

    public String getLat(){
        return this.lat;
    }

    public void setLat(String lat){
        this.lat = lat;
    }

    public String getLng(String lng){
        return this.lng;
    }

    public void setLng(String lng){
        this.lng = lng;
    }

}