package com.example.lu.exp02;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lu.exp02.City.CityExpandableListViewAdapter;
import com.example.lu.exp02.City.DataList;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView rename;
    Button selectCity;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rename= (TextView) findViewById(R.id.title);
        selectCity= (Button) findViewById(R.id.select_city);
        editText= (EditText) findViewById(R.id.edit_city);

        registerForContextMenu(rename);
        selectCity.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId())
        {
            case R.id.rename:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入新的名称");
                builder.setIcon(R.mipmap.ic_launcher);
                final EditText edit = new EditText(MainActivity.this);
                builder.setView(edit);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                rename.setText(edit.getText().toString());
                            }
                        });
                builder.create().show();
                break;

            case R.id.set_blue:
                rename.setTextColor(Color.BLUE);
                break;

            case R.id.set_red:
                rename.setTextColor(Color.RED);
                break;

            case R.id.set_green:
                rename.setTextColor(Color.GREEN);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        switch (v.getId()) {
            /**
             * 判断选中的是哪个上下文菜单，并响应相应的操作
             */
            case R.id.title:
                menu.setHeaderTitle("文件操作");
                menu.add(0, Menu.FIRST + 1, 0, "重命名");
                SubMenu color = menu.addSubMenu(0, Menu.FIRST + 2, 0, "设置文本颜色");
                color.add(0, Menu.FIRST + 21, 0, "红色");
                color.add(0, Menu.FIRST + 22, 0, "绿色");
                color.add(0, Menu.FIRST + 23, 0, "蓝色");
                break;
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case Menu.FIRST + 1:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("请输入新的名称");
                builder.setIcon(R.mipmap.ic_launcher);
                final EditText edit = new EditText(MainActivity.this);
                builder.setView(edit);
                builder.setPositiveButton("确定",
                        new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                rename.setText(edit.getText().toString());
                            }
                        });
                builder.create().show();
                break;
            case Menu.FIRST + 21:
                rename.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 22:
                rename.setTextColor(Color.GREEN);
                break;
            case Menu.FIRST + 23:
                rename.setTextColor(Color.BLUE);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.select_city:
                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("选择所在城市");
                final ExpandableListView expandableListView=new ExpandableListView(MainActivity.this);
                expandableListView.setAdapter(new CityExpandableListViewAdapter(MainActivity.this));
                expandableListView.setGroupIndicator(null);
                builder.setView(expandableListView);
                builder.show();

//                builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
//                    @Override
//                    public void onDismiss(DialogInterface dialog) {
//                        Log.d("Main","onDismiss");
//                    }
//                });
                expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                        editText.setText(DataList.getProvince().get(groupPosition).getProvinceName()+" "
                                +DataList.getProvince().get(groupPosition).getCityName().get(childPosition));


//                        try {
//                            Field field = builder.getClass().getSuperclass().getDeclaredField("mShowing");
//                            field.setAccessible(true);
//                            field.set(builder, true);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                        return true;
                    }
                });

                break;
        }
    }
}
