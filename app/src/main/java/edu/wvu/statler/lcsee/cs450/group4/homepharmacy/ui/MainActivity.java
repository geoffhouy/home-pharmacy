package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.Notifier;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.history.HistoryMenu;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.pill.PillMenu;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule.ScheduleMenu;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.user.UserMenu;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ScheduleViewModel;

import static android.content.ContentValues.TAG;

public class MainActivity extends AppCompatActivity {

    private boolean firstRun = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        findViewById(R.id.SchedulesButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,ScheduleMenu.class));
            }
        });
        findViewById(R.id.PillsButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,PillMenu.class));
            }
        });
        findViewById(R.id.UsersButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,UserMenu.class));
            }
        });
        findViewById(R.id.HistoryButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(MainActivity.this,HistoryMenu.class));
            }
        });
//        findViewById(R.id.TestButton).setOnClickListener(new View.OnClickListener(){
//
//            @Override
//            public void onClick(View view){
//                AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
//                alertDialog.setTitle("Test");
//                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int which) {
//                                dialog.dismiss();
//                            }
//                        });
//                alertDialog.show();
//            }
//        });
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(firstRun){
            firstRun = false;
            final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);

            //TODO schedule is apparently always null so we need to fix that
            List<Schedule> schedules = scheduleViewModel.getAllSchedulesAsList();

            AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
            Log.d(TAG, "PROGRESS: Getting to if");
            if (schedules != null && schedules.size() > 0) {
                for (Schedule schedule : schedules) {

                    Intent myIntent = new Intent(getApplicationContext(), Notifier.class);
                    //putExtras are going to be used to push the information of the pill and the user to the notifcation itself
                    //name is the name of the field and the value is what we want to actually send
                    Log.d(TAG, "PROGRESS: Making Schedules");
                    myIntent.putExtra("ScheduleName", schedule.getName());
                    myIntent.putExtra("PillName", schedule.getPillName());
                    myIntent.putExtra("NumPills", schedule.getNumPillsToTake());
                    myIntent.putExtra("DispenserNumber", schedule.getDispenserNumber());
                    myIntent.putExtra("UserName", schedule.getUserName());

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
                        manager.setRepeating(manager.RTC_WAKEUP, System.currentTimeMillis() + 6000, 50000000, pendingIntent);
                        //Toast.makeText(MainActivity.this,"Alarm set1",Toast.LENGTH_SHORT).show();
                    } else {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
                        manager.setRepeating(manager.RTC_WAKEUP, System.currentTimeMillis() + 6000, 50000000, pendingIntent);
                        //Toast.makeText(MainActivity.this,"Alarm set2",Toast.LENGTH_SHORT).show();
                    }
                    //manager.set(AlarmManager.RTC_WAKEUP,Calendar.getInstance().getTime().getTime()+20, pendingIntent);
                    //Log.d(TAG, "onCreate: ");
                }
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
