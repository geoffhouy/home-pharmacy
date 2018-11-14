package edu.wvu.statler.lcsee.cs450.group4.homepharmacy;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

public class NotificationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle("your title");
        alertDialog.setMessage("your message");
        alertDialog.setIcon(R.drawable.ic_perm_contact_calendar_black_24dp);

        alertDialog.show();
    }
}
