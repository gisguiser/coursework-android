package com.example.sign_in;

public class Cha {
    private  String state;
    private Payloadss payload;
    public String getState() {
        return state;
    }

    public  Payloadss getPayload() {
        return payload;
    }


}

class Payloadss {
    private String count;
    private Sign_list sign_list;
    private String token;

    public String getToken() {
        return token;
    }

    public Sign_list getSign_list() {
        return sign_list;
    }

    public String getCount() {
        return count;

    }
}
//用于构建下一个url参数
    class Sign_list {
    private String sign_name;
    private String time_limit;
    private String sign_id;


    public String getsign_name() {
        return sign_name;
    }

    public String gettime_limit() {
        return time_limit;
    }

    public String getsign_id() {
        return sign_id;
    }
}