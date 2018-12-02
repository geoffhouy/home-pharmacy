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

import com.google.android.things.pio.Gpio;
import com.google.android.things.pio.PeripheralManager;

import java.io.IOException;
import java.security.spec.ECField;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ConsumptionViewModel;

import static android.content.ContentValues.TAG;

public class NotificationActivity extends AppCompatActivity {

    PeripheralManager manager = PeripheralManager.getInstance();
    Gpio gpio = null;

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
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        final Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
        r.setLooping(true);
        r.play();
        SelectLED(extra.getInt("DispenserNumber"));
        SwitchLED(true);

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
//                NotificationActivity.this.finish();
                consumptionViewModel.createConsumption(
                        System.currentTimeMillis(),
                        extra.getString("PillName"),
                        extra.getString("UserName"),
                        true);
                r.stop();
                SwitchLED(false);
                NotificationActivity.this.finish();
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
//                NotificationActivity.this.finish();
                consumptionViewModel.createConsumption(
                        System.currentTimeMillis(),
                        extra.getString("PillName"),
                        extra.getString("UserName"),
                        false);
                r.stop();
                SwitchLED(false);
                NotificationActivity.this.finish();
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
    public void SelectLED(int num){
        try{
            switch (num) {
                case 1:
                    gpio = manager.openGpio("BCM4");
                    Log.d("Light",gpio.getName());
                    return;
                case 2:
                    gpio = manager.openGpio("BCM17");
                    Log.d("Light",gpio.getName());
                    return;
                case 3:
                    gpio = manager.openGpio("BCM27");
                    Log.d("Light",gpio.getName());
                    return;
                case 4:
                    gpio = manager.openGpio("BCM22");
                    Log.d("Light",gpio.getName());
                    return;
                case 5:
                    gpio = manager.openGpio("BCM23");
                    Log.d("Light",gpio.getName());
                    return;
                case 6:
                    gpio = manager.openGpio("BCM24");
                    Log.d("Light",gpio.getName());
                    return;
                case 7:
                    gpio = manager.openGpio("BCM25");
                    Log.d("Light",gpio.getName());
                    return;
                case 8:
                    gpio = manager.openGpio("BCM5");
                    Log.d("Light",gpio.getName());
                    return;
                case 9:
                    gpio = manager.openGpio("BCM6");
                    Log.d("Light",gpio.getName());
                    return;
                case 10:
                    gpio = manager.openGpio("BCM12");
                    Log.d("Light",gpio.getName());
                    return;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void SwitchLED(Boolean on){
        try{
            if(on){
                gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
                gpio.setActiveType(Gpio.ACTIVE_HIGH);
                gpio.setValue(true);
                Log.d("Light",gpio.getName()+" state: "+gpio.getValue());
            }else{
                Log.d("Light","Close "+gpio.getName());
                gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
                gpio.setActiveType(Gpio.ACTIVE_HIGH);
                gpio.setValue(false);
                Log.d("Light",gpio.getName()+" state: "+gpio.getValue());
                gpio.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
//    public static void LED(int num, boolean on) {
////        PeripheralManager manager = PeripheralManager.getInstance();
////        Gpio gpio = null;
////        try {
////            switch (num) {
////                case 1:
////                    gpio = manager.openGpio("BCM4");
////                case 2:
////                    gpio = manager.openGpio("BCM17");
////                case 3:
////                    gpio = manager.openGpio("BCM27");
////                    Log.d("NotificationActivity","BCM27");
////                case 4:
////                    gpio = manager.openGpio("BCM22");
////                case 5:
////                    gpio = manager.openGpio("BCM23");
////                case 6:
////                    gpio = manager.openGpio("BCM24");
////                case 7:
////                    gpio = manager.openGpio("BCM25");
////                case 8:
////                    gpio = manager.openGpio("BCM5");
////                case 9:
////                    gpio = manager.openGpio("BCM6");
////                    Log.d("NotificationActivity","BCM6");
////                case 10:
////                    gpio = manager.openGpio("BCM12");
////            }
//
//            if(on){
//                gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
//                gpio.setValue(!gpio.getValue());
//            }else{
//                Log.d("NotificationActivity","Close BCM6");
//                gpio.close();
//            }
////            if (on) {
////
////                // Initialize the pin as a low output
////                gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
////                // high voltage is considered active
////                //gpio.setActiveType(Gpio.ACTIVE_HIGH);
////
////                // Toggle the value to be LOW
////                gpio.setValue(true);
////            } else {
////                // Initialize the pin as a low output
////                gpio.setDirection(Gpio.DIRECTION_OUT_INITIALLY_LOW);
////                // high voltage is considered active
////                //gpio.setActiveType(Gpio.ACTIVE_LOW);
////
////                // Toggle the value to be LOW
////                gpio.setValue(false);
////            }
//
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//    }
}