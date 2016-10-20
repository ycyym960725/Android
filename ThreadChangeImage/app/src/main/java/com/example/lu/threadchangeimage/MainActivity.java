package com.example.lu.threadchangeimage;

import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private String TAG="MainActivity";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView= (ImageView) findViewById(R.id.image);
        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic3));
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                /**
                 * 对消息进行处理
                 */
                switch (msg.what){
                    case 0x1111:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic1));
                        break;
                    case 0x1112:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic2));
                        break;
                    case 0x1113:
                        imageView.setImageDrawable(getResources().getDrawable(R.drawable.pic3));
                        break;
                }
            }
        };
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.d(TAG,"thread");
                Message message;
                int i=0;
                while (true){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    /**
                     * 消息发送过去之后就不能再使用了
                     * 初始化消息的what（默认0）值 然后再++
                     */
                    message=handler.obtainMessage();
                    message.what=0x1111;
                    Log.d(TAG,"what:"+message.what);
                    message.what=message.what+i%3;
                    Log.d(TAG,"what:"+message.what);

                    handler.sendMessage(message);
                    i++;
                }
            }
        }).start();
    }
}
