package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class NotificationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate: ");
//        setContentView(R.layout.activity_pill_editor);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
//        alertDialog.setTitle("Take Pill:");
//        alertDialog.setMessage("Did you take the pill?");
//        alertDialog.setIcon(R.drawable.ic_launcher_foreground);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NotificationActivity.this.finish();
                //TODO Connor, Turn Light Off
                //TODO Geoff, add row to the history Table of the DB
            }
        });
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                NotificationActivity.this.finish();
                //TODO Connor, Turn Light Off
                //TODO Geoff, add row to the history Table of the DB
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
        //alertDialog.create().show();
        Intent intent = getIntent();
        Bundle extra = intent.getExtras();
        dialog.setTitle("Take Pill: "+extra.getString("PillName"));
        dialog.setMessage("Did you take the pill?");
        dialog.show();
    }
}