package com.example.lu.expandablelistviewlearn;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lu on 2016/9/28.
 */

public class SimpleExpandableListViewAdapter extends BaseExpandableListAdapter {
    // 大学的集合
    private List<College> colleges;

    private Activity activity;


    public SimpleExpandableListViewAdapter(List<College> colleges, Activity activity) {
        this.colleges = colleges;
        this.activity = activity;
    }

    @Override
    public int getGroupCount() {
        return colleges.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        // 一个子ExpandableListView对象
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return colleges.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return colleges.get(groupPosition).classList.get(childPosition);
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

        return getGenericView(colleges.get(groupPosition).name,Color.GRAY);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getGenericExpandableListView(colleges.get(groupPosition));
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }


    /**
     * 返回父View
     * @param string
     * @return
     */
    private TextView getGenericView(String string,int color) {
        LayoutInflater layoutInflater=LayoutInflater.from(activity);
        LinearLayout linearLayout= (LinearLayout) layoutInflater.inflate(R.layout.list_item,null);
        TextView textView = (TextView) linearLayout.findViewById(R.id.list_item_textView);
        textView.setText(string);
        textView.setTextColor(color);
        return textView;
    }


    /**
     *  返回子ExpandableListView 的对象  此时传入的是该大学下所有班级的集合。
     *  关键方法
     * @param college
     * @return
     */
    public ExpandableListView getGenericExpandableListView(College college){

        CustomExpandableListView view = new CustomExpandableListView(activity);

        // 加载班级的适配器
        ClassesExpandableListViewAdapter adapter = new ClassesExpandableListViewAdapter(college.classList,activity);

        view.setAdapter(adapter);

        view.setPadding(20,0,0,0);
        return view;
    }
}
