package com.example.patrick.login_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button logOut;
    EditText eID;
    UserLocalStore userLocalStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eID = (EditText)findViewById(R.id.Usernamefield);
        logOut = (Button) findViewById(R.id.logout);
        logOut.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public  void onStart(){
        super.onStart();

        if(authenticate() == true){
            displayUserDetails();
        }else{
            startActivity(new Intent(MainActivity.this, Login.class));
        }
    }

    private boolean authenticate(){
        return userLocalStore.getUserLoggin();
    }

    public void displayUserDetails(){
        User user = userLocalStore.getLoggedInUser();
        eID.setText(user.studentID);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.logout:
                userLocalStore.clearUserData();
                userLocalStore.setUserLoggedIn(false);

                startActivity(new Intent(this, Login.class));
                break;
        }
    }
}

