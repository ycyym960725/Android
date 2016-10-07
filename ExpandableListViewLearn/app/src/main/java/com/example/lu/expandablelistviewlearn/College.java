package com.example.lu.expandablelistviewlearn;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lu on 2016/9/28.
 */

/**
 * 实体类，存大学名称和班级对象（list）
 * 对象clone时注意引用问题
 */
public class College {
//    private static String TAG="College";

    // 大学名
    public String name;

    // 班级列表
    public List<Classes> classList;

    @Override
    protected College clone() throws CloneNotSupportedException {
        College college=new College();
        String name=this.name;
        college.name=name;
        college.classList=new ArrayList<>(this.classList);
        return college;
    }
}
