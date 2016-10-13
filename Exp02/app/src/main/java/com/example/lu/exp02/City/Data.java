package com.example.lu.exp02.City;

import java.util.ArrayList;

/**
 * Created by lu on 2016/10/13.
 */

public class Data {
    private String provinceName;
    private ArrayList<String> cityName;

    public Data(String provinceName, ArrayList<String> cityName) {
        this.provinceName = provinceName;
        this.cityName = cityName;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public ArrayList<String> getCityName() {
        return cityName;
    }

    public void setCityName(ArrayList<String> cityName) {
        this.cityName = cityName;
    }
}
