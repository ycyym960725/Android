package com.example.lu.exp02;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.example.lu.exp02.city.CityExpandableListViewAdapter;
import com.example.lu.exp02.city.DataList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView title;
    Button selectCity;
    EditText editCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title= (TextView) findViewById(R.id.title);
        selectCity= (Button) findViewById(R.id.select_city);
        editCity= (EditText) findViewById(R.id.edit_city);

        registerForContextMenu(title);
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
                renameDialog();
                break;

            case R.id.set_blue:
                title.setTextColor(Color.BLUE);
                break;

            case R.id.set_red:
                title.setTextColor(Color.RED);
                break;

            case R.id.set_green:
                title.setTextColor(Color.GREEN);
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
                /**
                 * 菜单
                 */
                menu.setHeaderTitle("文件操作");
                menu.add(0, Menu.FIRST + 1, 0, "重命名");
                SubMenu color = menu.addSubMenu(0, Menu.FIRST + 2, 0, "设置文本颜色");
                /**
                 * 增加子菜单
                 */
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
                renameDialog();
                break;
            case Menu.FIRST + 21:
                title.setTextColor(Color.RED);
                break;
            case Menu.FIRST + 22:
                title.setTextColor(Color.GREEN);
                break;
            case Menu.FIRST + 23:
                title.setTextColor(Color.BLUE);
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.select_city:
                selectCityDialog();
                break;
        }
    }

    private void selectCityDialog() {
        final android.support.v7.app.AlertDialog.Builder builder =new android.support.v7.app.AlertDialog.Builder(MainActivity.this);
        builder.setTitle("选择所在城市");
        final ExpandableListView expandableListView=new ExpandableListView(MainActivity.this);
        expandableListView.setAdapter(new CityExpandableListViewAdapter(MainActivity.this));
        expandableListView.setGroupIndicator(null);
        builder.setView(expandableListView);
        /**
         * 用尽各种方法，结果show方法会返回AlertDialog。。。
         * 直接调用cancel，取消dialog
         */
        final AlertDialog alertDialog=builder.show();

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                editCity.setText(DataList.getProvinces().get(groupPosition).getProvinceName()+" "
                        +DataList.getProvinces().get(groupPosition).getCityNames().get(childPosition));
                /**
                 * 利用View.GONE去销毁ExpandableListView,但是无法销毁dialog
                 * 利用反射获取AlertDialog实例，去调用cancel方法去销毁dialog，但是该dialog没有绑定builder实例
                 */
                alertDialog.cancel();
                return true;
            }
        });
    }

    private void renameDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("请输入新的名称");
        builder.setIcon(R.mipmap.ic_launcher);
        final EditText edit = new EditText(MainActivity.this);
        builder.setView(edit);
        builder.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        title.setText(edit.getText().toString());
                    }
                });
        builder.show();
    }
}
