package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.schedule;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter.ScheduleListAdapter;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ScheduleViewModel;

public class ScheduleMenu extends AppCompatActivity {

    private static ScheduleViewModel scheduleViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Displays live list of schedules in database

        RecyclerView recyclerView = findViewById(R.id.ScheduleInformation);
        final ScheduleListAdapter scheduleListAdapter = new ScheduleListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        //recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(scheduleListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);

        scheduleViewModel.getAllSchedules().observe(this, new Observer<List<Schedule>>() {

            @Override
            public void onChanged(@Nullable List<Schedule> schedules) {
                scheduleListAdapter.setSchedules(schedules);
            }

        });

        //

        findViewById(R.id.ScheduleAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                ScheduleEditor.selectedSchedule = null;
                startActivity(new Intent(ScheduleMenu.this, ScheduleEditor.class));
            }
        });
    }
}
