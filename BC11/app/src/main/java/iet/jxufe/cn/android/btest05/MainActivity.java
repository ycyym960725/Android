package iet.jxufe.cn.android.btest05;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

/**
 * 主Activity
 */
public class MainActivity extends Activity {
	/**
	 *	请求码是当前activity传给启动的activity，然后会跟结果码一起回传给当前activity
	 *	结果码是被启动的activity回传给当前activity的，通过回调方法获得
	 *
	 *	请求码可以用来判断启动了哪个activity，也可以判断是什么控件启动了同一个activity
	 *	结果码可以用来判断启动的activity是否处理成功，也可以判断来自哪个activity
	 *	请求码和结果码是用来解决复杂问题的  但是本题不需要使用
	 */

	public static final int REQUEST_CODE_LOGIN =0x8888;
	public static final int REQUEST_CODE_REGISTER =0x8889;


	private TextView userInfoText;
	private Intent intent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		userInfoText = (TextView) findViewById(R.id.userInfo);
		/**
		 * getIntent应该是该activity被其他activity启动才可以获取到intent
		 * 后两句都可以省略
		 */
		intent=getIntent();
		if (intent==null)
			initFromIntent();
	}


	/**
	 * Intent非空的时候调用
	 */
	private void initFromIntent(){
		/**
		 * 从intent中获取登录名并设置标题为登录信息
		 */
		String loginName = intent.getStringExtra("loginName");

		if (loginName == null || loginName.equals("")) {
			userInfoText.setVisibility(View.GONE);
		} else {
			userInfoText.setVisibility(View.VISIBLE);
			userInfoText.setText(Html.fromHtml("登录成功!欢迎您：<font color=red>"
					+ loginName + "</font>"));
			return;
			/**
			 * 结束函数
			 */
		}
		/**
		 * 从Intent中获取注册名并设置标题为注册信息
		 */
		String registerName=intent.getStringExtra("registerName");

		if (registerName == null || registerName.equals("")) {
			userInfoText.setVisibility(View.GONE);
		} else {
			userInfoText.setVisibility(View.VISIBLE);
			userInfoText.setText(Html.fromHtml("注册成功!欢迎您：<font color=red>"
					+ registerName + "</font>"));
			return;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		/**
		 * 这里可以无视请求码和结果码，因为可以通过回传的intent内容判断
		 */
		if(data!=null){
			intent=data;
			initFromIntent();
		}
	}

	/**
	 * 登录按钮的事件处理，跳转到登录界面
	 * 响应xml中设置的onClick方法
	 * @param view 登录按钮
     */
	public void login(View view) {
		startActivityForResult(new Intent(MainActivity.this,LoginActivity.class),MainActivity.REQUEST_CODE_LOGIN);
	}

	/**
	 * 注册按钮的事件处理，跳转到注册界面
	 * @param view	注册按钮
     */
	public void register(View view) {
		startActivityForResult(new Intent(MainActivity.this,RegisterActivity.class),MainActivity.REQUEST_CODE_REGISTER);
	}	
}
