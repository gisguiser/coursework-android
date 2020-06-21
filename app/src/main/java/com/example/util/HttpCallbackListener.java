package com.example.util;
    //请求回调接口
    public interface HttpCallbackListener {

        void onFinish(String response);

        void onError(Exception e);
}
