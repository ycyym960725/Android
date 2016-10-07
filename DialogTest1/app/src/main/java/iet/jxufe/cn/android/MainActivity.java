package iet.jxufe.cn.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button exit, single, friend;
	private TextView statusInfo;
	private String[] status = new String[] { "在线", "隐身", "其它", "忙碌", "离开", "离线" };

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        exit=(Button)findViewById(R.id.exit);
        single=(Button)findViewById(R.id.single);
        statusInfo=(TextView)findViewById(R.id.statusInfo);
        friend=(Button)findViewById(R.id.friend);
        final Builder builder=new AlertDialog.Builder(this);
		/**
		 * 退出按钮的监听
		 */
        exit.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {				
				builder.setMessage("Are you sure you want to exit?");				
				builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {						
						Toast.makeText(MainActivity.this, "你单击了'是'按钮！", Toast.LENGTH_LONG).show();
					}
				});
				builder.setNegativeButton("否",new DialogInterface.OnClickListener() {					
					public void onClick(DialogInterface dialog, int which) {						
						Toast.makeText(MainActivity.this, "你单击了'否'按钮！", Toast.LENGTH_LONG).show();
					}
				});
				builder.setCancelable(false);
				builder.create();
				builder.show();
			}
		});
		/**
		 * 单选对话框的监听
		 */
        single.setOnClickListener(new OnClickListener() {			
			public void onClick(View v) {
				Builder builder =new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("请选择您的状态");
				builder.setIcon(R.drawable.ic_launcher);
				builder.setSingleChoiceItems(status, 1, new DialogInterface.OnClickListener() {					
					public void onClick(DialogInterface dialog, int which) {
						if(which==(2))//对应“其他”的位置
						{
							Builder builder=new AlertDialog.Builder(MainActivity.this);
							builder.setTitle("请输入您的状态");
							builder.setIcon(R.drawable.ic_launcher);
							final EditText myStatus=new EditText(MainActivity.this);
							builder.setView(myStatus);
							builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {								
								public void onClick(DialogInterface dialog, int which) {
									statusInfo.setText("您当前的状态是： "+myStatus.getText().toString());
								}
							} );
							builder.create().show();
						}else{
							statusInfo.setText("您当前的状态是： "+status[which]);
						}
					}
				});	
				builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {					
					public void onClick(DialogInterface dialog, int which) {}
					});
				builder.setCancelable(false);
				builder.create().show();
			}
		});

       final String[] names=new String[]{"明天会更好","淺川","萍水相逢"};
       String[] infos=new String[]{"个性签名：磨剑！","个性签名：拼搏！","个性签名：求其上者得其中，求其中者得其下！"};
       int[] imgIds=new int[]{R.drawable.qq01,R.drawable.qq02,R.drawable.qq03};
       final ArrayList<Map<String,Object>> data=new ArrayList<Map<String,Object>>();
       for(int i=0;i<names.length;i++){
    	   Map<String,Object> item=new HashMap<String,Object>();
    	   item.put("name",names[i]);
    	   item.put("info",infos[i]);
    	   item.put("img",imgIds[i]);
    	   data.add(item);
       }

       friend.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				Builder builder=new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("请选择好友");
				builder.setIcon(R.drawable.ic_launcher);
				SimpleAdapter adapter=new SimpleAdapter(MainActivity.this, data, R.layout.item, new String[]{"name","info","img"}, new int[]{R.id.name,R.id.info,R.id.img});
				builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
					
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(MainActivity.this, "您选择了："+names[which], Toast.LENGTH_LONG).show();
					}
				});
				builder.create().show();
			}
		});
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
}
