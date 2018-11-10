package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule;

import android.app.DatePickerDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ScheduleViewModel;

public class ScheduleEditor extends AppCompatActivity {

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

        //Do something similar to above here for the time

        //

        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);

        final EditText editTextName = findViewById(R.id.ScheduleEditorNameInput);
        // date
        // time
        final EditText editTextNumPills = findViewById(R.id.ScheduleEditorNumberOfPillsInput);
        final EditText editTextPillName = findViewById(R.id.ScheduleEditorPillNameInput);
        final EditText editTextDispenserNumber = findViewById(R.id.ScheduleEditorDispenserNumberInput);
        final EditText editTextUser = findViewById(R.id.ScheduleEditorUserInput);

        findViewById(R.id.ScheduleEditorAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(editTextName.getText())
                        || TextUtils.isEmpty(dateEdit.getText())
                       // || TextUtils.isEmpty(timeEdit.getText())
                        || TextUtils.isEmpty(editTextNumPills.getText())
                        || TextUtils.isEmpty(editTextPillName.getText())
                        || TextUtils.isEmpty(editTextDispenserNumber.getText())
                        || TextUtils.isEmpty(editTextUser.getText())) {
                    Toast.makeText(getApplicationContext(), "Schedule not saved due to missing information.", Toast.LENGTH_LONG).show();
                } else {
                    String scheduleName = editTextName.getText().toString();
                    // Convert date and time to one timestamp
                    long timestamp = 0l;
                    int numPills = Integer.parseInt(editTextNumPills.getText().toString());
                    String pillName = editTextPillName.getText().toString();
                    int dispenserNumber = Integer.parseInt(editTextDispenserNumber.getText().toString());
                    String userName = editTextUser.getText().toString();

                    Toast.makeText(getApplicationContext(), "Schedule \"" + scheduleName + "\" saved!", Toast.LENGTH_LONG).show();
                    scheduleViewModel.createSchedule(scheduleName, timestamp, numPills,pillName, dispenserNumber, userName);
                    finish();
                }
            }
        });

        findViewById(R.id.ScheduleEditorDeleteButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

                // Have to get the schedule to delete
                // scheduleViewModel.deleteSchedule(schedule);

                finish();
            }
        });

        //
    }
}