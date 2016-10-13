package com.example.lu.exp02.City;

import java.util.ArrayList;

/**
 * Created by lu on 2016/10/13.
 */

public class DataList {
    private static ArrayList<Data> province;



    public static ArrayList<Data> getProvince() {
        if (province==null)
        {
            province=new ArrayList<>();

            ArrayList<String> cityName=new ArrayList<>();
            String provinceName="江西";
            cityName.add("南昌");
            cityName.add("抚州");
            cityName.add("鹰潭");
            cityName.add("上饶");
            cityName.add("九江");

            province.add(new Data(provinceName,new ArrayList<String>(cityName)));

            provinceName="江苏";
            cityName.clear();
            cityName.add("南京");
            cityName.add("常州");
            cityName.add("扬州");
            cityName.add("无锡");
            cityName.add("南通");
            province.add(new Data(provinceName,new ArrayList<String>(cityName)));

            provinceName="浙江";
            cityName.clear();
            cityName.add("杭州");
            cityName.add("宁波");
            cityName.add("湖州");
            cityName.add("嘉兴");
            cityName.add("温州");
            province.add(new Data(provinceName,new ArrayList<String>(cityName)));

        }

        return province;
    }
}
