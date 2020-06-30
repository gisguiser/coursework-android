package com.example.sign_in;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.example.util.HttpRequest;
import com.google.gson.Gson;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**代码中TextView参数的说明：   sumnum:签到总人数  desc:签到描述   key:签到码     endtime:截止时间
                              statue:签到码状态  person:签到人员姓名
 */



public class SigninfoActivity extends Activity {
    TextView sumnum,desc,key,endtime,statue,person;
    private String originAddress = "http://218.78.85.248/v1/sign/query_sign_in"; //
    private String sign_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.logininfor);
        sumnum=(TextView) this.findViewById(R.id.sumnum);
        desc=(TextView)this.findViewById(R.id.desc);
        key=(TextView)this.findViewById(R.id.key);
        endtime=(TextView)this.findViewById(R.id.endtime);
        statue=(TextView)this.findViewById(R.id.statue);
        person=(TextView)this.findViewById(R.id.person);
        //获取前面页面传送的dign_id
        Bundle bunde=this.getIntent().getExtras();
        sign_id=bunde.getString("text");

        //发送http请求
        SharedPreferences config = getSharedPreferences("config", MODE_PRIVATE);
        String token = config.getString("token", "");
        if (token.isEmpty()) {
            String result=HttpRequest.sendGet(originAddress,sign_id,token);
            GSONAnalysis(result);
        }

    }
    protected void GSONAnalysis(String result) {
        Gson gson=new Gson();
        //解析http请求返回的gson
        user_token userToken=gson.fromJson(result,user_token.class);
        String state=userToken.getState();
        if(state == "true") {
            int count=userToken.getPayloads().getSign_count();
            sumnum.setText(count);
            String message=userToken.getPayloads().getSign_message();
            desc.setText(message);
            String code=userToken.getPayloads().getSign_code();
            key.setText(code);
            String limit=userToken.getPayloads().getTime_limit();
            endtime.setText(limit);
            List<String> person_name_list=userToken.getPayloads().sign_person;
            for(String person_name:person_name_list){
                person.setText(person_name);
            }

        }

    }
    //获取gsonz中state和payload
    class user_token{
        private String state;
        private Payloads payloads;
        public String getState(){
            return state;
        }
        public Payloads getPayloads(){
            return payloads;
        }
    }
    class Payloads{
        private int sign_count;
        private String sign_message;
        List<String> sign_person;
        String sign_code;
        String time_limit;
        public int getSign_count(){
            return sign_count;
        }
        public String getSign_message(){
            return sign_message;
        }
        public List<String> getSign_person(){
            return sign_person;
        }
        public String getSign_code(){
            return sign_code;
        }
        public String getTime_limit(){
            return time_limit;
        }

    }


}

