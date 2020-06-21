package com.example.sign_in;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SetActivity extends Activity implements CompoundButton.OnCheckedChangeListener,
        View.OnClickListener {
    CheckBox cb_music, cb_lol, cb_swing;
    DatePicker datePicker;
    Button btn_finish;
    List<String> list = new ArrayList<String>();

    UserBean user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        initView();
        user = (UserBean) getIntent().getSerializableExtra("user");
    }

    private void initView() {
        cb_music = (CheckBox) findViewById(R.id.cb_music);
        cb_lol = (CheckBox) findViewById(R.id.cb_lol);
        cb_swing = (CheckBox) findViewById(R.id.cb_swing);
        cb_music.setOnCheckedChangeListener(this);
        cb_lol.setOnCheckedChangeListener(this);
        cb_swing.setOnCheckedChangeListener(this);
        datePicker = (DatePicker) findViewById(R.id.datepicker);
        btn_finish = (Button) findViewById(R.id.btn_finish);
        // 初始化时间
        Calendar calendar = Calendar.getInstance();
        datePicker.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new  DatePicker.OnDateChangedListener() {

                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        user.setBirthday(year + "-" + (monthOfYear + 1) + "-"
                                + dayOfMonth);
                    }
                });
        btn_finish.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.cb_music:
                if (isChecked)
                    list.add("未知");
                else
                    list.remove("未知");
                break;
            case R.id.cb_lol:
                if (isChecked)
                    list.add("未签到");
                else
                    list.remove("未签到");
                break;
            case R.id.cb_swing:
                if (isChecked)
                    list.add("已签到");
                else
                    list.remove("已签到");
                break;
        }
    }

    @Override
    public void onClick(View v) {
        // 将爱好设置进去
        if (list.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (String str : list) {
                sb.append(str + " ");
            }
            user.setHobbit(sb.toString());
        }
        // 跳转
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("user", user);
        startActivity(intent);
    }

}