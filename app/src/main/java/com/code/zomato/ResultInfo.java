package com.code.zomato;

public class ResultInfo {

    String name;
    String thumb;
    String cuisines;

    public ResultInfo(String name,String thumb,String cuisines) {
        this.name = name;
        this.thumb = thumb;
        this.cuisines = cuisines;
    }

    public String getCuisines() {
        return cuisines;
    }

    public void setCuisines(String cuisines) {
        this.cuisines = cuisines;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
