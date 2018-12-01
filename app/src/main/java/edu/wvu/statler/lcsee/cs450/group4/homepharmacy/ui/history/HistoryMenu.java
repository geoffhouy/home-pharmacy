package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.history;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.Notifier;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Schedule;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter.ConsumptionListAdapter;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ConsumptionViewModel;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ScheduleViewModel;

public class HistoryMenu extends AppCompatActivity {

    private static ConsumptionViewModel consumptionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.HistoryInformation);
        final ConsumptionListAdapter consumptionListAdapter = new ConsumptionListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(consumptionListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        consumptionViewModel = ViewModelProviders.of(this).get(ConsumptionViewModel.class);

        consumptionViewModel.getAllConsumptions().observe(this, new Observer<List<Consumption>>() {

            @Override
            public void onChanged(@Nullable List<Consumption> consumptions) {
                consumptionListAdapter.setConsumptions(consumptions);
            }

        });

        //This code will just run notifier when history is first run, this is just for testing during the expo
        final ScheduleViewModel scheduleViewModel = ViewModelProviders.of(this).get(ScheduleViewModel.class);
        List<Schedule> schedules = scheduleViewModel.getAllSchedulesAsList();
        Schedule schedule = schedules.get(0);
        AlarmManager manager = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        Intent myIntent = new Intent(getApplicationContext(), Notifier.class);
        myIntent.putExtra("ScheduleName", schedule.getName());
        myIntent.putExtra("PillName", schedule.getPillName());
        myIntent.putExtra("NumPills", schedule.getNumPillsToTake());
        myIntent.putExtra("DispenserNumber", schedule.getDispenserNumber());
        myIntent.putExtra("UserName", schedule.getUserName());
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
        manager.setRepeating(manager.RTC_WAKEUP, System.currentTimeMillis() + 6000, 5000000000L, pendingIntent);
        //********************************
    }
}
