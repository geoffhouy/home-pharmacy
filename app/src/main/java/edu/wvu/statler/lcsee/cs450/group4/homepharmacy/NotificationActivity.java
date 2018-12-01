package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.AlertDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ConsumptionViewModel;

import static android.content.ContentValues.TAG;

public class NotificationActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate: ");
//        setContentView(R.layout.activity_pill_editor);
        final ConsumptionViewModel consumptionViewModel = ViewModelProviders.of(this).get(ConsumptionViewModel.class);
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, R.style.MyAlertDialogStyle);
//        alertDialog.setTitle("Take Pill:");
//        alertDialog.setMessage("Did you take the pill?");
//        alertDialog.setIcon(R.drawable.ic_launcher_foreground);
        final Intent intent = getIntent();
        final Bundle extra = intent.getExtras();
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.setLooping(true);
        r.play();

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try{
//                    wait(10000);
//                    r.stop();
//                }catch(Exception e){
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        Log.d(TAG, "PROGRESS: Getting to notification Activity");
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NotificationActivity.this.finish();
                //TODO Connor, Turn Light Off
                consumptionViewModel.createConsumption(
                        System.currentTimeMillis(),
                        extra.getString("PillName"),
                        extra.getString("UserName"),
                        true);
                r.stop();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NotificationActivity.this.finish();
                //TODO Connor, Turn Light Off
                consumptionViewModel.createConsumption(
                        System.currentTimeMillis(),
                        extra.getString("PillName"),
                        extra.getString("UserName"),
                        false);
                r.stop();
            }
        });
//        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "Yes",
//                new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        //If the user took the pill put it into the list of taken pills
//                        dialog.dismiss();
//                        NotificationActivity.this.finish();
//                    }
//                });

        final AlertDialog dialog = alertDialog.create();
        dialog.setCanceledOnTouchOutside(false);
        //alertDialog.create().show();
        alertDialog.setIcon(R.drawable.ic_notification);
        dialog.setTitle("Take Pill: "+extra.getString("PillName"));
        //dialog.setTitle("Take Pill:");
        dialog.setMessage("Did you take the pill?");
        dialog.show();
    }

}