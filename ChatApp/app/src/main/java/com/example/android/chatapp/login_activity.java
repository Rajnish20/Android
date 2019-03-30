package com.example.android.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {

    private EditText mEmail;
    private EditText mPassword;
    private Button mButton;

    private FirebaseAuth mAuth;
    private ProgressDialog mloginProgress;

    private android.support.v7.widget.Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);

        mAuth = FirebaseAuth.getInstance();

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.login_activity_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        mloginProgress = new ProgressDialog(this);
        mEmail = (EditText)findViewById(R.id.login_email);
        mPassword = (EditText)findViewById(R.id.login_password);
        mButton = (Button)findViewById(R.id.login_btn);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();
                if(!TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){

                    mloginProgress.setTitle("Logging You In");
                    mloginProgress.setMessage("Please wait while we check your credentials");
                    mloginProgress.setCanceledOnTouchOutside(false);
                    mloginProgress.show();
                    loginUser(email,password);

                }

            }
        });


    }

    private void loginUser(String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                    mloginProgress.dismiss();
                    Intent login_intent = new Intent(login_activity.this,MainActivity.class);
                    startActivity(login_intent);
                    finish();
                }else{

                    mloginProgress.hide();
                    Toast.makeText(login_activity.this,"Cannot sign in. Please check the form and try again.",Toast.LENGTH_LONG).show();
                }

            }
        });

    }
}
