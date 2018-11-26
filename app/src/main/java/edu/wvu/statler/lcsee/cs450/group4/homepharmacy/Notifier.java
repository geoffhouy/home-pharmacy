package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import static android.content.ContentValues.TAG;

public class Notifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        Intent notificationIntent = new Intent(context,NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        notificationIntent.putExtras(intent);
        //TODO Connor, Notify ALEXA here, not below or above, here.
        //notifyAlexa("Take Pill "+intent.getExtras().getString("PillName"),);
        context.startActivity(notificationIntent);
        //Bundle extra = intent.getExtras();
        //Log.d(TAG, extra.getString("PillName"));
    }
}