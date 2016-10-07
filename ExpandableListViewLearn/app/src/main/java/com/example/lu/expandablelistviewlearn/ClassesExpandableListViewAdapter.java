package com.example.lu.expandablelistviewlearn;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lu on 2016/9/28.
 */

/**
 * BaseExpandableListAdapter
 *
 */
public class ClassesExpandableListViewAdapter extends BaseExpandableListAdapter {


    // 班级的集合
    private List<Classes> classes;

    // 创建布局使用
    private Activity activity;


    public ClassesExpandableListViewAdapter(List<Classes> classes, Activity activity) {
        this.classes = classes;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        // 获取一级条目的数量  就是班级的大小
        return classes.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 获取对应一级条目下二级条目的数量，就是各个班学生的数量
        return classes.get(groupPosition).students.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        // 获取一级条目的对应数据
        return classes.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        // 获取对应一级条目下二级条目的对应数据
        return classes.get(groupPosition).students.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        // 获取对应一级条目的View  和ListView 的getView相似
        return getGenericView(classes.get(groupPosition).name,Color.BLUE);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        // 获取对应二级条目的View  和ListView 的getView相似
        return getGenericView(classes.get(groupPosition).students.get(childPosition),Color.BLACK);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    /**
     * inflater一个TextView
     */
    private TextView getGenericView(String string,int color) {

        LayoutInflater layoutInflater=LayoutInflater.from(activity);
        LinearLayout linearLayout= (LinearLayout) layoutInflater.inflate(R.layout.list_item,null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.list_item_textView);
        textView.setText(string);
        textView.setTextColor(color);
        return textView;
    }

}
