package com.example.longfenghuang.hlfweather.Util;

/**
 * Created by longfenghuang on 16/5/3.
 */
public interface HttpCallbackListener {

    void onFinish(String response);
    void onError(Exception error);
}
