package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.MainActivity;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule.ScheduleEditor;

import static android.content.ContentValues.TAG;
import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class Notifier extends BroadcastReceiver {
    @Override
    public void onReceive(Context context,Intent intent){
        Intent notificationIntent = new Intent(context,NotificationActivity.class);
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(notificationIntent);
    }
}
