package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class NotificationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Log.d(TAG, "onCreate: ");
//        setContentView(R.layout.activity_pill_editor);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("Take Pill:");
        alertDialog.setMessage("Did you take the pill?");
        alertDialog.setIcon(R.drawable.ic_perm_contact_calendar_black_24dp);
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the user took the pill put it into the list of taken pills
                        dialog.dismiss();
                        NotificationActivity.this.finish();
                    }
                });
        alertDialog.setButton(android.support.v7.app.AlertDialog.BUTTON_NEUTRAL, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //If the user didnt take the pill make another dialog box to confirm the selection
                        dialog.dismiss();
                        NotificationActivity.this.finish();
                    }
                });
        alertDialog.show();
    }
}
