package com.example.patrick.login_test;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button bLogin;
    EditText eID, ePassword, roomNumber;
    TextView registerLink;

    UserLocalStore userLocalStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        eID = (EditText) findViewById(R.id.Usernamefield);
        ePassword=(EditText) findViewById(R.id.Passfield);
        bLogin = (Button) findViewById(R.id.login);
        roomNumber = (EditText) findViewById(R.id.CVRoomNumber);
        registerLink = (TextView) findViewById(R.id.regsterHere);

        bLogin.setOnClickListener(this);
        registerLink.setOnClickListener(this);

        userLocalStore = new UserLocalStore(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.login:
                int studentID = Integer.parseInt(eID.getText().toString());
                String password = ePassword.getText().toString();
                int room = Integer.parseInt(roomNumber.getText().toString());


                User user = new User(studentID,password,room);

                authenticate(user);
                break;
            case R.id.regsterHere:
                startActivity(new Intent(this, Register.class));
                break;
        }
    }

    private void authenticate(User user){
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.fetchUserDataBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                if(returnedUser ==null){
                    showErrorMessage();
                }else {
                    logUserIn(returnedUser);
                }
            }
        });
    }
    private void showErrorMessage(){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(Login.this);
        dialogBuilder.setMessage("Incorrect User Details");
        dialogBuilder.setPositiveButton("OK", null);
        dialogBuilder.show();
    }
    private void logUserIn(User returnedUser){
        userLocalStore.storeUserData(returnedUser);
        userLocalStore.setUserLoggedIn(true);

        startActivity(new Intent(this, MainActivity.class));

    }
}


