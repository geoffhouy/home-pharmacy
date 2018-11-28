package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.Notifier;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ScheduleViewModel;

public class ScheduleEditor extends AppCompatActivity {

    public static Schedule selectedSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final Calendar myCalendar = Calendar.getInstance();
        final TextInputEditText dateEdit = (TextInputEditText) findViewById(R.id.ScheduleEditorDateInput);
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                myCalendar.set(Calendar.YEAR, i);
                myCalendar.set(Calendar.MONTH,i1);
                myCalendar.set(Calendar.DAY_OF_MONTH,i2);
                String myFormat = "MM/dd/yy";
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
                dateEdit.setText(sdf.format(myCalendar.getTime()));
            }
        };
        dateEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(ScheduleEditor.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        final TextInputEditText timeEdit = (TextInputEditText) findViewById(R.id.ScheduleEditorTimeInput);
        final TimePickerDialog.OnTimeSetListener time = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                myCalendar.set(Calendar.HOUR_OF_DAY,i);
                myCalendar.set(Calendar.MINUTE,i1);
                if(myCalendar.get(Calendar.HOUR_OF_DAY) >= 12){
                    timeEdit.setText(i+":"+i1+" PM");
                }
                else{
                    timeEdit.setText(i+":"+i1+" AM");
                }
            }
        };
        timeEdit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                new TimePickerDialog(ScheduleEditor.this,time,myCalendar.get(Calendar.HOUR_OF_DAY),myCalendar.get(Calendar.MINUTE),false).show();
            }
        });

        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);

        final EditText editTextName = findViewById(R.id.ScheduleEditorNameInput);
        // myCalendar has all the information for the YEAR,MONTH,DAY_OF_MONTH, HOUR_OF_DAY, and MINUTE, just use myCalendar.get(Calendar.WHATEVER_YOU_WANT)
        final EditText editTextNumPills = findViewById(R.id.ScheduleEditorNumberOfPillsInput);
        final EditText editTextPillName = findViewById(R.id.ScheduleEditorPillNameInput);
        final EditText editTextDispenserNumber = findViewById(R.id.ScheduleEditorDispenserNumberInput);
        final EditText editTextUser = findViewById(R.id.ScheduleEditorUserInput);

        final AlarmManager alarmManager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        final Context context = this;

        findViewById(R.id.ScheduleEditorAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextName.getText())
                        || TextUtils.isEmpty(dateEdit.getText())
                        || TextUtils.isEmpty(timeEdit.getText())
                        || TextUtils.isEmpty(editTextNumPills.getText())
                        || TextUtils.isEmpty(editTextPillName.getText())
                        || TextUtils.isEmpty(editTextDispenserNumber.getText())
                        || TextUtils.isEmpty(editTextUser.getText())) {
                    Toast.makeText(getApplicationContext(), "Schedule not saved due to missing information.", Toast.LENGTH_LONG).show();
                } else {
                    String scheduleName = editTextName.getText().toString();

                    // Convert date and time to one timestamp
                    long timestamp = myCalendar.getTimeInMillis();

                    int numPills = Integer.parseInt(editTextNumPills.getText().toString());
                    String pillName = editTextPillName.getText().toString();

                    int dispenserNumber = Integer.parseInt(editTextDispenserNumber.getText().toString());
                    String userName = editTextUser.getText().toString();

                    long interval = 0L;

                    // if 8 hours
                    // + that many milliseconds

                    Toast.makeText(getApplicationContext(), "Schedule \"" + scheduleName + "\" saved!", Toast.LENGTH_LONG).show();
                    scheduleViewModel.createSchedule(scheduleName, timestamp, numPills,pillName, dispenserNumber, userName, interval);

                    //TODO Geoff, Here is where you make the alarm stuff like in main activity
                    Intent intent = new Intent(getApplicationContext(), Notifier.class);

                    intent.putExtra("UserName", userName);
                    intent.putExtra("PillName", pillName);
                    intent.putExtra("Timestamp", timestamp);
                    intent.putExtra("NumPills", numPills);
                    intent.putExtra("DispenserNumber", dispenserNumber);

                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.CUPCAKE) {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, System.currentTimeMillis() + 6000, 50000000, pendingIntent);
                        //Toast.makeText(MainActivity.this,"Alarm set1",Toast.LENGTH_SHORT).show();
                    } else {
                        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
                        alarmManager.setRepeating(alarmManager.RTC_WAKEUP, System.currentTimeMillis() + 6000, 50000000, pendingIntent);
                        //Toast.makeText(MainActivity.this,"Alarm set2",Toast.LENGTH_SHORT).show();
                    }

                    finish();
                }
            }
        });

        findViewById(R.id.ScheduleEditorDeleteButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (selectedSchedule != null) {
                    scheduleViewModel.deleteSchedule(selectedSchedule);
                    Toast.makeText(getApplicationContext(), "Schedule \"" + selectedSchedule.getName() + "\" deleted!", Toast.LENGTH_LONG).show();
                    selectedSchedule = null;
                } else {
                    Toast.makeText(getApplicationContext(), "Schedule not deleted.", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

        long uuid = getIncomingIntent();
        if (uuid != -1) {
            final Schedule schedule = scheduleViewModel.getScheduleByUUID(uuid);
            editTextName.setText(schedule.getName());

            myCalendar.setTimeInMillis(schedule.getTimestamp());

            String myFormat = "MM/dd/yy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
            dateEdit.setText(sdf.format(myCalendar.getTime()));

            Date datetime = new Date(schedule.getTimestamp());
            DateFormat formatter = new SimpleDateFormat("HH:mm", Locale.US);
            formatter.setTimeZone(TimeZone.getTimeZone("EST"));
            timeEdit.setText(formatter.format(datetime));

            //dateEdit.setText(String.format("%f", schedule.getTimestamp()));
            //timeEdit.setText(String.format("%f", schedule.getTimestamp()));
            editTextUser.setText(schedule.getUserName());
            editTextNumPills.setText(String.format("%d", schedule.getNumPillsToTake()));
            editTextDispenserNumber.setText(String.format("%d", schedule.getDispenserNumber()));
            editTextPillName.setText(schedule.getPillName());
            selectedSchedule = schedule;
        }
    }

    private long getIncomingIntent() {
        if (getIntent().hasExtra("uuid")) {
            return getIntent().getLongExtra("uuid", -1);
        } else {
            return -1;
        }
    }
}