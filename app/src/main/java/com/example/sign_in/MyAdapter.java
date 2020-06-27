package com.example.sign_in;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends BaseAdapter {
    private List<String> listTitle;
    private List<String> listRemark;
    private Context context;
    public MyAdapter(List<String> listTitle,List<String> listRemark,Context context){
        this.listTitle=listTitle;
        this.listRemark=listRemark;
        this.context=context;
    }
    @Override
    public int getCount() {
        //return返回的是int类型，也就是页面要显示的数量。
        return listRemark.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;
        if (convertView==null){
            //通过一个打气筒 inflate 可以把一个布局转换成一个view对象
            view=View.inflate(context, R.layout.list_view_item,null);
        }else {
            view=convertView;//复用历史缓存对象
        }
        //页面的图标
        ImageView img=(ImageView) view.findViewById(R.id.iv_icon);
        //页面文字标题
        TextView tvTitle=(TextView)view.findViewById(R.id.tv_title);
        //页面的备注
        TextView tvRemark=(TextView)view.findViewById(R.id.tv_message);
        //将标题显示页面上
        tvTitle.setText(listTitle.get(position));
        //将信息显示在页面上
        tvRemark.setText(listRemark.get(position));
        return view;
    }
}