package edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.user;

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
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.db.entity.User;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.ui.adapter.UserListAdapter;
import edu.wvu.statler.lcsee.cs450.group4.homepharmacy.viewmodel.UserViewModel;

public class UserMenu extends AppCompatActivity {

    private static UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Displays live list of users in database

        RecyclerView recyclerView = findViewById(R.id.UserInformation);
        final UserListAdapter userListAdapter = new UserListAdapter(this);
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.HORIZONTAL));
        recyclerView.setAdapter(userListAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {

            @Override
            public void onChanged(@Nullable List<User> users) {
                userListAdapter.setUsers(users);
            }

        });

        //

        findViewById(R.id.UserAddButton).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){
                UserEditor.selectedUser = null;
                startActivity(new Intent(UserMenu.this, UserEditor.class));
            }

        });
    }
}
