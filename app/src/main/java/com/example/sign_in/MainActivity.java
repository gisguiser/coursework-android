package com.example.sign_in;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
public class MainActivity extends Activity {
   public static final String URL = "218.78.85.248";
    private Button btn;
    private ListView listView;
    //模拟新闻的标题数据
    private List<String> listTitle;
    //模拟新闻的信息数据
    private List<String> listRemark;
    //创建适配器对象
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化页面对象
        initUI();
        //将数据显示在页面上
        initDate();
        btn=(Button)findViewById(R.id.mButton);
        btn.setOnClickListener(new btnclock());
        }class  btnclock implements  OnClickListener{
         public void  onClick(View v){
            Intent intent=new Intent(MainActivity.this, SigninfoActivity.class);
            startActivity(intent);

        }
    }



    public void initUI(){
        listView=(ListView) findViewById(R.id.lv_text_view);
        listTitle=new ArrayList<String>();
        listRemark=new ArrayList<String>();

    }
    public  void initDate(){
        //模拟创建数据
        for (int i=0;i<99;i++){
            listTitle.add("签到名称"+i);
            listRemark.add("销毁时间"+i);
        }
        adapter=new MyAdapter(listTitle,listRemark,this);
        listView.setAdapter(adapter);
    }

}