package iet.jxufe.cn.android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private boolean flag = true;
	private Button file01, file02;
	private String[] sizes = new String[] { "8", "12", "16", "20", "24", "28" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		file01 = (Button) findViewById(R.id.file01);
		file02 = (Button) findViewById(R.id.file02);
		/**
		 * 注册上下文菜单
		 */
		registerForContextMenu(file01);
		registerForContextMenu(file02);
	}

	/**
	 * 选项菜单 Option
	 * 最右边的菜单
     */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}


	/**
	 * 启用or禁止，在onCreateOptionMenu之前调用
     */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem start = menu.findItem(R.id.start);
		MenuItem stop = menu.findItem(R.id.stop);
		start.setEnabled(flag);
		stop.setEnabled(!flag);
		flag = !flag;
		return super.onPrepareOptionsMenu(menu);
	}

	/**
	 * 文件图标,选项菜单被选中
     */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId())
		{
			/**
			 * 退出
			 */
			case R.id.exit:
				finish();
				break;
			/**
			 * 启用和禁止
			 */
			case R.id.start:
			case R.id.stop:
				/**
				 * 更新菜单
				 */
				invalidateOptionsMenu();
				break;
			case R.id.newFile:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * 上下文菜单
     */
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		switch (v.getId()) {
			/**
			 * 判断选中的是哪个上下文菜单，并响应相应的操作
			 */
		case R.id.file01:
			menu.setHeaderTitle("文件操作");
			menu.add(0, Menu.FIRST + 1, 0, "重命名");
			SubMenu color = menu.addSubMenu(0, Menu.FIRST + 2, 0, "设置文本颜色");
			color.add(0, Menu.FIRST + 21, 0, "红色");
			color.add(0, Menu.FIRST + 22, 0, "绿色");
			color.add(0, Menu.FIRST + 23, 0, "蓝色");
			break;
		case R.id.file02:
			menu.setHeaderTitle("文件操作");
			menu.add(0, Menu.FIRST + 3, 0, "设置文本字体大小");
			SubMenu bgcolor = menu.addSubMenu(0, Menu.FIRST + 4, 0, "设置文本背景");
			bgcolor.add(0, Menu.FIRST + 41, 0, "红色");
			bgcolor.add(0, Menu.FIRST + 42, 0, "绿色");
			bgcolor.add(0, Menu.FIRST + 43, 0, "蓝色");
			break;

		default:
			break;
		}
	}

	/**
	 * 上下文菜单被选中
     */
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case Menu.FIRST + 1:
			Builder builder = new AlertDialog.Builder(MainActivity.this);
			builder.setTitle("请输入新的名称");
			builder.setIcon(R.drawable.ic_launcher);
			final EditText edit = new EditText(MainActivity.this);
			builder.setView(edit);
			builder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							file01.setText(edit.getText().toString());
						}
					});
			builder.create().show();
			break;
		case Menu.FIRST + 21:
			file01.setTextColor(Color.RED);
			break;
		case Menu.FIRST + 22:
			file01.setTextColor(Color.GREEN);
			break;
		case Menu.FIRST + 23:
			file01.setTextColor(Color.BLUE);
			break;
		case Menu.FIRST + 3:
			Builder sizeBuilder = new AlertDialog.Builder(MainActivity.this);
			sizeBuilder.setTitle("请选择字体大小");
			sizeBuilder.setIcon(R.drawable.ic_launcher);
			sizeBuilder.setSingleChoiceItems(sizes, 1,
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							file02.setTextSize(Integer.parseInt(sizes[which]));
						}
					});

			sizeBuilder.setPositiveButton("确定",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
						}
					});
			sizeBuilder.create().show();
			break;

		case Menu.FIRST + 41:
			file02.setBackgroundColor(Color.RED);
			break;
		case Menu.FIRST + 42:
			file02.setBackgroundColor(Color.GREEN);
			break;
		case Menu.FIRST + 43:
			file02.setBackgroundColor(Color.BLUE);
			break;

		default:
			break;
		}
		return super.onContextItemSelected(item);
	}
}
