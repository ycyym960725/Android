package com.example.lu.hwexp01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    RelativeLayout trend;
    RelativeLayout report_item;
    RelativeLayout consult;
    RelativeLayout more;
    boolean flag=true;
    boolean flag1=true;
    boolean flag2=true;
    boolean flag3=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        trend= (RelativeLayout) findViewById(R.id.trend);
        report_item= (RelativeLayout) findViewById(R.id.report_item);
        consult= (RelativeLayout) findViewById(R.id.consult);
        more= (RelativeLayout) findViewById(R.id.more);
        trend.setOnClickListener(this);
        report_item.setOnClickListener(this);
        consult.setOnClickListener(this);
        more.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.consult:
                if (flag3)
                    consult.setBackgroundColor(Color.parseColor("#CED629"));
                else
                    consult.setBackgroundColor(Color.parseColor("#FED384"));
                flag3=!flag3;
                break;
            case R.id.report_item:
                if (flag)
                    report_item.setBackgroundColor(Color.parseColor("#CED629"));
                else
                    report_item.setBackgroundColor(Color.parseColor("#FED384"));
                flag=!flag;
                break;
            case R.id.more:
                if (flag2)
                    more.setBackgroundColor(Color.parseColor("#FED384"));
                else
                    more.setBackgroundColor(Color.parseColor("#CED629"));
                flag2=!flag2;
                break;
            case R.id.trend:
                if (flag1)
                    trend.setBackgroundColor(Color.parseColor("#FED384"));
                else
                    trend.setBackgroundColor(Color.parseColor("#CED629"));
                flag1=!flag1;
                break;
        }
    }
}
