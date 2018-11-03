package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.Schedule;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.MainActivity;

public class ScheduleMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.ScheduleAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                startActivity(new Intent(ScheduleMenu.this,ScheduleEditor.class));
            }
        });
    }

}
