package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.MainActivity;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule.ScheduleEditor;

public class Notifier extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
    public Notifier(){
        super("Notifier");
    }
    @Override
    protected void onHandleIntent(Intent intent){
//        String msg = intent.getStringExtra(PARAM_IN_MSG);
        while(true){
            //SystemClock.sleep(60000); // 60 seconds
            SystemClock.sleep(5000); // 5 seconds
//            Intent dialogIntent = new Intent(this,NotificationActivity.class);
//            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(dialogIntent);
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this);
            mBuilder.setContentTitle("Test");
            mBuilder.setContentText("Test2");
            mBuilder.setStyle(new NotificationCompat.BigTextStyle());
            mBuilder.setSmallIcon(R.drawable.ic_person_black_24dp);
            mBuilder.setAutoCancel(false);
            Intent resultIntent = new Intent(this,MainActivity.class);
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            mBuilder.setContentIntent(pendingIntent);
            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1,mBuilder.build());
        }
//        String myFormat = "MM/dd/yy";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
//        String resultTxt = msg + " " + sdf.format(System.currentTimeMillis());
    }
}
