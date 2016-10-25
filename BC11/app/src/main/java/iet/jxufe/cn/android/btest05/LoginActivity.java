package iet.jxufe.cn.android.btest05;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

/**
 * 登录界面
 */
public class LoginActivity extends Activity {

    public static final int RESULT_CODE_LOGIN_SUCCESS=0x7777;
    public static final int RESULT_CODE_LOGIN_FAILED=0x7778;

    private EditText name;
    private EditText psd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initComponent();
    }

    private void initComponent(){
        name= (EditText) findViewById(R.id.name);
        psd= (EditText) findViewById(R.id.psd);
    }

    public void login(View view) {
        String username=name.getText().toString();
        String password=psd.getText().toString();
        switch (checkoutForm(username,password)){
            case 0:
                /**
                 * 全都正确
                 */
                Intent intent=new Intent();
                intent.putExtra("loginName",username);
                setResult(LoginActivity.RESULT_CODE_LOGIN_SUCCESS,intent);
                finish();
                break;
            case 1:
                makeFailedDialog("用户名为空");
                break;
            case 2:
                makeFailedDialog("密码少于三位");
                break;
            case 3:
                Toast.makeText(LoginActivity.this,"用户名或密码错误，请重新输入",Toast.LENGTH_SHORT).show();
                setResult(LoginActivity.RESULT_CODE_LOGIN_FAILED,new Intent());
                break;
        }
    }

    public int checkoutForm(String name,String psd){
        int flag=0;
        if (name==null||name.equals("")){
            /**
             * 用户名为空
             */
            flag=1;
        }
        else if (psd.length()<3){
            /**
             * 密码少于三位
             */
            flag=2;
        }
        else if (!UserManager.getUserManager(LoginActivity.this).isInSQLite(new User(name,psd,null))) {
            /**
             * 用户名或密码输入错误
             */
            flag=3;
        }
        return flag;
    }

    private void makeFailedDialog(String s){
        AlertDialog.Builder builder=new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("输入警告");
        builder.setMessage(s);
        builder.setPositiveButton("知道了", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
        /**
         * 设置错误码
         */
        setResult(RESULT_CODE_LOGIN_FAILED,new Intent());
    }
}
