package com.example.lu.expandablelistviewlearn;

import java.util.List;

/**
 * Created by lu on 2016/9/28.
 */

/**
 * 实体类，存班级名称和学生信息（list）
 */
public class Classes {


    // 班级名
    public String name;


    // 班级中的学生列表
    public List<String> students;

    @Override
    protected Classes clone() throws CloneNotSupportedException {
        Classes classes=new Classes();
        classes.name=this.name;
        classes.students=this.students;
        return classes;
    }
}
