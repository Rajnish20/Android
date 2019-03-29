package com.example.android.chatapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toolbar;

public class login_activity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;

    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.login_activity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mEmail = (EditText)findViewById(R.id.login_email);
        mPassword = (EditText)findViewById(R.id.login_password);


    }
}
