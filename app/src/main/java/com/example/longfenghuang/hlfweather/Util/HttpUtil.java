package com.example.longfenghuang.hlfweather.Util;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;

/**
 * Created by longfenghuang on 16/5/3.
 */
public class HttpUtil {

    public static void sendHttpRequest(final String address,final HttpCallbackListener listener){

        AsyncHttpClient client = new AsyncHttpClient();

        client.get(address, new TextHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                if (statusCode == 200 && listener != null) {
                    listener.onFinish(responseString);
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                if (listener != null) {
                    listener.onError(null);
                }
            }
        });
    }
}
