package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.pill;

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
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter.PillListAdapter;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.PillViewModel;

public class PillMenu extends AppCompatActivity {

    private static PillViewModel pillViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Displays live list of pills in database

        RecyclerView recyclerView = findViewById(R.id.PillInformation);
        final PillListAdapter pillListAdapter = new PillListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(pillListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        pillViewModel = ViewModelProviders.of(this).get(PillViewModel.class);

        pillViewModel.getAllPills().observe(this, new Observer<List<Pill>>() {

            @Override
            public void onChanged(@Nullable List<Pill> pills) {
                pillListAdapter.setPills(pills);
            }

        });

        //

        findViewById(R.id.PillAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                PillEditor.selectedPill = null;
                startActivity(new Intent(PillMenu.this, PillEditor.class));
            }

        });
    }
}
