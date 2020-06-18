package com.example.sign_in;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import android.widget.ArrayAdapter;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView textView = (TextView)findViewById(R.id.text);
        ListView listView=(ListView)findViewById(R.id.list);
        List<String> list = new ArrayList<String>();
        list.add("计算机");
        list.add("高等数学");
        list.add("汇编");
        list.add("机械");
        list.add("政治");
        list.add("外语");
        ///可以一直添加，在真机运行后可以下拉列表
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);
    }
}
