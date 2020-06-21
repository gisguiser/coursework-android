package com.example.sign_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.sign_in.R;


public class MainActivity extends Activity implements View.OnClickListener,
        RadioGroup.OnCheckedChangeListener {

    EditText username, password;
    RadioGroup sex;
    Button btn_register, btn_cancel;

    UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = new UserBean();
        initView();
    }

    private void initView() {
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        sex = (RadioGroup) findViewById(R.id.sex);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_cancel = (Button) findViewById(R.id.btn_cancel);
        btn_register.setOnClickListener(this);
        btn_cancel.setOnClickListener(this);
        sex.setOnCheckedChangeListener(this);
        // 给定一个初始状态
        sex.check(R.id.boy);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_cancel:
                // 关闭
                finish();
                break;
            case R.id.btn_register:
                String uname = username.getText().toString();
                String pwd = password.getText().toString();
                // 非空
                if (TextUtils.isEmpty(uname) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    user.setUsername(uname);
                    user.setPassword(pwd);
                    // 跳转并且携带数据
                    Intent intent = new Intent(this, SetActivity.class);
                    intent.putExtra("user", user);
                    startActivity(intent);
                }
                break;
            default:
                break;
        }
    }


    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 状态改变时
        switch (checkedId) {
            case R.id.boy:
                user.setSex('男');
                break;
            case R.id.gril:
                user.setSex('女');
                break;
        }
    }
}