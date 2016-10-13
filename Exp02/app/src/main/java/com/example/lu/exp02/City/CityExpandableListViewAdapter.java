package com.example.lu.exp02.City;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lu.exp02.R;

import java.util.ArrayList;

/**
 * Created by lu on 2016/10/13.
 */

public class CityExpandableListViewAdapter extends BaseExpandableListAdapter {
    private Activity activity;
    private ArrayList<Data> datas;

    public CityExpandableListViewAdapter(Activity activity) {
        this.activity=activity;
        datas=DataList.getProvince();
    }


    @Override
    public int getGroupCount() {
        return datas.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return datas.get(groupPosition).getCityName().size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return datas.get(groupPosition).getProvinceName();
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return datas.get(groupPosition).getCityName().get(childPosition);
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
        return getGenericView(datas.get(groupPosition).getProvinceName(),Color.RED,1);
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        return getGenericView(datas.get(groupPosition).getCityName().get(childPosition),Color.BLUE,2);
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    public View getGenericView(String text, int color,int level)
    {
        LinearLayout linearLayout= (LinearLayout) activity.getLayoutInflater().inflate(R.layout.list_adapter,null);

        TextView textView = (TextView) linearLayout.findViewById(R.id.list_text_view);
        textView.setText(text);
        textView.setTextColor(color);
        if(level==2)
            textView.setPadding(textView.getPaddingLeft()+50,textView.getPaddingTop(),textView.getPaddingRight(),textView.getPaddingBottom());

        return linearLayout;
    }



}
