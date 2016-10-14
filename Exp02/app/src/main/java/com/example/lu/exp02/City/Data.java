package com.example.lu.exp02.city;

import java.util.ArrayList;

/**
 * Created by lu on 2016/10/13.
 */

public class Data {
    private String provinceName;
    private ArrayList<String> cityNames;

    public Data(String provinceName, ArrayList<String> cityNames) {
        this.provinceName = provinceName;
        this.cityNames = cityNames;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public ArrayList<String> getCityNames() {
        return cityNames;
    }

    public void setCityNames(ArrayList<String> cityNames) {
        this.cityNames = cityNames;
    }
}
