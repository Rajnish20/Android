package com.example.android.chatapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
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

public class reg_activity extends AppCompatActivity {

    private EditText mDisplayName;
    private EditText mEmail;
    private EditText mPassword;
    private Button mCreateBtn;
    private ProgressDialog mregProgress;

    private FirebaseAuth mAuth;

    private android.support.v7.widget.Toolbar mToolbar;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_activity);

        mToolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.register_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("Create Account");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





        mAuth = FirebaseAuth.getInstance();

        mDisplayName = (EditText)findViewById(R.id.display_name);
        mEmail = (EditText)findViewById(R.id.email);
        mPassword = (EditText)findViewById(R.id.password);
        mCreateBtn = (Button)findViewById(R.id.crtbtn);
        mregProgress = new ProgressDialog(this);

        mCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String display_name = mDisplayName.getText().toString();
                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                if(!TextUtils.isEmpty(display_name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(password)){
                    mregProgress.setTitle("Registering User");
                    mregProgress.setMessage("Please wait while we are creating your account");
                    mregProgress.setCanceledOnTouchOutside(false);
                    mregProgress.show();

                    register_user(display_name,email,password);

                }
            }
        });
    }

    private void register_user(String display_name, String email, String password){

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    mregProgress.dismiss();

                    Intent main_intent = new Intent(reg_activity.this,MainActivity.class);
                    main_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(main_intent);
                    finish();

                }else{

                    mregProgress.hide();

                    Toast.makeText(reg_activity.this,"Cannot sign in. Please check the form and try again.",Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}
