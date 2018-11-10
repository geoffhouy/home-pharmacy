package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.user;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.R;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.UserViewModel;

public class UserEditor extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_editor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //

        final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        final EditText editTextName = findViewById(R.id.UserEditorNameInput);
        final EditText editTextPin = findViewById(R.id.UserEditorPinInput);

        findViewById(R.id.UserEditorAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                if (TextUtils.isEmpty(editTextName.getText())
                        || TextUtils.isEmpty(editTextPin.getText())) {
                    Toast.makeText(getApplicationContext(), "User not saved due to missing information.", Toast.LENGTH_LONG).show();
                } else {
                    String userName = editTextName.getText().toString();
                    String userPin = editTextPin.getText().toString();

                    Toast.makeText(getApplicationContext(), "User \""+ userName + "\" saved!", Toast.LENGTH_LONG).show();
                    userViewModel.createUser(userName, userPin);
                    finish();
                }
            }
        });

        //

        findViewById(R.id.UserEditorDeleteButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                // Have to get the user to delete
                // userViewModel.deleteUser(user);

                finish();
            }
        });

        getIncomingIntent();
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("uuid")) {
            long uuid = getIntent().getLongExtra("uuid", -1);
            if (uuid != -1) {
                final UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

                final User user = userViewModel.getUserByUUID(uuid);

                final EditText editTextName = findViewById(R.id.UserEditorNameInput);
                final EditText editTextPin = findViewById(R.id.UserEditorPinInput);

                editTextName.setText(user.getName());
                editTextPin.setText(user.getPin());
            }
        }
    }

}
