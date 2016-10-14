package com.example.prash.mobile_labs;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Lab4Activity extends AppCompatActivity {
    BatteryNotifier batteryNotifier;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lab4);

        IntentFilter filter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        batteryNotifier = new BatteryNotifier();
        registerReceiver(batteryNotifier,filter);
    }
    protected void onStop(){
        super.onStop();
        unregisterReceiver(batteryNotifier);
    }
}
