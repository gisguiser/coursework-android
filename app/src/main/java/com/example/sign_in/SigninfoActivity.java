package com.example.sign_in;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.util.HttpCallbackListener;
import com.example.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

/**代码中TextView参数的说明：   sumnum:签到总人数  desc:签到描述   key:签到码     endtime:截止时间
                              statue:签到码状态  person:签到人员姓名
 */



public class SigninfoActivity extends Activity {
    TextView sumnum,desc,key,endtime,statue,person;
    private String originAddress = "http://218.78.85.248/v1/sign/query_sign_in"; //
    private String token="10sa3a023";
    private String sign_id="13531";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sumnum=(TextView) this.findViewById(R.id.sumnum);
        desc=(TextView)this.findViewById(R.id.desc);
        key=(TextView)this.findViewById(R.id.key);
        endtime=(TextView)this.findViewById(R.id.endtime);
        statue=(TextView)this.findViewById(R.id.statue);
        person=(TextView)this.findViewById(R.id.person);

        try {
            //构造完整URL
            String compeletedURL = HttpUtil.getURLWithParams(originAddress,sign_id);
            //发送请求
            HttpUtil.sendHttpRequest(compeletedURL, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                   JSONAnalysis(response);
                }

                @Override
                public void onError(Exception e) {
                    System.out.println("查询失败");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    protected void JSONAnalysis(String string) {
        JSONObject object = null;
        try {
            object = new JSONObject(string);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        /**
         * 在你获取的string这个JSON对象中，提取你所需要的信息。
         */
        JSONObject ObjectInfo = object.optJSONObject("payload");
        String state = ObjectInfo.optString("state");
        String  count = ObjectInfo.optString("sign_count");
        String message = ObjectInfo.optString("sign_message");
        String code = ObjectInfo.optString("sign_code");
        String limit = ObjectInfo.optString("time_limit");
        String person_name = ObjectInfo.optString("sign_person");
        sumnum.setText(count);
        desc.setText(message);
        key.setText(code);
        endtime.setText(limit);
        statue.setText(state);
        person.setText(person_name);

    }

}

