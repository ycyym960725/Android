package iet.jxufe.cn.android.btest05;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

/**
 * 注册页面
 */
public class RegisterActivity extends Activity {

    public static final int RESULT_CODE_REGISTER_SUCCESS=0x6666;
    public static final int RESULT_CODE_REGISTER_FAILED=0x66667;

    private EditText name;
    private EditText psd;
    private EditText repsd;
    private RadioButton male;
    private RadioButton female;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initComponent();
    }

    private void initComponent(){
        name= (EditText) findViewById(R.id.name);
        psd= (EditText) findViewById(R.id.psd);
        repsd= (EditText) findViewById(R.id.repsd);
        male= (RadioButton) findViewById(R.id.male);
        female= (RadioButton) findViewById(R.id.female);
    }

    /**
     *  响应xml中设置的onClick方法
     * @param view 注册按钮
     */
    public void register(View view){
        String username=name.getText().toString();
        String password=psd.getText().toString();
        String repassword=repsd.getText().toString();
        String gender=null;
        if (female.isChecked()) {
            gender = "女";
        }else if (male.isChecked()){
            gender="男";
        }
        switch (checkoutForm(username,password,repassword)){
            case 0:
                /**
                 * 输入没有问题，且未注册
                 */
                User user=new User(username,password,gender);
                UserManager.getUserManager(RegisterActivity.this).saveToSQLite(user);

                Intent intent=new Intent();
                intent.putExtra("registerName",username);
                setResult(RESULT_CODE_REGISTER_SUCCESS,intent);
                finish();
                break;
            case 1:
                makeFailedDialog("用户名为空");
                break;
            case 2:
                makeFailedDialog("密码低于3位");
                break;
            case 3:
                makeFailedDialog("两次输入的密码不一致");
                break;
            case 4:
                makeFailedDialog("已存在该用户");
                break;
        }
    }


    private void makeFailedDialog(String s){
        AlertDialog.Builder builder=new AlertDialog.Builder(RegisterActivity.this);
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
        setResult(RESULT_CODE_REGISTER_FAILED,new Intent());
    }

    public int checkoutForm(String name,String psd,String repsd){
        int flag=0;
        boolean isIn=UserManager.getUserManager(RegisterActivity.this).isInSQLite(name);
        /**
         * 用户名为空
         */
        if ((name==null)||(name.equals("")))
            flag=1;
        /**
         * 密码低于3位
         */
        else if (psd.length()<3)
            flag=2;
        /**
         * 两次输入的密码不一致
         */
        else if (!psd.equals(repsd))
            flag=3;
        /**
         * 已存在该用户
         */
        else if (isIn){
            flag=4;
        }
        return flag;
    }
}
