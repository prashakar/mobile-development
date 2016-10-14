package com.example.prash.mobile_labs;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.BatteryManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

/**
 * Created by prash on 29/09/16.
 */

public class BatteryNotifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        int batt_health = intent.getIntExtra(BatteryManager.EXTRA_HEALTH, BatteryManager.BATTERY_HEALTH_UNKNOWN);
        int batt_status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, BatteryManager.BATTERY_STATUS_UNKNOWN);
        int batt_plugged = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
        int batt_temp = intent.getIntExtra(BatteryManager.EXTRA_TEMPERATURE,0);

        String message = "Health: ";
        switch (batt_health) {
            case BatteryManager.BATTERY_HEALTH_GOOD:
                message += "Good";
                break;
            case BatteryManager.BATTERY_HEALTH_COLD:
                message += "Cold";
                break;
            case BatteryManager.BATTERY_HEALTH_OVERHEAT:
                message += "Overheating!";
                break;
            case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
                message += "Over voltage";
                break;
            case BatteryManager.BATTERY_HEALTH_UNKNOWN:
                message += "Unknown health";
        }
        message += "\nStatus: ";
        switch (batt_status) {
            case BatteryManager.BATTERY_STATUS_CHARGING:
                message += "Charging";
                break;
            case BatteryManager.BATTERY_STATUS_FULL:
                message += "Full";
                break;
            case BatteryManager.BATTERY_STATUS_DISCHARGING:
                message += "Discharging";
                break;
            case BatteryManager.BATTERY_STATUS_UNKNOWN:
                message += "Unknown charge status";
                break;
        }
        message += "\nPlugged Status: ";
        switch (batt_plugged) {
            case BatteryManager.BATTERY_PLUGGED_AC:
                message += "AC Charging";
                break;
            case BatteryManager.BATTERY_PLUGGED_USB:
                message += "USB Charging";
                break;
            case BatteryManager.BATTERY_PLUGGED_WIRELESS:
                message += "Wireless Charging";
                break;
            default:
                message += "On battery";
                break;
        }
        message += "\nTemperature: " + batt_temp;

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Battery Information")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(message))
                .setContentText(message);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService
                (Context.NOTIFICATION_SERVICE);
        Log.v("notification","battery_status");
        notificationManager.notify(1, mBuilder.build());

    }
}
