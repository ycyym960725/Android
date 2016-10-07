package com.example.lu.expandablelistviewlearn;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ExpandableListView listview;

    private List<College> colleges;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        initData();

        // 查找控件
        listview = ((ExpandableListView) findViewById(R.id.tree_view_simple));

        SimpleExpandableListViewAdapter adapter = new SimpleExpandableListViewAdapter(colleges,this);

        // 设置适配器
        listview.setAdapter(adapter);

    }

    /**
     * 初始化数据
     * ArrayList copy
     * newlist=new ArrayList<>(oldlist)
     * newlist.addAll(oldlist)
     */
    private void initData() {
        colleges = new ArrayList<>();

        //第一栏
        College college = new College();
        college.name = "江西财经大学";

        List<Classes> classesList = new ArrayList<>();
        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();
            classes.name = "软件工程"+i+"班";
            List<String> list = new ArrayList<>();
            list.add("Android");
            list.add("C Sharp");
            classes.students = list;
            classesList.add(classes);
        }
        List<Classes> temp = new ArrayList<>(classesList);
        college.classList = temp;
        try {
            colleges.add(college.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        //第二栏，先清空数据，再新增
        college.classList.clear();
        classesList.clear();
        college.name = "东华理工大学";

        for(int i = 1 ;i<3;i++) {
            Classes classes = new Classes();

            classes.name = "计算机"+i+"班";

            List<String> list = new ArrayList<>();

            list.add("Android");
            list.add("C Sharp");
            classes.students = list;

            classesList.add(classes);
        }

        temp = new ArrayList<>(classesList);
        college.classList = temp;
        try {
            colleges.add(college.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

    }
}
