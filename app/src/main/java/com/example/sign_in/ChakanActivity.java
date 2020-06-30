package com.example.sign_in;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.sign_in.Cha;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ChakanActivity extends Activity {
    //发送http请求

    private Button btn;


    TextView name, limit,signid;

    protected void onCreate(Bundle savedInstanceState) {


        Log.d("TAG", "");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view_item);
        name = (TextView) this.findViewById(R.id.sign_name);
        limit = (TextView) this.findViewById(R.id.time_limit);
        signid=(TextView) this.findViewById(R.id.sign_id);
        //获取前面页面传送的sign_id
       /* Bundle bunde=this.getIntent().getExtras();
        sign_id=bunde.getString("text");*/
       btn=(Button)this.findViewById(R.id.mButton);
       btn.setOnClickListener(new mClick());

        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        String token = config.getString("token", "");
        if (token.isEmpty()) {
            String result1 = HttpRequest.sendGet("http://218.78.85.248/v1/sign/query_all_sign_in", "", token);//获取截至时间和用户姓名
            String param = getparam(result1);
            String result = HttpRequest.sendGet("http://218.78.85.248/v1/sign/query_all_sign_in", param, token);
            GSONAnalysis(result);
        }
    }
    class mClick implements OnClickListener
    {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent();
            intent.setClass(ChakanActivity.this,SigninfoActivity.class);
            Bundle bundle=new Bundle();;
            String id=signid.getText().toString();
            bundle.putString("text",id);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }


    protected String getparam(String result) {
            Gson gson = new Gson();
            //解析http请求返回的gson
            Cha cha = gson.fromJson(result, Cha.class);
            String state = cha.getState();
            if(state == "true") {
                String signname = cha.getPayload().getSign_list().getsign_name();
                String timelimit = cha.getPayload().getSign_list().gettime_limit();
                String param="sign_name="+signname+";time_limit="+timelimit;
                return param;
            }
           return " ";
        }
        protected void GSONAnalysis (String results){
            Gson gson = new Gson();
            //解析http请求返回的gson
            Cha cha = gson.fromJson(results, Cha.class);
            String state = cha.getState();
            if(state == "true") {
                String signname = cha.getPayload().getSign_list().getsign_name();
                name.setText(signname);
                String timelimit = cha.getPayload().getSign_list().gettime_limit();
                limit.setText(timelimit);
                String sign_id=cha.getPayload().getSign_list().getsign_id();
                signid.setText(sign_id);
            }
        }

}