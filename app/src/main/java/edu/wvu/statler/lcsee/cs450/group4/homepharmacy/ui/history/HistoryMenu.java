package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.history;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.List;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Consumption;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter.ConsumptionListAdapter;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.ConsumptionViewModel;

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
        recyclerView.setAdapter(consumptionListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        consumptionViewModel = ViewModelProviders.of(this).get(ConsumptionViewModel.class);

        consumptionViewModel.getAllConsumptions().observe(this, new Observer<List<Consumption>>() {

            @Override
            public void onChanged(@Nullable List<Consumption> consumptions) {
                consumptionListAdapter.setConsumptions(consumptions);
            }

        });
    }
}
