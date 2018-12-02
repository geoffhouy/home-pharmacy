package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.pill;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.w3c.dom.Text;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.Pill;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.PillViewModel;

public class PillEditor extends AppCompatActivity {

    public static Pill selectedPill = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pill_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final PillViewModel pillViewModel = ViewModelProviders.of(this).get(PillViewModel.class);

        final EditText editTextName = findViewById(R.id.PillEditorPillNameInput);
        final EditText editTextDescription = findViewById(R.id.PillEditorPillDescriptionInput);
        final EditText editTextDispenser = findViewById(R.id.PillEditorDispenserNumberInput);
        final EditText editTextNumber = findViewById(R.id.PillEditorNumber);

        findViewById(R.id.PillEditorAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (TextUtils.isEmpty(editTextName.getText())
                        || TextUtils.isEmpty(editTextDescription.getText())
                        || TextUtils.isEmpty(editTextDispenser.getText())
                        || TextUtils.isEmpty(editTextNumber.getText())) {
                    Toast.makeText(getApplicationContext(), "Pill not saved due to missing information", Toast.LENGTH_LONG).show();
                } else {
                    String pillName = editTextName.getText().toString();
                    String pillDescription = editTextDescription.getText().toString();
                    int dispenserNumber = Integer.parseInt(editTextDispenser.getText().toString());
                    int numOfPills = Integer.parseInt(editTextNumber.getText().toString());

                    Toast.makeText(getApplicationContext(), "Pill \"" + pillName + "\" successfully saved!", Toast.LENGTH_LONG).show();
                    pillViewModel.createPill(pillName, pillDescription, numOfPills, 0l, dispenserNumber);
                    finish();
                }
            }
        });

        findViewById(R.id.PillEditorDeleteButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (selectedPill != null) {
                    pillViewModel.deletePill(selectedPill);
                    Toast.makeText(getApplicationContext(), "Pill \"" + selectedPill.getName() + "\" deleted!", Toast.LENGTH_LONG).show();
                    selectedPill = null;
                } else {
                    Toast.makeText(getApplicationContext(), "Pill not deleted.", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

        long uuid = getIncomingIntent();
        if (uuid != -1) {
            final Pill pill = pillViewModel.getPillByUUID(uuid);
            editTextName.setText(pill.getName());
            editTextDescription.setText(pill.getDescription());
            editTextDispenser.setText(String.format("%d", pill.getDispenserNumber()));
            editTextNumber.setText(String.format("%d", pill.getCount()));
            selectedPill = pill;
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
