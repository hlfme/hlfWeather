package com.example.longfenghuang.hlfweather.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.longfenghuang.hlfweather.Service.AutoUpdateWeatherService;

public class AutoUpdateReceiver extends BroadcastReceiver {
    public AutoUpdateReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, AutoUpdateWeatherService.class);
        context.startService(i);

    }
}
