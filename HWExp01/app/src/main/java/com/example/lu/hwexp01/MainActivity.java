package com.example.lu.hwexp01;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;
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
    TextView ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**
         * AppCompatActivity下requestWindowFeature(Window.FEATURE_NO_TITLE)无用
         */
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        /**
         * 初始化组件
         */
        trend= (RelativeLayout) findViewById(R.id.trend);
        report_item= (RelativeLayout) findViewById(R.id.report_item);
        consult= (RelativeLayout) findViewById(R.id.consult);
        more= (RelativeLayout) findViewById(R.id.more);
        ad= (TextView) findViewById(R.id.ad_text_view);
        /**
         * 注册点击事件
         */
        trend.setOnClickListener(this);
        report_item.setOnClickListener(this);
        consult.setOnClickListener(this);
        more.setOnClickListener(this);
        ad.setOnClickListener(this);
        /**
         * 注册ContextMenu
         */
        registerForContextMenu(more);
    }

    /**
     * 响应点击事件，参数为正被点击的View
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.trend:
                if (flag1)
                    trend.setBackgroundColor(Color.parseColor("#FED384"));
                else
                    trend.setBackgroundColor(Color.parseColor("#CED629"));
                flag1=!flag1;
                break;

            case R.id.report_item:
                if (flag) {
                    report_item.setBackgroundColor(Color.parseColor("#CED629"));
                }
                else {
                    report_item.setBackgroundColor(Color.parseColor("#FED384"));
                }
                flag=!flag;
                break;

            case R.id.consult:
                if (flag3) {
                    consult.setBackgroundColor(Color.parseColor("#CED629"));
                }
                else {
                    consult.setBackgroundColor(Color.parseColor("#FED384"));
                }
                flag3=!flag3;
                break;

            case R.id.more:
                if (flag2) {
                        more.setBackgroundColor(Color.parseColor("#FED384"));
                    }
                    else {
                        more.setBackgroundColor(Color.parseColor("#CED629"));
                    }
                flag2=!flag2;
                break;

            case R.id.ad_text_view:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                final String[] ads=new String[]{"广告占位1","广告占位2","广告占位3","广告占位4","广告占位5","广告占位6"};
                builder.setTitle("更多广告");
                builder.setItems(ads, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"点击了："+ads[which],Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
                break;
        }
    }

    /**
     * 创建ContextMenu
     * @param menu
     * @param v
     * @param menuInfo
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId())
        {
            case R.id.more:
                AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("提示");
                builder.setMessage("确定离开此页并跳转到豹考通首页？");
                builder.setPositiveButton("跳转", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri=Uri.parse("https://github.com/JLbeard/Android/tree/Exp01");
                        Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                        startActivity(intent);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                break;
        }
    }
}
