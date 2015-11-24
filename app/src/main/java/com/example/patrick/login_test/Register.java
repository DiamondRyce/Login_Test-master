package com.example.patrick.login_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Register extends AppCompatActivity implements View.OnClickListener{

    Button bRegister;
    EditText eID,ePassword,roomNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eID = (EditText) findViewById(R.id.Usernamefield);
        ePassword = (EditText) findViewById(R.id.Passfield);
        roomNumber = (EditText) findViewById(R.id.CVRoomNumber);
        bRegister = (Button) findViewById(R.id.register);

        bRegister.setOnClickListener(this);

        Spinner staticSpinner = (Spinner) findViewById(R.id.building);
        Spinner staticSpinner2 = (Spinner) findViewById(R.id.school);

        // Create an ArrayAdapter using the string array and a default spinner
        ArrayAdapter<CharSequence> housingAdapter = ArrayAdapter
                .createFromResource(this, R.array.housing_array,
                        android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> schoolAdapter = ArrayAdapter
                .createFromResource(this, R.array.school_array,
                        android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        housingAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schoolAdapter
                .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        staticSpinner.setAdapter(housingAdapter);
        staticSpinner2.setAdapter(schoolAdapter);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.register:

                int studentID = Integer.parseInt(eID.getText().toString());
                String password = ePassword.getText().toString();
                int room = Integer.parseInt(roomNumber.getText().toString());

                User registeredData = new User(studentID, password,room);
                registerUser(registeredData);
                break;
        }
    }

    private void registerUser(User user){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this,Login.class));
            }
        });
    }
}
