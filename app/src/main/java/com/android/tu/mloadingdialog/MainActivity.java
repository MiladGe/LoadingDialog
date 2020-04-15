package com.android.tu.mloadingdialog;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.android.tu.loadingdialog.LoadingDialog;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btn1, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn1 = (Button) findViewById(R.id.style1_btn);
        btn2 = (Button) findViewById(R.id.style2_btn);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.style1_btn:
                LoadingDialog.Builder builder1 = new LoadingDialog.Builder(MainActivity.this)
                        .setMessage("加载中...")
                        .setMaxWaitTime(8)
                        .setCancelable(true);
                final LoadingDialog dialog1 = builder1.create();
                dialog1.show();
                break;
            case R.id.style2_btn:
                LoadingDialog.Builder builder2 = new LoadingDialog.Builder(MainActivity.this)
                        .setShowMessage(false)
                        .setCancelable(true);
                final LoadingDialog dialog2 = builder2.create();
                dialog2.show();
                break;
        }
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };
}
