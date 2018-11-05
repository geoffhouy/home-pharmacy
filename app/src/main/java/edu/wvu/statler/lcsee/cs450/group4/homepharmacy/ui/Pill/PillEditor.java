package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.Pill;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.MainActivity;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.Schedule.ScheduleMenu;

public class PillEditor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        findViewById(R.id.PillEditorAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Add current pill
                finish();
            }
        });
        findViewById(R.id.PillEditorDeleteButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                //Add function to delete current selected pill
                finish();
            }
        });
    }
}
