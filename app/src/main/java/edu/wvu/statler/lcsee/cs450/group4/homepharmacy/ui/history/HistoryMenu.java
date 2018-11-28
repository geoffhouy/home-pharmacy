package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.history;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;

public class HistoryMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.HistoryInformation);
        //TODO Geoff, here setup the Consumption recycler view, we can just keep it as a simple list for now
    }
}
