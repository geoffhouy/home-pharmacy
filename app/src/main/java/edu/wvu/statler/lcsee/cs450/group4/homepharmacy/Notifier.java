package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

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
        for(int i = 0;i<3;i++){
            SystemClock.sleep(5000); // 5 seconds
            Intent dialogIntent = new Intent(this,NotificationActivity.class);
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(dialogIntent);
        }
//        String myFormat = "MM/dd/yy";
//        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
//        String resultTxt = msg + " " + sdf.format(System.currentTimeMillis());
    }
}
